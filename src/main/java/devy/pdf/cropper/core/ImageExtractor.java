package devy.pdf.cropper.core;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFImageWriter;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageExtractor {

    public static boolean toPng(String sourceFile, int resolution) throws IOException {
        boolean result = false;
        String imageFormat = "jpeg";
        int endOfPages = 0;
        PDDocument pDDocument = null;

        try {
            pDDocument = PDDocument.load(new File(sourceFile));
            endOfPages = pDDocument.getNumberOfPages();
        } catch (IOException ioe) {
            System.out.println("PDFToImage-extractPagesAsImage ERROR : " + ioe.getMessage());
        }

        String destFilePrefix = destFilePrefix(sourceFile) + "/";
        File destDirectory = new File(destFilePrefix);
        PDFImageWriter imageWriter = new PDFImageWriter();

        if(!destDirectory.exists()) {
            destDirectory.mkdir();
        }

        try {
            result = imageWriter.writeImage(pDDocument, imageFormat, null, 1, endOfPages, destFilePrefix, BufferedImage.TYPE_INT_RGB, resolution);
        } catch (IOException ioe) {
            System.out.println("PDFToImage-extractPagesAsImage ERROR : " + ioe.getMessage());
        }

        pDDocument.close();

        return result;

    }

    public static String destFilePrefix(String sourceFile) {
        String result = "";
        int lastSeparatorIndex = sourceFile.lastIndexOf("/") + 1;
        int lastCommaIndex =  sourceFile.lastIndexOf(".");
        result = sourceFile.substring(0, lastSeparatorIndex);
        result = result + (sourceFile.substring(lastSeparatorIndex, lastCommaIndex));

        return result;

    }

}
