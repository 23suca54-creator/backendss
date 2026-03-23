package com.example.jobapplication.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

/**
 * Global CORS configuration.
 * - Applies to all endpoints (/**)
 * - Allowed origins are configurable via property `app.cors.allowed-origins` (comma-separated)
 *   Default is "*" to allow all origins during development. Change in production.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${app.cors.allowed-origins:*}")
    private String allowedOriginsProp;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        String[] allowed = splitOrigins(allowedOriginsProp);
        // If single wildcard, allow any origin pattern
        if (allowed.length == 1 && "*".equals(allowed[0].trim())) {
            registry.addMapping("/**")
                    .allowedOriginPatterns("*")
                    .allowedMethods("*")
                    .allowedHeaders("*")
                    .allowCredentials(false)
                    .maxAge(3600);
        } else {
            registry.addMapping("/**")
                    .allowedOrigins(allowed)
                    .allowedMethods("*")
                    .allowedHeaders("*")
                    .allowCredentials(false)
                    .maxAge(3600);
        }
    }

    private String[] splitOrigins(String prop) {
        if (prop == null || prop.trim().isEmpty()) return new String[] {"*"};
        return Arrays.stream(prop.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .toArray(String[]::new);
    }

    /**
     * Expose CorsConfigurationSource so Spring Security can use the same configuration
     * when `http.cors()` is enabled in the security configuration.
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        String[] allowed = splitOrigins(allowedOriginsProp);
        if (allowed.length == 1 && "*".equals(allowed[0].trim())) {
            config.addAllowedOriginPattern("*");
        } else {
            Arrays.stream(allowed).forEach(config::addAllowedOrigin);
        }
        config.addAllowedMethod(CorsConfiguration.ALL);
        config.addAllowedHeader(CorsConfiguration.ALL);
        config.setAllowCredentials(false);
        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
