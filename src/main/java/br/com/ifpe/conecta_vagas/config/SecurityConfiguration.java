package br.com.ifpe.conecta_vagas.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import br.com.ifpe.conecta_vagas.modelo.acesso.Perfil;
import br.com.ifpe.conecta_vagas.modelo.seguranca.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

        private final AuthenticationProvider authenticationProvider;
        private final JwtAuthenticationFilter jwtAuthenticationFilter;

        public SecurityConfiguration(JwtAuthenticationFilter jwtAuthenticationFilter,
                        AuthenticationProvider authenticationProvider) {
                this.authenticationProvider = authenticationProvider;
                this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

                http
                                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                                .csrf(c -> c.disable())
                                .authorizeHttpRequests(authorize -> authorize

                                                .requestMatchers(HttpMethod.POST, "/api/candidato").permitAll()
                                                .requestMatchers(HttpMethod.GET, "/api/candidato").permitAll()
                                                .requestMatchers(HttpMethod.POST, "/api/recrutador").permitAll()
                                                .requestMatchers(HttpMethod.POST, "/api/auth").permitAll()
                                                .requestMatchers(HttpMethod.GET, "/ws/**").permitAll()

                                                // Vagas
                                                .requestMatchers(HttpMethod.GET, "/api/vagas/*").permitAll()
                                                .requestMatchers(HttpMethod.GET, "/api/vagas").permitAll()

                                                .requestMatchers(HttpMethod.POST, "/api/vagas").hasAnyAuthority(
                                                                Perfil.ROLE_RECRUTADOR)

                                                .requestMatchers(HttpMethod.PUT, "/api/vagas/*").hasAnyAuthority(
                                                                Perfil.ROLE_RECRUTADOR)

                                                .requestMatchers(HttpMethod.DELETE, "/api/vagas/*").hasAnyAuthority(
                                                                Perfil.ROLE_RECRUTADOR)
                                                .requestMatchers(HttpMethod.GET, "/api/vagas/recrutador").hasAnyAuthority(
                                                                Perfil.ROLE_RECRUTADOR)
                                                .requestMatchers(HttpMethod.GET, "/api/recrutador/vagas/*").permitAll() // Obter
                                                                                                                        // todas
                                                                                                                        // as
                                                                                                                        // vagas
                                                                                                                        // de
                                                                                                                        // um
                                                                                                                        // recrutador

                                                // Chat
                                                .requestMatchers(HttpMethod.POST, "/api/chat").hasAnyAuthority(
                                                                Perfil.ROLE_CANDIDATO,
                                                                Perfil.ROLE_RECRUTADOR)

                                                .requestMatchers(HttpMethod.GET, "/api/chat/recrutador").hasAnyAuthority(
                                                                Perfil.ROLE_RECRUTADOR)

                                                .requestMatchers(HttpMethod.GET, "/api/chat/candidato").hasAnyAuthority(
                                                                Perfil.ROLE_CANDIDATO)

                                                .requestMatchers(HttpMethod.GET, "/api/chat/*").hasAnyAuthority(
                                                                Perfil.ROLE_CANDIDATO,
                                                                Perfil.ROLE_RECRUTADOR)
                                                .requestMatchers(HttpMethod.GET, "/api/chat/mensagem*").hasAnyAuthority(
                                                                Perfil.ROLE_CANDIDATO,
                                                                Perfil.ROLE_RECRUTADOR)
                                                .requestMatchers(HttpMethod.POST, "/api/mensagem").hasAnyAuthority(
                                                                Perfil.ROLE_CANDIDATO,
                                                                Perfil.ROLE_RECRUTADOR)


                                                // Candidato
                                                .requestMatchers(HttpMethod.GET, "/api/candidato").hasAnyAuthority(
                                                                Perfil.ROLE_CANDIDATO)

                                                .requestMatchers(HttpMethod.POST, "/api/candidato").hasAnyAuthority(
                                                                Perfil.ROLE_CANDIDATO)

                                                .requestMatchers(HttpMethod.PUT, "/api/candidato/*").hasAnyAuthority(
                                                                Perfil.ROLE_CANDIDATO)

                                                .requestMatchers(HttpMethod.DELETE, "/api/candidato/*").hasAnyAuthority(
                                                                Perfil.ROLE_CANDIDATO)

                                                // Candidato > Endereco
                                                .requestMatchers(HttpMethod.PUT, "/api/candidato/endereco/*")
                                                .hasAnyAuthority(
                                                                Perfil.ROLE_CANDIDATO)

                                                .requestMatchers(HttpMethod.POST, "/api/candidato/endereco")
                                                .hasAnyAuthority(
                                                                Perfil.ROLE_CANDIDATO)

                                                .requestMatchers(HttpMethod.DELETE, "/api/candidato/endereco/*")
                                                .hasAnyAuthority(
                                                                Perfil.ROLE_CANDIDATO)

                                                .requestMatchers(HttpMethod.GET, "/api/candidato/endereco")
                                                .hasAnyAuthority(
                                                                Perfil.ROLE_CANDIDATO)

                                                // Candidato > Formação Academica
                                                .requestMatchers(HttpMethod.PUT, "/api/candidato/formacao/*")
                                                .hasAnyAuthority(
                                                                Perfil.ROLE_CANDIDATO)

                                                .requestMatchers(HttpMethod.POST, "/api/candidato/formacao")
                                                .hasAnyAuthority(
                                                                Perfil.ROLE_CANDIDATO)

                                                .requestMatchers(HttpMethod.DELETE, "/api/candidato/formacao/*")
                                                .hasAnyAuthority(
                                                                Perfil.ROLE_CANDIDATO)

                                                .requestMatchers(HttpMethod.GET, "/api/candidato/formacao")
                                                .hasAnyAuthority(
                                                                Perfil.ROLE_CANDIDATO)

                                                // Recrutador
                                                .requestMatchers(HttpMethod.PUT, "/api/recrutador/*")
                                                .hasAnyAuthority(
                                                                Perfil.ROLE_RECRUTADOR)
                                                .requestMatchers(HttpMethod.DELETE, "/api/recrutador/*")
                                                .hasAnyAuthority(
                                                                Perfil.ROLE_RECRUTADOR)
                                                // Candidatura
                                                .requestMatchers(HttpMethod.GET, "/api/candidatura")
                                                .hasAnyAuthority(
                                                                Perfil.ROLE_CANDIDATO)
                                                .requestMatchers(HttpMethod.POST, "/api/candidatura/*")
                                                .hasAnyAuthority(
                                                                Perfil.ROLE_CANDIDATO)
                                                .requestMatchers(HttpMethod.DELETE, "/api/candidatura/*")
                                                .hasAnyAuthority(
                                                                Perfil.ROLE_CANDIDATO)
                                                                .requestMatchers(HttpMethod.DELETE, "/api/candidatura/recrutador/*")
                                                .hasAnyAuthority(
                                                                Perfil.ROLE_RECRUTADOR)

                                                .anyRequest().authenticated()

                                )
                                .sessionManagement((session) -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .authenticationProvider(authenticationProvider)
                                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

                return http.build();
        }

        public CorsConfigurationSource corsConfigurationSource() {

                CorsConfiguration configuration = new CorsConfiguration();

                configuration.setAllowedOrigins(Arrays.asList("http://192.168.1.40:3000", "http://192.168.1.33:3000", "http://10.195.107.67:3000"));
                configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
                configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
                configuration.setAllowCredentials(true);

                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                source.registerCorsConfiguration("/**", configuration);
                return source;
        }
}
