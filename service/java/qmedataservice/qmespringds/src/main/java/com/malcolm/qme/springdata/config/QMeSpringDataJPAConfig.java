/**
 * Name      : com.malcolm.qme.springdata.config.QMeSpringDataJPAConfig.java
 * Date      : 5/3/15
 * Developer : Malcolm
 * Purpose   : QMe Spring Data JPA Configuration
 */

package com.malcolm.qme.springdata.config;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @Author: malcolm
 */
@Configuration
@EnableTransactionManagement
@PropertySource({"classpath:qmespringds.properties"})
@EnableJpaRepositories("com.malcolm.qme.springdata.repository")
@ComponentScan({"com.malcolm.qme.springdata.entity"})
public class QMeSpringDataJPAConfig {

    /**
     * Entity Package
     */
    private static final String ENTITY_PACKAGE = "com.malcolm.qme.springdata.entity";

    @Autowired
    private Environment environment;

    /**
     * Get DataSource
     *
     * @return
     */
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));
        dataSource.setUrl(environment.getRequiredProperty(PROPERTY_NAME_DATABASE_URL));
        dataSource.setUsername(environment.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME));
        dataSource.setPassword(environment.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD));
        return dataSource;
    }

    /**
     * Entity Manager
     *
     * @return
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactoryBean.setPackagesToScan(ENTITY_PACKAGE);
        entityManagerFactoryBean.setJpaProperties(hibernateProperties());
        return entityManagerFactoryBean;
    }

    /**
     * Return Hibernate Properties
     *
     * @return
     */
    protected Properties hibernateProperties() {
        return new Properties() {
            private static final long serialVersionUID = 6067241928722086747L;
            {
                setProperty(PROPERTY_NAME_HIBERNATE_DIALECT,environment.getProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
                setProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL,environment.getProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));
                setProperty(PROPERTY_NAME_HIBERNATE_CACHEABLE,environment.getProperty(PROPERTY_NAME_HIBERNATE_CACHEABLE));
                setProperty(PROPERTY_NAME_HIBERNATE_CACHE_MODE,environment.getProperty(PROPERTY_NAME_HIBERNATE_CACHE_MODE));
                setProperty(PROPERTY_NAME_HIBERNATE_SECOND_LEVEL_CACHE,environment.getProperty(PROPERTY_NAME_HIBERNATE_SECOND_LEVEL_CACHE));
                setProperty(PROPERTY_NAME_HIBERNATE_QUERY_CACHE,environment.getProperty(PROPERTY_NAME_HIBERNATE_QUERY_CACHE));
                setProperty(PROPERTY_NAME_JAVAX_SHARED_CACHE,environment.getProperty(PROPERTY_NAME_JAVAX_SHARED_CACHE));
            }
        };
    }

    /**
     * Return the Transaction Manager
     *
     * @return
     */
    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }

   /**
     * Database Configuration Parameters
     */
    private static final String PROPERTY_NAME_DATABASE_DRIVER 		= "dataSource.driver";
    private static final String PROPERTY_NAME_DATABASE_URL 			= "dataSource.url";
    private static final String PROPERTY_NAME_DATABASE_USERNAME 	= "dataSource.username";
    private static final String PROPERTY_NAME_DATABASE_PASSWORD 	= "dataSource.password";
    private static final String PROPERTY_NAME_HIBERNATE_DIALECT     = "hibernate.dialect";
    private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL    = "hibernate.show_sql";
    private static final String PROPERTY_NAME_HIBERNATE_CACHEABLE   = "org.hibernate.cacheable";
    private static final String PROPERTY_NAME_HIBERNATE_CACHE_MODE  = "org.hibernate.cacheMode";
    private static final String PROPERTY_NAME_HIBERNATE_SECOND_LEVEL_CACHE  = "hibernate.cache.use_second_level_cache";
    private static final String PROPERTY_NAME_HIBERNATE_QUERY_CACHE  = "hibernate.cache.use_query_cache";
    private static final String PROPERTY_NAME_JAVAX_SHARED_CACHE  = "javax.persistence.sharedCache.mode";
}
