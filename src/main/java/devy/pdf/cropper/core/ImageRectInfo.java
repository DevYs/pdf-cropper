package devy.pdf.cropper.core;

import java.awt.image.BufferedImage;

public class ImageRectInfo {

    int emptySpaceWidth;
    int emptySpaceHeight;
    int width;
    int height;
    int leftX;
    int leftY;
    int rightX;
    int rightY;

    public ImageRectInfo(BufferedImage bufferedImage) {
        this.emptySpaceWidth = (bufferedImage.getWidth() / 3) - 100;
        this.emptySpaceHeight = (bufferedImage.getHeight() / 3) - 100;
        this.width = bufferedImage.getWidth() - this.emptySpaceWidth;
        this.height = bufferedImage.getHeight() - this.emptySpaceHeight;
        this.leftX = this.emptySpaceWidth;
        this.leftY = 200;
        this.rightX = 0;
        this.rightY = 200;
    }

    public int getEmptySpaceWidth() {
        return emptySpaceWidth;
    }

    public int getEmptySpaceHeight() {
        return emptySpaceHeight;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getLeftX() {
        return leftX;
    }

    public int getLeftY() {
        return leftY;
    }

    public int getRightX() {
        return rightX;
    }

    public int getRightY() {
        return rightY;
    }

    @Override
    public String toString() {
        return "ImageRectInfo{" +
                "emptySpaceWidth=" + emptySpaceWidth +
                ", emptySpaceHeight=" + emptySpaceHeight +
                ", width=" + width +
                ", height=" + height +
                ", leftX=" + leftX +
                ", leftY=" + leftY +
                ", rightX=" + rightX +
                ", rightY=" + rightY +
                '}';
    }
}
