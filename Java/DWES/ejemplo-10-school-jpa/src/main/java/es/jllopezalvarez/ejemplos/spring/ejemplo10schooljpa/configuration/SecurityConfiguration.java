package es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.configuration;

import es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.security.SecurityMonitor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Value("${shop.security.bcrypt.cost.factor}")
    private int hashCostFactor;

    @Bean
    public SecurityFilterChain getSecurityFilterChain(HttpSecurity http, SecurityMonitor securityMonitor) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // Autorizar el acceso a la consola H2 para cualquier usuario
                        .requestMatchers("/h2-console", "/h2-console/*").permitAll()
                        // Autorizar el acceso al listado de estudiantes a cualquier usuario
                        .requestMatchers("/students", "/students/").permitAll()
                        // Autorizar el acceso a cualquier cosa de teachers (incluidos formularios) a cualquier usuario
                        .requestMatchers("/teachers", "/teachers/*").permitAll()
                        // Cualquier otra cosa (incluye los formularios de students) solo para usuarios autenticados
                        .anyRequest().authenticated())
                // No aplicar la protección CSRF a ciertas rutas de la aplicación
                .csrf(csrf -> csrf.ignoringRequestMatchers("/teachers/*", "/h2-console/*"))
                // Configurar las cabeceras X-Frame-Options para que no se pueda cargar
                // la aplicación dentro de frames de otro dominio.
                .headers(config -> config.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                // Activar la autenticación con formularios y sesión, con opciones por defecto.
                //.formLogin(Customizer.withDefaults())
                // Activar la autenticación con formularios y sesión pero personalizando.
                .formLogin(form -> form
                        .loginPage("/login").permitAll()
                        .successHandler(securityMonitor)
                        .failureHandler(securityMonitor))
                // Desactivar esquema HTTP basic, porque se usa Forms Authentication.
                .httpBasic(AbstractHttpConfigurer::disable);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Aunque NoOpPasswordEncode esté "deprecado", lo usamos para que el
        // login con "user" + UUID funcione hasta que configuremos completamente Spring Security
        // return NoOpPasswordEncoder.getInstance();

        // BCryptPasswordEncoder - Más o menos antiguo pero seguro
        // return new BCryptPasswordEncoder();
        // Cambiando el número de iteraciones para hacer que tarde más en calcular el hash.
        return new BCryptPasswordEncoder(hashCostFactor);

        // Pbkdf2PasswordEncoder - Más seguro, necesita más parámetros
        // return new Pbkdf2PasswordEncoder("Secret", 10, 10, 10);
    }


}
