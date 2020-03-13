package helpers;

import static org.monte.media.FormatKeys.EncodingKey;
import static org.monte.media.FormatKeys.FrameRateKey;
import static org.monte.media.FormatKeys.KeyFrameIntervalKey;
import static org.monte.media.FormatKeys.MIME_AVI;
import static org.monte.media.FormatKeys.MediaTypeKey;
import static org.monte.media.FormatKeys.MimeTypeKey;
import static org.monte.media.VideoFormatKeys.CompressorNameKey;
import static org.monte.media.VideoFormatKeys.DepthKey;
import static org.monte.media.VideoFormatKeys.ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE;
import static org.monte.media.VideoFormatKeys.QualityKey;

import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.monte.media.Format;
import org.monte.media.FormatKeys.MediaType;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;

public class VideoRecorder {

    public static final String USER_DIR = "user.dir";
    public static final String DOWNLOADED_FILES_FOLDER = "target/report/Videos";

    private static ScreenRecorder screenRecorder;
    
    private static String videoPath = "";

//    public static void main(String[] args) throws Exception {
//
//        startRecording();
//
//        WebDriver driver;
//        System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver_70v");
//		ChromeOptions options = new ChromeOptions();
//		options.addArguments("start-maximized");
//		options.addArguments("disable-infobars");
//		driver = new ChromeDriver(options);
//		ConfigurationHelper.init();
//		driver.manage().deleteAllCookies();
//		driver.manage().window().fullscreen();
//		driver.get("http://www.google.com");
//		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
//        
//        WebElement element = driver.findElement(By.name("q"));
//        element.sendKeys("BreizhCamp 2018");
//        element.submit();
//        System.out.println("Page title is: " + driver.getTitle());
//        driver.quit();
//        stopRecording();
//    }

    public static String startRecording() throws Exception {
    	videoPath = "";
    	String fileName = "JPMC";
        File file = new File(System.getProperty(USER_DIR) + File.separator + DOWNLOADED_FILES_FOLDER);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");
    	Date date = new Date();
    	fileName = fileName+"-"+dateFormat.format(date);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        Rectangle captureSize = new Rectangle(0, 0, width, height);

        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();

        screenRecorder = new SpecializedScreenRecorder(gc, captureSize, new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
                new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, DepthKey, 24, FrameRateKey,
                        Rational.valueOf(15), QualityKey, 1.0f, KeyFrameIntervalKey, 15 * 60),
                new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black", FrameRateKey, Rational.valueOf(30)), null, file, fileName);
        screenRecorder.start();
        videoPath = file+File.separator+fileName+".avi";
        return videoPath;
    }

    public static void stopRecording() throws Exception {
        screenRecorder.stop();
    }
    
    public static String getVideoPath() {
    	return videoPath;
    }
}