package api.zapi.jwt.jwt_core;

import java.net.URI;

public interface JwtGenerator {

    String generateJWT(String requestMethod, URI uri, int jwtExpiryWindowSeconds);
}
