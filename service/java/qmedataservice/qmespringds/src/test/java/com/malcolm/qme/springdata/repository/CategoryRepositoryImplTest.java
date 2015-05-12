/**
 * Name      : com.malcolm.qme.springdata.repository.CategoryRepositoryImplTest.java
 * Date      : 5/12/2015
 * Developer : Malcolm
 * Purpose   : QMe Category Repository Implementation Test Cases
 */
package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.springdata.config.QMeSpringDataJPAConfig;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

/**
 * @Author: malcolm
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {QMeSpringDataJPAConfig.class})
@TestExecutionListeners(listeners = {
        DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class
})
public class CategoryRepositoryImplTest {
}
