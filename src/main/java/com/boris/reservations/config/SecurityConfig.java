package com.boris.reservations.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.boris.reservations.controllers.RestAuthenticationEntryPoint;
import com.boris.reservations.security.JwtAuthenticationProvider;
import com.boris.reservations.security.JwtTokenAuthenticationProcessingFilter;
import com.boris.reservations.security.TokenExtractor;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    public static final String JWT_TOKEN_HEADER_PARAM = "X-Authorization";
    public static final String RESERVATIONS_ENTRY_POINT = "/reservations/";
    public static final String NEW_RESERVATIONS = "/reservations/new/**";
    public static final String CONFIRMED_RESERVATIONS = "/reservations/confirmed/**";
    
    
    @Autowired private TokenExtractor tokenExtractor;
    
    @Autowired private AuthenticationManager authenticationManager;
    @Autowired private JwtAuthenticationProvider jwtAuthenticationProvider;
    
    @Autowired private RestAuthenticationEntryPoint authenticationEntryPoint;
    @Autowired private AuthenticationFailureHandler failureHandler;


    protected JwtTokenAuthenticationProcessingFilter buildJwtTokenAuthenticationProcessingFilter() throws Exception {
        RequestMatcher matcher = new OrRequestMatcher(new AntPathRequestMatcher(RESERVATIONS_ENTRY_POINT), new AntPathRequestMatcher(NEW_RESERVATIONS), new AntPathRequestMatcher(CONFIRMED_RESERVATIONS));
        JwtTokenAuthenticationProcessingFilter filter 
            = new JwtTokenAuthenticationProcessingFilter(failureHandler, tokenExtractor, matcher);
        filter.setAuthenticationManager(this.authenticationManager);
        return filter;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(jwtAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .csrf().disable() // We don't need CSRF for JWT based authentication
        .exceptionHandling()
        .authenticationEntryPoint(this.authenticationEntryPoint)
        .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
            .authorizeRequests()
                .antMatchers(RESERVATIONS_ENTRY_POINT, NEW_RESERVATIONS, CONFIRMED_RESERVATIONS).authenticated() // Protected API End-points
        .and()
            .addFilterBefore(buildJwtTokenAuthenticationProcessingFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
