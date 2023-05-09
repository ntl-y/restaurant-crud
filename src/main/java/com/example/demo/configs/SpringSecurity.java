package com.example.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests((authorize) ->
                        authorize.requestMatchers("/register/**").permitAll()
                                .requestMatchers("/").permitAll()
                                .requestMatchers("/managers/**").hasAnyRole("USER", "ADMIN")
                                .requestMatchers("/managers_new").hasAnyRole("USER", "ADMIN")
                                .requestMatchers("/managers_edit/**").hasAnyRole("USER", "ADMIN")
                                .requestMatchers("/managers_delete/**").hasAnyRole("USER", "ADMIN")
                                .requestMatchers("/foods/**").hasAnyRole("USER", "ADMIN")
                                .requestMatchers("/foods_new").hasAnyRole("USER", "ADMIN")
                                .requestMatchers("/foods_edit/**").hasAnyRole("USER", "ADMIN")
                                .requestMatchers("/foods_delete/**").hasAnyRole("USER", "ADMIN")
                                .requestMatchers("/foods_categories/**").hasAnyRole("USER", "ADMIN")
                                .requestMatchers("/foods_categories_new").hasAnyRole("USER", "ADMIN")
                                .requestMatchers("/foods_categories_edit/**").hasAnyRole("USER", "ADMIN")
                                .requestMatchers("/foods_categories_delete/**").hasAnyRole("USER", "ADMIN")
                                .requestMatchers("/menus/**").hasAnyRole("USER", "ADMIN")
                                .requestMatchers("/menus_new").hasAnyRole("USER", "ADMIN")
                                .requestMatchers("/menus_edit/**").hasAnyRole("USER", "ADMIN")
                                .requestMatchers("/menus_delete/**").hasAnyRole("USER", "ADMIN")
                                .requestMatchers("/restaurants/**").hasAnyRole("USER", "ADMIN")
                                .requestMatchers("/restaurants_new").hasAnyRole("USER", "ADMIN")
                                .requestMatchers("/restaurants_edit/**").hasAnyRole("USER", "ADMIN")
                                .requestMatchers("/restaurants_delete/**").hasAnyRole("USER", "ADMIN")
                                .requestMatchers("/users").hasRole("ADMIN")
                ).formLogin(
                        form -> form
                                .loginPage("/login")
                                .loginProcessingUrl("/login")
                                .defaultSuccessUrl("/users")
                                .permitAll()
                ).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll()
                );
        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}