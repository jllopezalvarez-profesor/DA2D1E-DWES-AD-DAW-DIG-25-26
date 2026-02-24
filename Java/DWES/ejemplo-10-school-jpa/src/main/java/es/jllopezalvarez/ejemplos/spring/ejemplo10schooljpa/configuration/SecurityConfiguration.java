package es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain getSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth.requestMatchers("/students", "/students/").anonymous())
                // .authorizeHttpRequests(auth-> auth.requestMatchers("/students", "/students/").authenticated())
                .authorizeHttpRequests(auth -> auth.requestMatchers("/teachers", "/teachers/*").anonymous())
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
                //.httpBasic(Customizer.withDefaults());
                .formLogin(Customizer.withDefaults());

        return http.build();

    }
}
