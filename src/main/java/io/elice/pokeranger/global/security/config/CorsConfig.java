package io.elice.pokeranger.global.security.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class CorsConfig implements WebMvcConfigurer {


    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("*");
        config.addAllowedOriginPattern("http://kdt-cloud-1-team03.elicecoding.com");
//        config.addAllowedOriginPattern("http://192.168.0.4:5500/");
        config.addAllowedOriginPattern("http://localhost" ); // 수정된 부분
        config.addAllowedOriginPattern("http://192.168.170.1" ); // 수정된 부분

        config.addAllowedOriginPattern("http://192.168.56.1" ); // 수정된 부분
        config.addAllowedOriginPattern("http://192.168.0.4" ); // 수정된 부분


//        config.addAllowedOriginPattern("http://localhost:63342");
//        config.addAllowedOriginPattern("http://127.0.0.1:5500");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}



