/**
 * Name      : com.malcolm.qme.rest.QMeApplication.java
 * Date      : 5/20/15
 * Developer : Malcolm
 * Purpose   : Spring Boot Core Starter Application
 */

package com.malcolm.qme.rest;

import com.malcolm.qme.rest.config.RestConfig;
import com.malcolm.qme.springdata.config.QMeSpringDataJPAConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * @author malcolm
 */
@SpringBootApplication
@EnableAutoConfiguration
@Import({RestConfig.class,QMeSpringDataJPAConfig.class})
public class QMeApplication {
    /**
     * Run the Application
     *
     * @param args Main Program Arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(QMeApplication.class, args);
    }
}
