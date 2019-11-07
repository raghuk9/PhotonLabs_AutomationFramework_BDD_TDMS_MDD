package helpers;

public enum Platform {

    WEB_PORTAL("webPortal"),
    UNKNOWN("unknown");
    String name;
    Platform(String name) {
        this.name = name;
    }

    public static Platform getPlatform(String name) {
        for (Platform platform : Platform.values()) {
            if (platform.name.equals(name)) {
                return platform;
            }
        }

        return Platform.UNKNOWN;
    }
}
