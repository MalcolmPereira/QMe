/**
 * Name      : com.malcolm.qme.springdata.repository.AllDomainTests.java
 * Date      : 9/23/15
 * Developer : Malcolm
 * Purpose   : Domain Classes Tests
 */
package com.malcolm.qme.core.domain;

/**
 * @author Malcolm
 */

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
     AnswerOptionMediaTest.class,
     AnswerOptionTest.class,
     AnswerReferenceMediaTest.class,
     CategoryTest.class,
     MediaTypeTest.class,
     QuestionHitTest.class,
     QuestionTest.class,
     QuizQuestionTest.class,
     QuizTest.class,
     RoleTest.class,
     UserCategoryLikesTest.class,
     UserCategoryTest.class,
     UserQuestionLikesTest.class,
     UserQuizGameTest.class,
     UserQuizLikesTest.class,
     UserQuizTest.class,
     UserRoleTest.class,
     UserTest.class
})
public class AllDomainTests {
}
