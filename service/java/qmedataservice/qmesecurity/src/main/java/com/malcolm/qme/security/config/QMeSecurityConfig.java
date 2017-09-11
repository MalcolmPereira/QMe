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
import org.springframework.core.env.Environment;
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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * QME Authentication Header
     */
    private static final String QME_AUTH_HEADER_NAME = "QME-AUTH-TOKEN";

    /**
     * CORS Filter Path
     */
    private static final String CORS_PATH = "/**";

    /**
     * Supported Client
     */
    private static final List<String> supportedClients = new ArrayList<>();
    static{
        supportedClients.add("http://localhost:8000");
        supportedClients.add("http://localhost:4200");
        supportedClients.add("http://127.0.0.1:8000");
        supportedClients.add("http://127.0.0.1:4200");
        supportedClients.add("http://10.85.22.86:8000");
        supportedClients.add("http://10.85.22.86:4200");
        supportedClients.add("http://10.85.22.37:8000");
        supportedClients.add("http://10.85.22.37:4200");
    }

    /**
     * Control Header Names
     */
    private static final List<String> supportedHeaders = new ArrayList<>();
    static{
        supportedHeaders.add("content-type");
        supportedHeaders.add("x-requested-with");
        supportedHeaders.add("accept,origin");
        supportedHeaders.add("Access-Control-Request-Method");
        supportedHeaders.add("Access-Control-Request-Headers");
        supportedHeaders.add("Authorization");
        supportedHeaders.add(QME_AUTH_HEADER_NAME);
    }

    /**
     * Supported Msthod Name
     */
    private static final List<String> supportedMethods = new ArrayList<>();
    static {
        supportedMethods.add("GET");
        supportedMethods.add("POST");
        supportedMethods.add("PUT");
        supportedMethods.add("DELETE");
        supportedMethods.add("OPTIONS");
    }

    /**
     * Maximum Age
     */
    private static final Long MAX_AGE = 3600L;

    /**
     * Dev Mode allow request from all origins
     */
    private static final String DEV_MODE = "sys.dev.mode";

    /**
     * Dev model allow all
     */
    private static final String DEV_MODE_ALL_ALL = "*";

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passcodeEncoder;

    @Autowired
    private QMETokenAuthenticationService qmeTokenAuthenticationService;

    @Autowired
    private Environment environment;

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
            .addFilter(new CorsFilter(initCORSConfig()))
            .addFilterBefore(new QMeLoginFilter(QME_LOGIN, userDetailsService, qmeTokenAuthenticationService, authenticationManager()), UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(new QMeTokenFilter(qmeTokenAuthenticationService), UsernamePasswordAuthenticationFilter.class)

        ;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }

    /**
     * Initialized CORS Configuration
     * @return
     */
    private CorsConfigurationSource initCORSConfig() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        for (String origin : supportedClients) {
            config.addAllowedOrigin(origin);
        }
        for (String header: supportedHeaders) {
            config.addAllowedHeader(header);
        }
        for (String method: supportedMethods) {
            config.addAllowedMethod(method);
        }
        if(environment != null && environment.getProperty(DEV_MODE) != null && Boolean.valueOf(environment.getProperty(DEV_MODE).toString())){
            config.addAllowedOrigin(DEV_MODE_ALL_ALL);
        }
        config.setMaxAge(MAX_AGE);
        config.setAllowCredentials(Boolean.TRUE);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration(CORS_PATH, config);
        return source;
    }
}
