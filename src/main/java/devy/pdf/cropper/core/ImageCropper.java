package devy.pdf.cropper.core;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.List;

public class ImageCropper {

     private static final String DIR_CROP = "cropped";

     public static void crop(String path, List<Integer> changepPoint) throws IOException {
          String firstPath = path + "/1.jpeg";
          String targetDirPath = path;
          String destDirPath = path + "/" + DIR_CROP;
          File targetPathDir = new File(targetDirPath);
          File firstPathDir = new File(firstPath);
          File destPathDir = new File(destDirPath);

          int length = targetPathDir.list(new FilenameFilter() {
               @Override
               public boolean accept(File file, String s) {
                    if(s.contains(".jpeg") || s.contains(".jpg")) {
                         return true;
                    }
                    return false;
               }
          }).length;

          BufferedImage firstBufferedImage = ImageIO.read(firstPathDir);

          // 클래스 내에서 값 조정을 잘해줘야 잘 짤림
          ImageRectInfo imageRectInfo = new ImageRectInfo(firstBufferedImage);
          int w = imageRectInfo.getWidth();
          int h = imageRectInfo.getHeight();
          int leftX = imageRectInfo.getLeftX();
          int leftY = imageRectInfo.getLeftY();
          int rightX = imageRectInfo.getRightX();
          int rightY = imageRectInfo.getRightY();

          if(!destPathDir.exists()) {
               destPathDir.mkdir();
          }

          for(int i = 1; i<= length; i++) {
               int x = i % 2 == 0 ? leftX : rightX;
               int y = i % 2 == 0 ? leftY : rightY;

               if(changepPoint.contains(i)) {
                    int temp = rightX;
                    rightX = leftX;
                    leftX = temp;

                    temp = rightY;
                    rightY = leftY;
                    leftY = temp;
               }

               String targetPath = targetDirPath + "/" + i + ".jpeg";
               String destPath = destDirPath + "/" + i + ".jpeg";

               System.out.println("Write " + destPath);

               File target = new File(targetPath);
               File dest = new File(destPath);
               BufferedImage bufferedImage = ImageIO.read(target);
               BufferedImage subImage = bufferedImage.getSubimage(x, y, w, h);
               ImageIO.write(subImage, "jpeg", dest);

          }
     }

}
