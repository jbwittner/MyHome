package fr.myhome.server.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    public static final String[] PUBLIC_ENDPOINTS = new String[] {
        "/", "index.html", "/favicon.ico", "/*manifest.json", "workbox-*/*.js", "/*.js", "/*.png",
        "/static/**", "/*.svg", "/*.jpg", "/user/registration", "/authentication/*"};
    
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
    
}
