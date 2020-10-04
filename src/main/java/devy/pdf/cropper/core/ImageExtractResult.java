package devy.pdf.cropper.core;

public class ImageExtractResult {

    private String path;
    private int numberOfPage;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getNumberOfPage() {
        return numberOfPage;
    }

    public void setNumberOfPage(int numberOfPage) {
        this.numberOfPage = numberOfPage;
    }

    @Override
    public String toString() {
        return "ImageConvertResult{" +
                "path='" + path + '\'' +
                ", numberOfPage=" + numberOfPage +
                '}';
    }
}
