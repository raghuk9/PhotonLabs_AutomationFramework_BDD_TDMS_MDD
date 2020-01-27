package api.zapi;

import api.zapi.jwt.jwt_core.*;
import api.zapi.jwt.jwt_implementation.*;

public class ZFJCloudRestClient {

    private JwtGenerator jwtGenerator;

    private ZFJCloudRestClient() {
    }

    public static Builder restBuilder(String zephyrBaseUrl, String accessKey, String secretKey, String userName) {
        return new ZFJCloudRestClient().new Builder(zephyrBaseUrl, accessKey, secretKey, userName);
    }

    public JwtGenerator getJwtGenerator() {
        return jwtGenerator;
    }

    public class Builder {

        private String accessKey;
        private String secretKey;
        private String userName;
        private String zephyrBaseUrl;

        private Builder(String zephyrBaseUrl, String accessKey, String secretKey, String userName) {
            this.zephyrBaseUrl = zephyrBaseUrl;
            this.accessKey = accessKey;
            this.secretKey = secretKey;
            this.userName = userName;
        }

        public ZFJCloudRestClient build() {
            ZConfig zConfig = new ZConfig(accessKey, secretKey, userName, zephyrBaseUrl);
            jwtGenerator = new JwtGeneratorImpl(zConfig);

            return ZFJCloudRestClient.this;
        }
    }
}
