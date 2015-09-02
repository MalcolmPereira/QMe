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
import org.springframework.security.web.savedrequest.NullRequestCache;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @author malcolm
 */
@EnableWebSecurity
@Configuration
@ComponentScan({"com.malcolm.qme.security"})
public class QMeSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String REGISTER_PATH = "/qme/user/register";

    private static final String RESET_PATH = "/qme/user/reset/**";

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

        http.csrf().requireCsrfProtectionMatcher(new AntPathRequestMatcher(REGISTER_PATH)).disable();

        http.requestCache().requestCache(new NullRequestCache());

        http.authorizeRequests()
                .antMatchers(REGISTER_PATH).permitAll()
                .antMatchers(RESET_PATH).permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/qme/**").permitAll()
                .anyRequest().authenticated().and().httpBasic()
                .and().logout().permitAll()

        ;

    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(-3);
    }
}
