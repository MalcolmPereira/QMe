/**
 * Name      : com.malcolm.qme.security.config.QMeSecurityConfig.java
 * Date      : 5/25/15
 * Developer : Malcolm
 * Purpose   : QMe Security Config
 */

package com.malcolm.qme.security.config;

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
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author malcolm
 */
@EnableWebSecurity
@Configuration
@ComponentScan({"com.malcolm.qme.security"})
public class QMeSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * QMe Options for Pre-Flight Requests
     */
    private static final String QME_OPTIONS = "/qme/**";

    /**
     * Register Path
     */
    private static final String REGISTER_PATH = "/qme/user/stage/**";

    /**
     * Register Confirmation Path
     */
    private static final String REGISTER_CONFIRM_PATH = "/qme/user/confirm";


    /**
     * Forgot Password Path
     */
    private static final String RESET_FORGOT_PASSWORD_PATH = "/qme/user/reset/forgotpassword/**";

    /**
     * Reset Password Path
     */
    private static final String RESET_RESET_PASSWORD_PATH = "/qme/user/reset/resetpassword/**";

    /**
     * QMe Logout
     */
    private static final String QME_LOGOUT = "/qme/logout";

    /**
     * QMe CRF Token
     */
    private static final String QME_CRF_TOKEN_NAME = "XSRF-TOKEN";

    /**
     * QMe CRF Token Header Name
     */
    private static final String QME_CRF_TOKEN_HEADER_NAME = "X-XSRF-TOKEN";

    /**
     * QMe CSRF Token Path
     */
    private static final String QME_TOKEN_PATH = "/qme/";

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

        http
            .httpBasic()
        .and()
            .logout()
                .logoutUrl(QME_LOGOUT)
                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler())
                .invalidateHttpSession(true)
                .deleteCookies(QME_CRF_TOKEN_NAME)
         .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, QME_OPTIONS).permitAll()
                .antMatchers(REGISTER_PATH, REGISTER_CONFIRM_PATH, RESET_FORGOT_PASSWORD_PATH, RESET_RESET_PASSWORD_PATH, QME_LOGOUT).permitAll().anyRequest()
                .authenticated()
         .and()
                .csrf()
                .ignoringAntMatchers(REGISTER_PATH, REGISTER_CONFIRM_PATH, RESET_FORGOT_PASSWORD_PATH, RESET_RESET_PASSWORD_PATH,QME_LOGOUT)
                .csrfTokenRepository(qmeCSRFTokenRepository())
         .and()
                .addFilterAfter(qmeCSRFilter(), CsrfFilter.class)

         ;
    }

    /**
     * QMe CSRF Filter
     * @return Filter
     */
    private Filter qmeCSRFilter() {

        return new OncePerRequestFilter() {
            @Override
            protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
                CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
                if (csrf != null) {
                    Cookie cookie = WebUtils.getCookie(request, QME_CRF_TOKEN_NAME);
                    String token = csrf.getToken();
                    if (cookie == null || token != null && !token.equals(cookie.getValue())) {
                        cookie = new Cookie(QME_CRF_TOKEN_NAME, token);
                        cookie.setSecure(true);
                        cookie.setPath(QME_TOKEN_PATH);
                        response.addCookie(cookie);
                    }
                }
                filterChain.doFilter(request, response);
            }
        };
    }

    /**
     * QME CSRF Token Repository
     * @return
     */
    private CsrfTokenRepository qmeCSRFTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setHeaderName(QME_CRF_TOKEN_HEADER_NAME);
        return repository;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(-3);
    }
}
