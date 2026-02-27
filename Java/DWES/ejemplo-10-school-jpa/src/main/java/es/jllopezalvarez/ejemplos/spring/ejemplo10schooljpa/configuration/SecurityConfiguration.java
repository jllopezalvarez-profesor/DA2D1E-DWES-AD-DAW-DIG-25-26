package es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain getSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                // Autorizar el acceso a la consola H2 para cualquier usuario
                .authorizeHttpRequests(auth -> auth.requestMatchers("/h2-console", "/h2-console/*").permitAll())
                // Autorizar el acceso al listado de estudiantes a cualquier usuario
                .authorizeHttpRequests(auth -> auth.requestMatchers("/students", "/students/").permitAll())
                // Autorizar el acceso a cualquier cosa de teachers (incluidos formularios) a cualquier usuario
                .authorizeHttpRequests(auth -> auth.requestMatchers("/teachers", "/teachers/*").permitAll())
                // Cualquier otra cosa (incluye los formularios de students) solo para usuarios autenticados
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
                // Activar esquema HTTP basic con configuración por defecto. Como usamos Forms Authentication, no se activa.
                //.httpBasic(Customizer.withDefaults());
                .csrf(csrf -> csrf.ignoringRequestMatchers("/teachers/*", "/h2-console/*"))
                .headers(AbstractHttpConfigurer::disable)
                .formLogin(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Aunque NoOpPasswordEncode esté "deprecado", lo usamos para que el
        // login con "user" + UUID funcione hasta que configuremos completamente Spring Security
        // return NoOpPasswordEncoder.getInstance();

        // BCryptPasswordEncoder - Más o menos antiguo pero seguro
        return new BCryptPasswordEncoder();

        // Pbkdf2PasswordEncoder - Más seguro, necesita más parámetros
        // return new Pbkdf2PasswordEncoder("Secret", 10, 10, 10);
    }



}
