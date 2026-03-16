package es.jllopezalvarez.ejemplos.ejemplo20issuetracker.web.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain getSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "/logout").permitAll()
                        .requestMatchers("/h2-console", "/h2-console/**").permitAll()
                        .requestMatchers("/styles/**", "/scripts/**", "/favicon/**", "/images/**").permitAll()
                        .anyRequest().authenticated())
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/h2-console/**"))
                .headers(headers -> headers
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                .formLogin(login -> login
                        .loginPage("/login").permitAll())
                .logout(logout->logout
                        .logoutUrl("/logout").permitAll()
                        .logoutSuccessUrl("/"))
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                .httpBasic(AbstractHttpConfigurer::disable);
        return http.build();
    }
}
