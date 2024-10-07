import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import java.io.File;

public class TextExtractor {
    public static String[][] extractText(Mat image) {
        String[][] textArray = new String[5][5];

        // Convert Mat to a file
        String tempImagePath = "temp_image.png";
        Imgcodecs.imwrite(tempImagePath, image);

        // Create Tesseract instance
        ITesseract tesseract = new Tesseract();
        tesseract.setDatapath("path/to/tessdata"); // Set the path to your tessdata folder
        tesseract.setLanguage("eng"); // Set the language

        try {
            // Perform OCR on the image
            String result = tesseract.doOCR(new File(tempImagePath));

            // Split the result into lines
            String[] lines = result.split("\n");
            int count = 0;

            // Fill the 5x5 array
            for (String line : lines) {
                if (count < 25) {
                    String[] words = line.split(" ");
                    for (int j = 0; j < words.length && j < 5; j++) {
                        textArray[count / 5][count % 5] = words[j];
                        count++;
                        if (count >= 25)
                            break;
                    }
                }
            }
        } catch (TesseractException e) {
            System.out.println("Error during OCR: " + e.getMessage());
        }

        // Return the text array
        return textArray;
    }
}
