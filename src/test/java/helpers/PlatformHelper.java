package helpers;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;

import platforms.AndroidPlatform;
import platforms.JPMCPlatform;
import platforms.WebPortal;

public class PlatformHelper {

    public static JPMCPlatform getPlatformInstance(String type) {
        
        JPMCPlatform platform;
            switch (type) {
            case "webPortal_Dev":
            case "android_Web":
                platform = new WebPortal();
                break;
            case "android":
                platform = new AndroidPlatform();
                break;
            default:
                throw new RuntimeException("Invalid platform");
            }
            return platform;
    }

    public static JPMCPlatform getCurrentPlatform() throws FileNotFoundException, IOException, ParseException {
        return PlatformHelper.getPlatformInstance(ConfigurationHelper.getPlatform());
    }

}
