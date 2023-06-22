package com.se.rental.config;


import com.se.rental.filter.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    public static final String CUSTOMER = "CUSTOMER";
    public static final String ADMIN = "ADMIN";
    public static final String OWNER = "OWNER";
    private final UserDetailsService userDetailsService;
    private final JwtFilter jwtFilter;

    @Bean
    public UserDetailsService userDetailsSvc() {
        return userDetailsService;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .cors().and().csrf().disable()
                .authorizeRequests()

                .requestMatchers("/api/v1/authenticate").permitAll()
                .requestMatchers("/**").permitAll()
//                .requestMatchers(HttpMethod.POST, "/api/v1/user", "/api/v1/user/", "/api/v1/authenticate/user", "/api/v1/property", "/api/v1/authenticate",
//                        "/api/v1/authenticate/user/otp/**").permitAll()
//                .requestMatchers(HttpMethod.PUT, "/api/v1/property/{id}/clicks", "/api/v1/property/{id}/clicks/").permitAll()
//                .requestMatchers(HttpMethod.GET, "/api/v1/property", "/api/v1/property/").permitAll()
//                .requestMatchers(HttpMethod.POST, "/api/v1/offers/**").hasAnyRole(CUSTOMER, OWNER)
//                .requestMatchers("/api/v1/favourite/**").hasRole(CUSTOMER)
//                .requestMatchers(HttpMethod.PUT, "/api/v1/offers/**").hasAnyRole(CUSTOMER, OWNER)
//                .requestMatchers(HttpMethod.GET, "/api/v1/offers/**").hasRole(CUSTOMER)
//                .requestMatchers(HttpMethod.PUT, "/api/v1/property", "/api/v1/property/", "/api/v1/property/{id}").permitAll()
//                .requestMatchers(HttpMethod.DELETE, "/api/v1/property", "/api/v1/property/{id}").hasAnyRole(CUSTOMER, OWNER)
//                .requestMatchers("/api/v1/admin/**").hasRole(ADMIN)
//                .requestMatchers(HttpMethod.PUT, "/api/v1/admin/**").hasRole(ADMIN)
//                .requestMatchers("/api/v1/dashboard/**").hasAnyRole(ADMIN, CUSTOMER, OWNER)
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();

    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {

        return (web) -> web.ignoring().requestMatchers("/images/**", "/js/**", "/webjars/**", "/static/**");
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsSvc());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }
}
