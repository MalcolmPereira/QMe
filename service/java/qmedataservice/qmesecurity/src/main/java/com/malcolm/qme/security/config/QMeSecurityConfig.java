/**
 * Name      : com.malcolm.qme.security.config.QMeSecurityConfig.java
 * Date      : 5/25/15
 * Developer : Malcolm
 * Purpose   : QMe Security Config
 */

package com.malcolm.qme.security.config;

import com.malcolm.qme.security.service.QMETokenAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

/**
 * @author malcolm
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true)
@ComponentScan({"com.malcolm.qme.security"})
public class QMeSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * QME API
     */
    private static final String QME_API = "/qme/api";

    /**
     * QME App Login
     */
    private static final String QME_LOGIN = "/qme/login";

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

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passcodeEncoder;

    @Autowired
    private QMETokenAuthenticationService qmeTokenAuthenticationService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passcodeEncoder);
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
            .headers()
        .and()
            .logout()
                .logoutUrl(QME_LOGOUT)
                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler())
                .invalidateHttpSession(true)
        .and()
             .csrf().disable()
             .authorizeRequests()
             .antMatchers(HttpMethod.OPTIONS, QME_OPTIONS).permitAll()
             .antMatchers(QME_API, QME_LOGIN, REGISTER_PATH, REGISTER_CONFIRM_PATH, RESET_FORGOT_PASSWORD_PATH, RESET_RESET_PASSWORD_PATH, QME_LOGOUT).permitAll()
             .anyRequest().authenticated()
        .and()
            .addFilterBefore(new QMeLoginFilter(QME_LOGIN, userDetailsService, qmeTokenAuthenticationService, authenticationManager()), UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(new QMeTokenFilter(qmeTokenAuthenticationService), UsernamePasswordAuthenticationFilter.class)
        ;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }
}
