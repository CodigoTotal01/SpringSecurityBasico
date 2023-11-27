package com.example.config;

import jakarta.websocket.Session;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

// Configurando la segyuridad de la aplicación
@Configuration
@EnableWebSecurity
public class SecurityConfig {

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
            //Parametros y metodos de configuracion de la seguridad
            return httpSecurity
                    .authorizeHttpRequests()
                    .requestMatchers("/v1/index2").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .formLogin().permitAll() // Acceso al formulario que vimos al inicio
                    .and()
                    .httpBasic() //Autenticacion basic, cuando la seguridad no es tan regurosa o importante, credenciales en el header de la autneticacion
                    .and()
                    .build();
        }

    //Segunda configuracion
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
//        //Parametros y metodos de configuracion de la seguridad
//        return httpSecurity
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/v1/index2").permitAll()
//                        .anyRequest().authenticated()
//                )
//                .formLogin()
//                .successHandler(successHandler())
//                .permitAll() // Acceso al formulario que vimos al inicio
//                .and()
//                .sessionManagement()
//                //Podemos tener cierto datos guardados
//                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS) // ALLWAYS - IF-REQUIRED - NEVER - STATELESS(No guarda ninugn tipo de datos)
//                .invalidSessionUrl("/login")
//                .maximumSessions(1)
//                .expiredUrl("/login")
//                .sessionRegistry(sessionRegistry()) //Rastro de los datos de la sesion
//                .and()
//                .sessionFixation()
//                    .migrateSession()// migrateSession - newSession - none
//                .and()
//                .httpBasic() //Autenticacion basic, cuando la seguridad no es tan regurosa o importante
//                .and()
//                .build();
//    }

    //Nos redirecciona a la pagina de inicion
    public AuthenticationSuccessHandler successHandler() {
        return (request, response, authentication) -> {
            response.sendRedirect("/v1/index");
        };
    }

    @Bean
    public SessionRegistry sessionRegistry (){
        return new SessionRegistryImpl();
    }
}
