package com.project.hotel_management.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class hotelSecurity {
	
	@Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        
        jdbcUserDetailsManager.setUsersByUsernameQuery(
            "SELECT username, password, enabled FROM users WHERE username=?");
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
            "SELECT username, authority FROM authorities WHERE username=?");

        return jdbcUserDetailsManager;
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer -> configurer
        		// get for all
                .requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole("ADMIN", "GUEST", "RECEPTIONIST", "MANAGER")
        		
                // get hotel for all
                .requestMatchers(HttpMethod.GET, "/api/hotels/**").permitAll()

                // for guest 
                .requestMatchers(HttpMethod.GET, "/api/rooms/**").hasRole("GUEST")
                .requestMatchers(HttpMethod.GET, "/api/bookings/my/**").hasRole("GUEST")
               

                // for receptionist 
                .requestMatchers(HttpMethod.GET, "/api/bookings/**").hasRole("RECEPTIONIST")
                .requestMatchers(HttpMethod.GET, "/api/guests/**").hasRole("RECEPTIONIST")
                .requestMatchers(HttpMethod.PUT, "/api/guests/**").hasRole("RECEPTIONIST")
                .requestMatchers(HttpMethod.POST, "/api/guests/**").hasRole("RECEPTIONIST")
                .requestMatchers(HttpMethod.POST, "/api/bookings/**").hasAnyRole("RECEPTIONIST","MANAGER")
                
                // for manager 
                .requestMatchers(HttpMethod.POST, "/api/rooms/**").hasRole("MANAGER")
                .requestMatchers(HttpMethod.PUT, "/api/rooms/**").hasRole("MANAGER")
                .requestMatchers(HttpMethod.PUT, "/api/bookings/**").hasRole("MANAGER")

                // for admin 
                .requestMatchers(HttpMethod.DELETE, "/api/bookings/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/rooms/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/hotels/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/hotels/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/hotels/**").hasRole("ADMIN"))

            .httpBasic(Customizer.withDefaults())
            .csrf(csrf -> csrf.disable());

            return http.build();
    	
//    	http.authorizeHttpRequests(configurer -> configurer.anyRequest().permitAll())
//        .httpBasic(Customizer.withDefaults())
//        .csrf(csrf -> csrf.disable());
//    	return http.build();
    }
}
