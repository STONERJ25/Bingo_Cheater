import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.videoio.VideoCapture;

public class WebcamCapture {
  static {
    // Load OpenCV native library
    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
  }

  public static Mat captureImage(int sideLength) {
    // Initialize video capture
    VideoCapture camera = new VideoCapture(0); // 0 is the default camera index

    if (!camera.isOpened()) {
      System.out.println("Error: Camera is not opened.");
      return null;
    }

    // Capture a frame
    Mat frame = new Mat();
    camera.read(frame);

    // Check if the frame is empty
    if (frame.empty()) {
      System.out.println("Error: Could not capture a frame.");
      camera.release();
      return null;
    }

    // Ensure the captured frame is large enough
    int minSize = Math.min(frame.width(), frame.height());
    if (minSize < sideLength) {
      System.out.println("Error: The specified side length is larger than the captured frame.");
      camera.release();
      return null;
    }

    // Crop the frame to the desired square size
    Rect cropArea = new Rect(0, 0, sideLength, sideLength);
    Mat squareFrame = new Mat(frame, cropArea);

    // Release the camera
    camera.release();

    return squareFrame; // Return the captured square image
  }
}