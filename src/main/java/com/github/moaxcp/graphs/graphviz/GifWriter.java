package com.github.moaxcp.graphs.graphviz;

import javax.imageio.*;
import javax.imageio.metadata.IIOInvalidTreeException;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class GifWriter implements AutoCloseable {
    private int screenWidth;
    private int screenHeight;
    private boolean loop;
    private int defaultDelay;
    private String defaultDisposalMethod;
    private boolean defaultInterlace;
    private boolean first = true;
    private ImageWriter gifWriter;

    private static final List<String> validDisposalMethods = List.of("none", "doNotDispose", "restoreToBackgroundColor", "restoreToPrevious", "");

    private GifWriter(Builder builder) {
        requireNonNull(builder.out, "out must not be null.");
        if(builder.screenWidth < 1) {
            throw new IllegalArgumentException("screenWidth must not be less than one.");
        }
        if(builder.screenHeight < 1) {
            throw new IllegalArgumentException("screenHeight must not be less than one.");
        }
        screenWidth = builder.screenWidth;
        screenHeight = builder.screenHeight;
        loop = builder.loop;
        setDefaultDelay(builder.defaultDelay);
        setDefaultDisposalMethod(builder.defaultDisposalMethod);
        defaultInterlace = builder.defaultInterlace;
        gifWriter = getWriter();
        gifWriter.setOutput(builder.out);
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenheight() {
        return screenHeight;
    }

    public boolean getLoop() {
        return loop;
    }

    public int getDefaultDelay() {
        return defaultDelay;
    }

    public void setDefaultDelay(int defaultDelay) {
        if(defaultDelay < 0) {
            throw new IllegalArgumentException("defaultDelay must not be less than 0.");
        }
        this.defaultDelay = defaultDelay;
    }

    public void setDefaultDisposalMethod(String defaultDisposalMethod) {
        requireNonNull(defaultDisposalMethod, "defaultDisposalMethod must not be null.");
        if(!validDisposalMethods.contains(defaultDisposalMethod)) {
            throw new IllegalArgumentException("defaultDisposalMethod most be either non, background.");
        }
        this.defaultDisposalMethod = defaultDisposalMethod;
    }

    public String getDefaultDisposalMethod() {
        return defaultDisposalMethod;
    }

    public void writeToSequence(RenderedImage img) throws IOException {
        writeToSequence(img, defaultDelay);
    }

    public void writeToSequence(RenderedImage img, int delay) throws IOException {
        IIOMetadata imageMetadata = getMetadata(img.getWidth(), img.getHeight(), delay, defaultDisposalMethod, defaultInterlace);
        if(first) {
            IIOMetadata streamMetadata = gifWriter.getDefaultStreamMetadata(null);

            gifWriter.prepareWriteSequence(streamMetadata);

            String metadataName = imageMetadata.getNativeMetadataFormatName();

            IIOMetadataNode root = (IIOMetadataNode) imageMetadata.getAsTree(metadataName);

            IIOMetadataNode extensions = getNode(root, "ApplicationExtensions");
            IIOMetadataNode extension = new IIOMetadataNode("ApplicationExtension");
            extension.setAttribute("applicationID", "NETSCAPE");
            extension.setAttribute("authenticationCode", "2.0");

            int loopData = loop ? 0 : 1;
            extension.setUserObject(new byte[]{0x1, (byte) (loopData & 0xFF), (byte) ((loopData >> 8) & 0xFF)});
            extensions.appendChild(extension);
            first = false;
        }
        gifWriter.writeToSequence(new IIOImage(img, null, imageMetadata), null);
    }

    private IIOMetadata getMetadata(int width, int height, int delay, String disposalMethod, boolean interlace) throws IIOInvalidTreeException {
        ImageTypeSpecifier type = ImageTypeSpecifier.createFromBufferedImageType(BufferedImage.TYPE_3BYTE_BGR);
        IIOMetadata metadata = gifWriter.getDefaultImageMetadata(type, null);
        String metadataName = metadata.getNativeMetadataFormatName();

        IIOMetadataNode root = (IIOMetadataNode) metadata.getAsTree(metadataName);

//        IIOMetadataNode descriptor = getNode(root, "ImageDescriptor");
//        descriptor.setAttribute("imageLeftPosition", "0");
//        descriptor.setAttribute("imageTopPosition", "0");
//        descriptor.setAttribute("imageWidth", Integer.toString(width));
//        descriptor.setAttribute("imageHeight", Integer.toString(height));
//        descriptor.setAttribute("interlaceFlag", Boolean.toString(interlace).toUpperCase());

        IIOMetadataNode gce = getNode(root, "GraphicControlExtension");
        gce.setAttribute("disposalMethod", disposalMethod);
        gce.setAttribute("userInputFlag", "FALSE");
        gce.setAttribute("transparentColorFlag", "FALSE");
        gce.setAttribute("delayTime", Integer.toString(delay));
        gce.setAttribute("transparentColorIndex", "0");

        IIOMetadataNode comments = getNode(root, "CommentExtensions");
        comments.setAttribute("CommentExtension", "Created by ");

        metadata.setFromTree(metadataName, root);

        return metadata;
    }

    @Override
    public void close() throws IOException {
        gifWriter.endWriteSequence();
    }

    private static ImageWriter getWriter() {
        Iterator<ImageWriter> iter = ImageIO.getImageWritersBySuffix("gif");
        return iter.next();
    }

    private static IIOMetadataNode getNode(IIOMetadataNode rootNode, String nodeName) {
        int nNodes = rootNode.getLength();
        for (int i = 0; i < nNodes; i++) {
            if (rootNode.item(i).getNodeName().compareToIgnoreCase(nodeName) == 0) {
                return ((IIOMetadataNode) rootNode.item(i));
            }
        }
        IIOMetadataNode node = new IIOMetadataNode(nodeName);
        rootNode.appendChild(node);
        return node;
    }

    public static class Builder {
        private int screenWidth;
        private int screenHeight;
        private boolean loop = true;
        private int defaultDelay = 100;
        private String defaultDisposalMethod = "none";
        private boolean defaultInterlace = false;
        private ImageOutputStream out;

        public Builder screenWidth(int screenWidth) {
            this.screenWidth = screenWidth;
            return this;
        }

        public Builder screenHeight(int screenHeight) {
            this.screenHeight = screenHeight;
            return this;
        }

        public Builder loop(boolean loop) {
            this.loop = loop;
            return this;
        }

        public Builder defaultDelay(int defaultDelay) {
            this.defaultDelay = defaultDelay;
            return this;
        }

        public Builder out(ImageOutputStream out) {
            this.out = out;
            return this;
        }

        public Builder defaultDisposalMethod(String defaultDisposalMethod) {
            this.defaultDisposalMethod = defaultDisposalMethod;
            return this;
        }

        public Builder defaultInterlace(boolean defaultInterlace) {
            this.defaultInterlace = defaultInterlace;
            return this;
        }

        public GifWriter build() {
            return new GifWriter(this);
        }
    }
}
