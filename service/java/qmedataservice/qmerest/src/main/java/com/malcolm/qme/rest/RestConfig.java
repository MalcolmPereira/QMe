/**
 * Name      : com.malcolm.qme.rest.RestConfig.java
 * Date      : 5/20/15
 * Developer : Malcolm
 * Purpose   : Configuration for REST MVC
 */

package com.malcolm.qme.rest;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author malcolm
 */
@EnableWebMvc
@Configuration
@ComponentScan({"com.malcolm.qme.rest"})
public class RestConfig {
}
