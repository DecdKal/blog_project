package web_project.blog.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import web_project.blog.repository.UserRepository;
import web_project.blog.service.impl.PUserDetailsService;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(
                        authorizeRequests ->
                                authorizeRequests
                                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                                        .requestMatchers("/", "/users/login", "/users/register", "/error").permitAll()
                                        .requestMatchers("/tags/add", "/categories/add").hasAuthority("ROLE_ADMIN")
                                        //.requestMatchers("/posts/create", "/posts/update").hasAnyAuthority()
                                        .anyRequest()
                                        .authenticated()
                )
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/users/login")
                                .usernameParameter("email")
                                .passwordParameter("password")
                                .defaultSuccessUrl("/", true)
                                .failureForwardUrl("/users/login-error")
                )
                .logout(
                        logout ->
                                logout
                                        .logoutUrl("/users/logout")
                                        .logoutSuccessUrl("/")
                                        .invalidateHttpSession(true)
                )
                .build();
    }

    @Bean
    public PUserDetailsService userDetailsService(UserRepository userRepository) {
        return new PUserDetailsService(userRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return Pbkdf2PasswordEncoder
                .defaultsForSpringSecurity_v5_8();
    }
}
