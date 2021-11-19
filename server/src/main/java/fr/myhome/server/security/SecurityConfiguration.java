package fr.myhome.server.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    public static final String[] PUBLIC_ENDPOINTS = new String[] {
        "/", "index.html", "/favicon.ico", "/*manifest.json", "workbox-*/*.js", "/*.js", "/*.png",
        "/static/**", "/*.svg", "/*.jpg"};
    
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.cors()
            .and()
            .csrf().disable()
            .formLogin().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .antMatcher("/**").authorizeRequests()
            .antMatchers(PUBLIC_ENDPOINTS).permitAll()
            .anyRequest().authenticated();

    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {

            @Override
            public void addCorsMappings(@NonNull final CorsRegistry registry) {
                registry.addMapping("/**")
                    .allowedMethods("*")
                    .allowedOrigins("http://localhost:9200", "http://localhost:3000")
                    .allowCredentials(true);
            }
        };
    }

    
}
