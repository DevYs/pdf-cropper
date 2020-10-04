package devy.pdf.cropper.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PDFCropper {

    public static void main(String[] args) throws IOException {
        String sourcePath = "/home/devy/Documents/pdf/";
        String filename = "리액트를_다루는_기술_개정판_2019";
//        ImageExtractor.toPng(sourcePath + filename + ".pdf", 400);

        List<Integer> changePoint = new ArrayList<>();
        changePoint.add(109);
        changePoint.add(206);
        changePoint.add(277);
        changePoint.add(400);
        changePoint.add(507);
        changePoint.add(822);
        changePoint.add(857);
        ImageCropper.crop(sourcePath + filename, changePoint);

        PDFCreator.generateImageToPDF("/home/devy/Documents/pdf/dest/" + filename + ".pdf", "/home/devy/Documents/pdf/" + filename + "/cropped/");
    }

}
