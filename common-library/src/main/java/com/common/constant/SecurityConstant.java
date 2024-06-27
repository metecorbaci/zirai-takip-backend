package com.common.constant;

import java.util.List;

public class SecurityConstant {
    /**
     * Public URLs : Bu URL'ler herkes tarafından erişilebilir.
     */
    public static final String[] PUBLIC_URLS = {
            "/auth/**",
            "/api/**",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/api-docs/**",
    };
    
    public static final List<String> CORS_URLS = List.of(
            "*"
    );
    public static final List<String> CORS_ALLOWED_METHODS = List.of(
            "GET",
            "POST",
            "PUT",
            "DELETE",
            "OPTIONS"
    );
}
