package com.pizza.pizza.security;

import com.pizza.pizza.security.MyUserDetailsService;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;
import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

    @Bean
    public static NoOpPasswordEncoder getEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain getChain(HttpSecurity http) throws Exception {
        http
//                .authorizeRequests()
//                .requestMatchers("/h2-console/**").permitAll()
//                .requestMatchers(HttpMethod.GET, "/ index.html", "", "/open").permitAll()
//                .requestMatchers(HttpMethod.GET, "/ Logout.html", "/secure").authenticated()
//                .requestMatchers(HttpMethod.GET, "/admin/**").hasAnyRole("ADMIN")
//                .anyRequest().authenticated();
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers("/h2-console/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/cafe").authenticated()
                                .requestMatchers(HttpMethod.GET, "/cafes").hasAnyRole("ADMIN")
                                .anyRequest().authenticated()
                )

                //.and()
                .formLogin()
                .and()
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .httpBasic(Customizer.withDefaults())
                .logout() // POST /logout
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");
        return http.build();
    }

    @Bean
    public MyUserDetailsService userDetailsService() {
        return new MyUserDetailsService();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(getEncoder());
        provider.setUserDetailsService(userDetailsService());
        return provider;
    }
}

