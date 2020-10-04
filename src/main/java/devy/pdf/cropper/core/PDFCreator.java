package devy.pdf.cropper.core;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDJpeg;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDXObjectImage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class PDFCreator {

    public static boolean generateImageToPDF(String destPDFFile, String srcImageDir) {
        boolean result = true;
        PDDocument doc = null;

        File file = new File(srcImageDir);
        int length = file.list().length;

        try {
            doc = new PDDocument();

            for(int pageNo=1; pageNo<=length; pageNo++) {
                FileInputStream inputStream = new FileInputStream(srcImageDir + "/" + pageNo + ".jpeg");

                PDXObjectImage xImage = new PDJpeg(doc, inputStream);
                PDPage page = new PDPage(new PDRectangle(xImage.getWidth(), xImage.getHeight()));
                doc.addPage(page);

                PDPageContentStream contentStream = new PDPageContentStream(doc, page);
                contentStream.drawImage(xImage, 0, 0);

                closeQuietly(contentStream);

                System.out.println("creating " + pageNo);
            }

            doc.save(destPDFFile);
        } catch (Exception e) {
            System.out.println("ImageToPDF-generateImageToPDF ERROR : " + e.getMessage());
        } finally {
            closeQuietly(doc);
        }

        return result;
    }

    /**
     * 이미지 크기에 맞는 용지 크리를 반환함<br />
     * default 용지는 A4
     * @param imageWidth
     * @param imageHeight
     * @return
     */
    public static PDRectangle getPageSize(int imageWidth, int imageHeight) {
        PDRectangle pdRectangle = null;
        pdRectangle = PDPage.PAGE_SIZE_A4;
        return pdRectangle;
    }

    public static void closeQuietly(PDPageContentStream pdPageContentStream) {
        try {
            if (pdPageContentStream != null) {
                pdPageContentStream.close();
            }
        } catch (IOException ioe) {
            System.out.println("IOUtils-closeQuietly ERROR : " + ioe.getMessage());
        }
    }

    public static void closeQuietly(PDDocument pdDocument) {
        try {
            if (pdDocument != null) {
                pdDocument.close();
            }
        } catch (IOException ioe) {
            System.out.println("IOUtils-closeQuietly ERROR : " + ioe.getMessage());
        }
    }

}
