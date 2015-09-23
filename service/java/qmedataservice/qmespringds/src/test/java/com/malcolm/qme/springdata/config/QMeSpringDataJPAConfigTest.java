/**
 * Name      : com.malcolm.qme.springdata.config.QMeSpringDataJPAConfigTest.java
 * Date      : 9/23/15
 * Developer : Malcolm
 * Purpose   : QMe Spring Data JPA Configuration Test
 */
package com.malcolm.qme.springdata.config;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author malcolm
 */
@RunWith(MockitoJUnitRunner.class)
public class QMeSpringDataJPAConfigTest {

    @Mock
    private Environment environment;

    @InjectMocks
    private  QMeSpringDataJPAConfig qMeSpringDataJPAConfig;

    @Before
    public void initMocks(){
        qMeSpringDataJPAConfig = new QMeSpringDataJPAConfig();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDataSource() throws Exception {
        when(environment.getRequiredProperty("dataSource.driver")).thenReturn("com.mysql.jdbc.Driver");
        when(environment.getRequiredProperty("dataSource.url")).thenReturn("jdbc:mysql://localhost:3306/QME");
        when(environment.getRequiredProperty("dataSource.username")).thenReturn("someuser");
        when(environment.getRequiredProperty("dataSource.password")).thenReturn("somepassword");
        DataSource dataSource = qMeSpringDataJPAConfig.dataSource();
        verify(environment).getRequiredProperty("dataSource.driver");
        verify(environment).getRequiredProperty("dataSource.url");
        verify(environment).getRequiredProperty("dataSource.username");
        verify(environment).getRequiredProperty("dataSource.password");
        assertNotNull(dataSource);
    }

    @Test
    public void testEntityManagerFactory() throws Exception {
        when(environment.getRequiredProperty("dataSource.driver")).thenReturn("com.mysql.jdbc.Driver");
        when(environment.getRequiredProperty("dataSource.url")).thenReturn("jdbc:mysql://localhost:3306/QME");
        when(environment.getRequiredProperty("dataSource.username")).thenReturn("someuser");
        when(environment.getRequiredProperty("dataSource.password")).thenReturn("somepassword");
        when(environment.getProperty("hibernate.dialect")).thenReturn("org.hibernate.dialect.MySQLDialect");
        when(environment.getProperty("hibernate.show_sql")).thenReturn("true");
        when(environment.getProperty("org.hibernate.cacheable")).thenReturn("false");
        when(environment.getProperty("org.hibernate.cacheMode")).thenReturn("REFRESH");
        when(environment.getProperty("hibernate.cache.use_second_level_cache")).thenReturn("false");
        when(environment.getProperty("hibernate.cache.use_query_cache")).thenReturn("false");
        when(environment.getProperty("javax.persistence.sharedCache.mode")).thenReturn("NONE");
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = qMeSpringDataJPAConfig.entityManagerFactory();
        verify(environment).getRequiredProperty("dataSource.driver");
        verify(environment).getRequiredProperty("dataSource.url");
        verify(environment).getRequiredProperty("dataSource.username");
        verify(environment).getRequiredProperty("dataSource.password");
        verify(environment).getProperty("hibernate.dialect");
        verify(environment).getProperty("hibernate.show_sql");
        verify(environment).getProperty("org.hibernate.cacheable");
        verify(environment).getProperty("org.hibernate.cacheMode");
        verify(environment).getProperty("hibernate.cache.use_second_level_cache");
        verify(environment).getProperty("hibernate.cache.use_query_cache");
        verify(environment).getProperty("javax.persistence.sharedCache.mode");
        assertNotNull(localContainerEntityManagerFactoryBean);
    }

    @Test
    public void testTransactionManager() throws Exception {
        when(environment.getRequiredProperty("dataSource.driver")).thenReturn("com.mysql.jdbc.Driver");
        when(environment.getRequiredProperty("dataSource.url")).thenReturn("jdbc:mysql://localhost:3306/QME");
        when(environment.getRequiredProperty("dataSource.username")).thenReturn("someuser");
        when(environment.getRequiredProperty("dataSource.password")).thenReturn("somepassword");
        when(environment.getProperty("hibernate.dialect")).thenReturn("org.hibernate.dialect.MySQLDialect");
        when(environment.getProperty("hibernate.show_sql")).thenReturn("true");
        when(environment.getProperty("org.hibernate.cacheable")).thenReturn("false");
        when(environment.getProperty("org.hibernate.cacheMode")).thenReturn("REFRESH");
        when(environment.getProperty("hibernate.cache.use_second_level_cache")).thenReturn("false");
        when(environment.getProperty("hibernate.cache.use_query_cache")).thenReturn("false");
        when(environment.getProperty("javax.persistence.sharedCache.mode")).thenReturn("NONE");
        JpaTransactionManager jpaTransactionManager = qMeSpringDataJPAConfig.transactionManager();
        verify(environment).getRequiredProperty("dataSource.driver");
        verify(environment).getRequiredProperty("dataSource.url");
        verify(environment).getRequiredProperty("dataSource.username");
        verify(environment).getRequiredProperty("dataSource.password");
        verify(environment).getProperty("hibernate.dialect");
        verify(environment).getProperty("hibernate.show_sql");
        verify(environment).getProperty("org.hibernate.cacheable");
        verify(environment).getProperty("org.hibernate.cacheMode");
        verify(environment).getProperty("hibernate.cache.use_second_level_cache");
        verify(environment).getProperty("hibernate.cache.use_query_cache");
        verify(environment).getProperty("javax.persistence.sharedCache.mode");
        assertNotNull(jpaTransactionManager);

    }

    @Test
    public void testExceptionTranslation() throws Exception {
        assertNotNull(qMeSpringDataJPAConfig.exceptionTranslation());
    }
}