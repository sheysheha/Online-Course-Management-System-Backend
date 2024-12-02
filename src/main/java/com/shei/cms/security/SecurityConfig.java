//package com.shei.cms.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//
//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
////                .csrf(csrf -> csrf
////                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()) // Store CSRF tokens in cookies
////                )
//                .csrf(csrf -> csrf
//                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()) // CSRF token stored in cookies
//                        .requireCsrfProtectionMatcher(request -> false) // Disable CSRF protection for all endpoints (for testing)
//                )
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/api/auth/login").permitAll() // Public access to authentication APIs
////                        .requestMatchers("/api/courses/**").hasRole("INSTRUCTOR")
//                            .requestMatchers("/api/courses/**").permitAll()
//                                .requestMatchers("/api/feedbacks").permitAll()
//                                .requestMatchers("/api/auth/users").permitAll()
//                        .anyRequest().authenticated() // Authentication required for other requests
//                )
//                .httpBasic(Customizer.withDefaults()); // Enable HTTP Basic authentication
//
//        return http.build();
//    }
//
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(passwordEncoder.encode("admin"))
//                .roles("ADMIN")
//                .build();
//        UserDetails instructor = User.builder()
//                .username("instructor")
//                .password(passwordEncoder.encode("instructor"))
//                .roles("INSTRUCTOR")
//                .build();
//
//        UserDetails student = User.builder()
//                .username("student")
//                .password(passwordEncoder.encode("student"))
//                .roles("STUDENT")
//                .build();
//
//        return new InMemoryUserDetailsManager(admin,instructor, student); // In-memory user management
//    }
//
//    @Bean
//    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
//        return http.getSharedObject(AuthenticationManagerBuilder.class).build();
//    }
//}
package com.shei.cms.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

// Uncomment these imports for JWT support later:
// import org.springframework.security.authentication.jwt.JwtAuthenticationFilter;
// import org.springframework.security.authentication.AuthenticationManager;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()) // CSRF token stored in cookies
                        .requireCsrfProtectionMatcher(request -> false) // Disable CSRF protection for all endpoints (for testing)
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/login").permitAll() // Public access to authentication APIs
                        .requestMatchers("/api/courses/**").permitAll() // Public access to courses API
                        .requestMatchers("/api/feedbacks").permitAll() // Public access to feedbacks API
                        .requestMatchers("/api/feedbacks/all").permitAll()
                        .requestMatchers("/api/auth/users").permitAll() // Public access to user management API
                        .anyRequest().authenticated() // Authentication required for other requests
                )
                .httpBasic(Customizer.withDefaults()); // Enable HTTP Basic authentication

        // Uncomment the below lines for JWT-based authentication later
        /*
        http
            .addFilter(new JwtAuthenticationFilter(authenticationManager())) // Add JWT filter for API authentication
            .authorizeRequests()
            .antMatchers("/api/auth/login").permitAll() // Allow public access to login API
            .anyRequest().authenticated(); // Secure other endpoints
        */

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Password encryption for security
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        // In-memory users for testing purposes
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .roles("ADMIN")
                .build();
        UserDetails instructor = User.builder()
                .username("instructor")
                .password(passwordEncoder.encode("instructor"))
                .roles("INSTRUCTOR")
                .build();
        UserDetails student = User.builder()
                .username("student")
                .password(passwordEncoder.encode("student"))
                .roles("STUDENT")
                .build();

        return new InMemoryUserDetailsManager(admin, instructor, student); // In-memory user store for testing
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class).build();
    }

    // Uncomment and configure the JWT filter when using JWT authentication
    /*
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(); // Customize the JWT filter as per your requirements
    }
    */
}
