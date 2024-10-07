import java.io.File;

// text extraction
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;

public class CardScanner {
  public static void main(String[] args) {
    Tesseract tesseract = new Tesseract();
    try {

      tesseract.setDatapath("D:/Tess4J/tessdata");

      // the path of your tess data folder
      // inside the extracted file
      String text = tesseract.doOCR(new File("image.jpg"));

      // path of your image file
      System.out.print(text);
    } catch (TesseractException e) {

      Mat capturedIMage = WebcamCapture.captureImage(640);

      String[][] extractedText = TextExtractor.extractText(capturedIMage);

      System.out.println("Extracted Text:");
      for (String[] row : extractedText) {
        for (String text : row) {
          System.out.print((text != null ? text : "[null]") + "\t");
        }
        System.out.println();
      }

    }
  }
}