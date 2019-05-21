package com.github.moaxcp.graphs.graphviz;

import javax.imageio.stream.ImageOutputStream;
import javax.imageio.stream.MemoryCacheImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class GifBuilder {
    private List<BufferedImage> images = new ArrayList<>();
    private int maxWidth;
    private int maxHeight;
    private int minWidth = Integer.MAX_VALUE;
    private int minHeight = Integer.MAX_VALUE;
    private int delay;
    private int loop;
    private Path file;

    public GifBuilder image(BufferedImage image) {
        images.add(image);
        if(image.getWidth() > maxWidth) {
            maxWidth = image.getWidth();
        }
        if(image.getHeight() > maxHeight) {
            maxHeight = image.getHeight();
        }
        if(image.getWidth() < minWidth) {
            minWidth = image.getWidth();
        }
        if(image.getHeight() < minHeight) {
            minHeight = image.getHeight();
        }
        return this;
    }

    public GifBuilder delay(int delay) {
        this.delay = delay;
        return this;
    }

    public GifBuilder loop(int loop) {
        this.loop = loop;
        return this;
    }

    public GifBuilder file(Path file) {
        this.file = file;
        return this;
    }

    public byte[] buildBytes() throws IOException {
        try(ByteArrayOutputStream bytes = new ByteArrayOutputStream()) {
            writeToOutput(bytes);
            return bytes.toByteArray();
        }
    }

    private void writeToOutput(OutputStream out) throws IOException {
        try(ImageOutputStream wrapped  = new MemoryCacheImageOutputStream(out);
                GifWriter writer = new GifWriter.Builder().screenWidth(maxWidth).screenHeight(maxHeight).defaultDelay(delay).loop(loop).out(wrapped).build()) {
            for (BufferedImage image : images) {
                BufferedImage background = null;
                if(minWidth != maxWidth || minHeight != maxHeight) {
                    background = createOutputImage(guessBackgroundColor(images.get(0)), maxWidth, maxHeight, images.get(0).getType());
                }
                if (background != null) {
                    image = centerOnOutput(background, image);
                }
                writer.writeToSequence(image);
            }
        }

    }

    public void build() throws IOException {
        Files.createDirectories(file.getParent());
        try(FileOutputStream out = new FileOutputStream(file.toFile())) {
            writeToOutput(out);
        }
    }

    private int guessBackgroundColor(BufferedImage image) {
        return image.getRGB(0, 0);
    }

    private BufferedImage createOutputImage(int background, int width, int height, int type) {
        BufferedImage out = new BufferedImage(width, height, type);
        Graphics2D g2d = out.createGraphics();
        g2d.setBackground(new Color(background));
        g2d.clearRect(0, 0, width, height);
        g2d.dispose();
        return out;
    }

    private BufferedImage centerOnOutput(BufferedImage output, BufferedImage image) {
        int x = (output.getWidth() - image.getWidth()) / 2;
        int y = (output.getHeight() - image.getHeight()) / 2;
        Graphics2D g2d = output.createGraphics();
        g2d.drawImage(image, x, y, image.getWidth(), image.getHeight(), null);
        g2d.dispose();
        return output;
    }
}
