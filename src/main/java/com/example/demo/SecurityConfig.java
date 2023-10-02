package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	// User Creation
	@Bean
	public UserDetailsService userDetailsService(PasswordEncoder encoder) {

		// InMemoryUserDetailsManager
		UserDetails admin = User.withUsername("some_admin")
				.password(encoder.encode("1234"))
				.roles("ADMIN", "USER")
				.build();

		UserDetails user = User.withUsername("some_user")
				.password(encoder.encode("1234"))
				.roles("USER")
				.build();

		return new InMemoryUserDetailsManager(admin, user);
	}

	// Configuring HttpSecurity
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.
                authorizeHttpRequests(requests -> {
                        requests.requestMatchers(new AntPathRequestMatcher("/secure/**")).hasAuthority("MyAuthority");
                        requests.requestMatchers(new AntPathRequestMatcher("/"), new AntPathRequestMatcher("api/v1/users")).authenticated();
                        requests.requestMatchers(new AntPathRequestMatcher("/"), new AntPathRequestMatcher("api/v1/trivial/cards")).authenticated();
                        // allow all other requests
                        requests.anyRequest().anonymous();
                })
                
                .build();
    }

	// Password Encoding
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
