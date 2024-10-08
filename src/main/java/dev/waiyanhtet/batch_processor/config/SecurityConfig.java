package dev.waiyanhtet.batch_processor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilter(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizedRequest ->
                        authorizedRequest
                                .requestMatchers("/api/v1/greet").permitAll()
                                .anyRequest().authenticated());

//        we can use opaqueToken also to validate token
//        http.oauth2ResourceServer(server ->
//                server.opaqueToken(
//                        Customizer.withDefaults()
//                )
//        );

        http.oauth2ResourceServer(server ->
                        server.jwt(
                                Customizer.withDefaults()
                        ));

        return http.build();
    }

}
