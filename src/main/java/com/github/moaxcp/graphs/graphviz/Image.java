package com.github.moaxcp.graphs.graphviz;

import java.awt.image.RenderedImage;

public class Image {
    private RenderedImage image;
    private int delay;
    private DisposalMethod disposalMethod;
    private boolean interlace;
    private int imageLeftPosition;
    private int imageTopPosition;

    public enum DisposalMethod {
        NONE("none"),
        DO_NOT_DISPOSE("doNotDispose"),
        RESTORE_TO_BACKGROUND("restoreToBackgroundColor"),
        RESTORE_TO_PREVIOUS("restoreToPrevious");

        private String value;

        private DisposalMethod(String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }
    }

    public Image(RenderedImage image) {
        this.image = image;
    }

    public RenderedImage getImage() {
        return image;
    }

    public int getDelay() {
        return delay;
    }

    public Image delay(int delay) {
        if(delay < 0) {
            throw new IllegalArgumentException("delay must be greater than or equal to 0.");
        }
        this.delay = delay;
        return this;
    }

    public DisposalMethod getDisposalMethod() {
        return disposalMethod;
    }

    public Image disposalMethod(DisposalMethod disposalMethod) {
        this.disposalMethod = disposalMethod;
        return this;
    }

    public boolean isInterlace() {
        return interlace;
    }

    public Image interlace(boolean interlace) {
        this.interlace = interlace;
        return this;
    }

    public int getImageLeftPosition() {
        return imageLeftPosition;
    }

    public Image imageLeftPosition(int imageLeftPosition) {
        if(imageLeftPosition < 0 || imageLeftPosition > 65535) {
            throw new IllegalArgumentException("imageLeftPosition must be a value of 0 to 65535.");
        }
        this.imageLeftPosition = imageLeftPosition;
        return this;
    }

    public int getImageTopPosition() {
        return imageTopPosition;
    }

    public Image imageTopPosition(int imageTopPosition) {
        if(imageTopPosition < 0 || imageTopPosition > 65535) {
            throw new IllegalArgumentException("imageTopPosition must be a value of 0 to 65535.");
        }
        this.imageTopPosition = imageTopPosition;
        return this;
    }
}
