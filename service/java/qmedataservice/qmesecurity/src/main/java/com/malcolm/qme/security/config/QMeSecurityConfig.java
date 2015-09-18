/**
 * Name      : com.malcolm.qme.security.config.QMeSecurityConfig.java
 * Date      : 5/25/15
 * Developer : Malcolm
 * Purpose   : QMe Security Config
 */

package com.malcolm.qme.security.config;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.savedrequest.NullRequestCache;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import java.io.IOException;

/**
 * @author malcolm
 */
@EnableWebSecurity
@Configuration
@ComponentScan({"com.malcolm.qme.security"})
public class QMeSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String QME_OPTIONS = "/qme/**";

    private static final String REGISTER_PATH = "/qme/user/register";

    private static final String RESET_FORGOT_PASSWORD_PATH = "/qme/user/reset/forgotpassword/**";

    private static final String RESET_RESET_PASSWORD_PATH = "/qme/user/reset/resetpassword/**";

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passcodeEncoder;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passcodeEncoder);
    }

    //TODO: Fix Basic Authentication (Need to have OAuth here)
    @Override
    protected void configure(final HttpSecurity http) throws Exception {

        //http.csrf().requireCsrfProtectionMatcher(new AntPathRequestMatcher(REGISTER_PATH)).disable();
        //http.csrf().requireCsrfProtectionMatcher(new AntPathRequestMatcher(RESET_FORGOT_PASSWORD_PATH)).disable();
        //http.csrf().requireCsrfProtectionMatcher(new AntPathRequestMatcher(RESET_RESET_PASSWORD_PATH)).disable();

        http
            .httpBasic()
            .and()
            .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, QME_OPTIONS).permitAll()
                .antMatchers(HttpMethod.PUT, QME_OPTIONS).permitAll()
                .antMatchers(REGISTER_PATH, RESET_FORGOT_PASSWORD_PATH, RESET_RESET_PASSWORD_PATH).permitAll().anyRequest()
                .authenticated()
                .and()
                .csrf()
                .ignoringAntMatchers(REGISTER_PATH, RESET_FORGOT_PASSWORD_PATH, RESET_RESET_PASSWORD_PATH)
                .csrfTokenRepository(csrfTokenRepository())
                .and()
                .addFilterAfter(csrfHeaderFilter(), CsrfFilter.class)
            ;

        /*
        http.csrf().requireCsrfProtectionMatcher(new AntPathRequestMatcher(REGISTER_PATH)).disable();

        http.requestCache().requestCache(new NullRequestCache());

        http.authorizeRequests()
                .antMatchers(REGISTER_PATH).permitAll()
                .antMatchers(HttpMethod.OPTIONS, QME_OPTIONS).permitAll()
                .antMatchers(HttpMethod.OPTIONS, RESET_FORGOT_PASSWORD_PATH).permitAll()
                .antMatchers(HttpMethod.OPTIONS, RESET_RESET_PASSWORD_PATH).permitAll()
                .antMatchers(HttpMethod.PUT, RESET_FORGOT_PASSWORD_PATH).permitAll()
                .antMatchers(HttpMethod.PUT, RESET_RESET_PASSWORD_PATH).permitAll()
                .anyRequest().authenticated().and().httpBasic()
                .and().logout().permitAll()

        ;
        */

    }

    private Filter csrfHeaderFilter() {
        return new OncePerRequestFilter() {
            @Override
            protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
                CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
                if (csrf != null) {
                    Cookie cookie = WebUtils.getCookie(request, "XSRF-TOKEN");
                    String token = csrf.getToken();
                    if (cookie == null || token != null && !token.equals(cookie.getValue())) {
                        cookie = new Cookie("XSRF-TOKEN", token);
                        cookie.setPath("/qme/");
                        response.addCookie(cookie);
                    }
                }
                filterChain.doFilter(request, response);
            }
        };
    }

    private CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setHeaderName("X-XSRF-TOKEN");
        return repository;
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(-3);
    }
}
