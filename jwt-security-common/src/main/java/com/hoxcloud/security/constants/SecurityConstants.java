package com.hoxcloud.security.constants; 

public final class SecurityConstants {

    private SecurityConstants() {
        throw new IllegalStateException("Utility class");
    }

    // HTTP Headers
    public static final String AUTHORIZATION_HEADER = "Authorization";

    // JWT Prefix
    public static final String TOKEN_PREFIX = "Bearer ";

    // JWT Claims
    public static final String ROLE_CLAIM = "role";
    public static final String TOKEN_TYPE_CLAIM = "type";
    public static final String JTI_CLAIM = "jti";

    // Token Types
    public static final String ACCESS_TOKEN = "ACCESS";
    public static final String REFRESH_TOKEN = "REFRESH";

    // Spring Security Roles
    public static final String ROLE_PREFIX = "ROLE_";
}
