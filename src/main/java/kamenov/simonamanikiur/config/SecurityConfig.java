package kamenov.simonamanikiur.config;

import kamenov.simonamanikiur.entity.enums.UserRoleEnum;
import kamenov.simonamanikiur.repos.UserRepository;
import kamenov.simonamanikiur.services.AppUserDetailsService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           SecurityContextRepository securityContextRepository
            , JwtAuthFilter jwtAuthFilter   ) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        authorizeHttpRequests ->
                                authorizeHttpRequests.
                                        requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                                        .permitAll().
                                        requestMatchers("/",
                                                "api/home/**",
                                                "/about",
                                                "/api/treatments/pedicure",
                                                "/delete/**",
                                                "/edit/**",
                                                "/users/login-error",
                                                "/api/auth/login",
                                                "/api/auth/register",
                                                "api/treatments/**",
                                                "/api/auth/**",
                                                "/api/appointments"

                                        )
                                        .permitAll()
                                        .requestMatchers("/api/appointments/**").hasRole(UserRoleEnum.ADMIN.name())
                                        .requestMatchers("/cart/add/**").authenticated()

//                                        .anyRequest().authenticated().
                                        .requestMatchers("/error").permitAll().
                                        requestMatchers("/api/appointments/**").hasRole(UserRoleEnum.ADMIN.name()).
                                        requestMatchers("/pages/all").hasRole(UserRoleEnum.USER.name()).
                                        anyRequest()
                                        .authenticated()


//                                       requestMatchers("/**").authenticated()
                )
                .formLogin(
                        (formLogin) ->
                                formLogin.
                                        loginPage("/users/login").
                                        usernameParameter(
                                                UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY).
                                        passwordParameter(
                                                UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY).
                                        defaultSuccessUrl("/", true).
                                        failureForwardUrl("/users/login-error")

                )
                .logout((logout) ->
                        logout.logoutUrl("/users/logout").
                                logoutSuccessUrl("/").//go to homepage after logout
                                invalidateHttpSession(true)
                                .deleteCookies("JSESSIONID")


                )
//                .rememberMe(httpSecurityRememberMeConfigurer -> {
//                            httpSecurityRememberMeConfigurer.key(rememberMeKey)
//                                    .rememberMeParameter("rememberMe")
//                                    .rememberMeCookieName("rememberMe")
//                                    .tokenValiditySeconds(10000);
//                        }
//                )

                .securityContext(
                        securityContext -> securityContext.
                                securityContextRepository(securityContextRepository)

                )
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(jwtAuthFilter,UsernamePasswordAuthenticationFilter.class)

        ;

        return http.build();
    }
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                        .allowedOrigins("http://localhost:8000")
//                        .allowedMethods("GET", "POST", "PUT", "DELETE");
//            }
//        };
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new AppUserDetailsService(userRepository);
    }

    @Bean
    public SecurityContextRepository securityContextRepository() {
        return new DelegatingSecurityContextRepository(
                new RequestAttributeSecurityContextRepository(),
                new HttpSessionSecurityContextRepository()
        );
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
