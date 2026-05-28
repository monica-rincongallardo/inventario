package com.prueba.inventario.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


// configuración de seguridad
@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws  Exception {
        http.csrf(csrf -> csrf.disable()) // desactivar crft temporalmente
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/inventario") // ambos roles pueden ver inventario
                        .hasAnyRole("ADMINISTRADOR", "ALMACENISTA")
                        .requestMatchers(
                                "/nuevo-producto",
                                "/guardar-producto",
                                "/entrada-producto/**",
                                "guardar-entrada",
                                "/cambiar-estatus/**",
                                "/historial")
                        .hasRole("ADMINISTRADOR")
                        .requestMatchers(
                                "/salida-producto/**",
                                "/guardar-salida")
                        .hasRole("ALMACENISTA")
                        .anyRequest().authenticated()) // cualquier otra requiere login
                .formLogin(form -> form.defaultSuccessUrl("/inventario", true) // página después del login
                        .permitAll()) // activar formulario login
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll());

        return http.build();
    }
}
