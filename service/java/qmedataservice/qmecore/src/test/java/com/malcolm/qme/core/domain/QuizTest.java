/**
 * Name      : com.malcolm.qme.core.domain.QuizTest.java
 * Date      : 9/22/15
 * Developer : Malcolm
 * Purpose   : QMe Quiz Domain Class Test
 */
package com.malcolm.qme.core.domain;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

/**
 * @author Malcolm
 */
public class QuizTest {

    @Test
    public void testGetQuizID() throws Exception {
        Quiz quiz = new Quiz(1L, "Some Quiz Name", "Description", 1L, 1L, 1L, 3, LocalDateTime.now(), 1L, LocalDateTime.now(), 1L);
        assertThat(quiz.getQuizID(), equalTo(1L));
        quiz = new Quiz("Some Quiz Name", "Description", 1L, 3, 1L);
        assertThat(quiz.getQuizID(), equalTo(0L));
    }

    @Test
    public void testGetQuizName() throws Exception {
        Quiz quiz = new Quiz(1L, "Some Quiz Name", "Description", 1L, 1L, 1L, 3, LocalDateTime.now(), 1L, LocalDateTime.now(), 1L);
        assertThat(quiz.getQuizName(), equalTo("Some Quiz Name"));
        quiz = new Quiz("Some Quiz Name", "Description", 1L, 3, 1L);
        assertThat(quiz.getQuizName(), equalTo("Some Quiz Name"));
    }

    @Test
    public void testGetQuizDesc() throws Exception {
        Quiz quiz = new Quiz(1L, "Some Quiz Name", "Description", 1L, 1L, 1L, 3, LocalDateTime.now(), 1L, LocalDateTime.now(), 1L);
        assertThat(quiz.getQuizDesc(), equalTo("Description"));
        quiz = new Quiz("Some Quiz Name", "Description", 1L, 3, 1L);
        assertThat(quiz.getQuizDesc(), equalTo("Description"));
    }

    @Test
    public void testGetCategoryID() throws Exception {
        Quiz quiz = new Quiz(1L, "Some Quiz Name", "Description", 1L, 1L, 1L, 3, LocalDateTime.now(), 1L, LocalDateTime.now(), 1L);
        assertThat(quiz.getCategoryID(), equalTo(1L));
        quiz = new Quiz("Some Quiz Name", "Description", 1L, 3, 1L);
        assertThat(quiz.getCategoryID(), equalTo(1L));
    }

    @Test
    public void testGetLikes() throws Exception {
        Quiz quiz = new Quiz(1L, "Some Quiz Name", "Description", 1L, 1L, 1L, 3, LocalDateTime.now(), 1L, LocalDateTime.now(), 1L);
        assertThat(quiz.getLikes(), equalTo(1L));
        quiz = new Quiz("Some Quiz Name", "Description", 1L, 3, 1L);
        assertThat(quiz.getLikes(), equalTo(0L));
    }

    @Test
    public void testGetQuizHit() throws Exception {
        Quiz quiz = new Quiz(1L, "Some Quiz Name", "Description", 1L, 1L, 1L, 3, LocalDateTime.now(), 1L, LocalDateTime.now(), 1L);
        assertThat(quiz.getQuizHit(), equalTo(1L));
        quiz = new Quiz("Some Quiz Name", "Description", 1L, 3, 1L);
        assertThat(quiz.getQuizHit(), equalTo(0L));
    }

    @Test
    public void testGetQuizMaxAttempts() throws Exception {
        Quiz quiz = new Quiz(1L, "Some Quiz Name", "Description", 1L, 1L, 1L, 3, LocalDateTime.now(), 1L, LocalDateTime.now(), 1L);
        assertThat(quiz.getQuizMaxAttempts(), equalTo(3));
        quiz = new Quiz("Some Quiz Name", "Description", 1L, 3, 1L);
        assertThat(quiz.getQuizMaxAttempts(), equalTo(3));
    }

    @Test
    public void testGetQuizCreateDate() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        Quiz quiz = new Quiz(1L, "Some Quiz Name", "Description", 1L, 1L, 1L, 3, now, 1L, now, 1L);
        assertThat(quiz.getQuizCreateDate(), equalTo(now));
        quiz = new Quiz("Some Quiz Name", "Description", 1L, 3, 1L);
        assertNotNull(quiz.getQuizCreateDate());
    }

    @Test
    public void testGetCreateUserID() throws Exception {
        Quiz quiz = new Quiz(1L, "Some Quiz Name", "Description", 1L, 1L, 1L, 3, LocalDateTime.now(), 1L, LocalDateTime.now(), 1L);
        assertThat(quiz.getCreateUserID(), equalTo(1L));
        quiz = new Quiz("Some Quiz Name", "Description", 1L, 3, 1L);
        assertThat(quiz.getCreateUserID(), equalTo(1L));
    }

    @Test
    public void testGetQuizUpdateDate() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        Quiz quiz = new Quiz(1L, "Some Quiz Name", "Description", 1L, 1L, 1L, 3, now, 1L, now, 1L);
        assertThat(quiz.getQuizUpdateDate(), equalTo(now));
        quiz = new Quiz("Some Quiz Name", "Description", 1L, 3, 1L);
        assertNotNull(quiz.getQuizUpdateDate());
    }

    @Test
    public void testGetUpdateUserID() throws Exception {
        Quiz quiz = new Quiz(1L, "Some Quiz Name", "Description", 1L, 1L, 1L, 3, LocalDateTime.now(), 1L, LocalDateTime.now(), 1L);
        assertThat(quiz.getUpdateUserID(), equalTo(1L));
        quiz = new Quiz("Some Quiz Name", "Description", 1L, 3, 1L);
        assertThat(quiz.getUpdateUserID(), equalTo(0L));
    }

    @Test
    public void testEquals(){
        Quiz quiz1 = new Quiz(1L, "Some Quiz Name", "Description", 1L, 1L, 1L, 3, LocalDateTime.now(), 1L, LocalDateTime.now(), 1L);
        Quiz quiz2 = new Quiz(1L, "Some Quiz Name", "Description", 1L, 1L, 1L, 3, LocalDateTime.now(), 1L, LocalDateTime.now(), 1L);
        assertTrue(quiz1.equals(quiz2));
        assertTrue(quiz1.equals(quiz1));
        quiz1 = new Quiz(1L, "Some Quiz Name1", "Description", 1L, 1L, 1L, 3, LocalDateTime.now(), 1L, LocalDateTime.now(), 1L);
        quiz2 = new Quiz(1L, "Some Quiz Name2", "Description", 1L, 1L, 1L, 3, LocalDateTime.now(), 1L, LocalDateTime.now(), 1L);
        assertFalse(quiz1.equals(quiz2));
        quiz1 = new Quiz("Some Quiz Name", "Description", 1L, 3, 1L);
        quiz2 = new Quiz("Some Quiz Name", "Description", 1L, 3, 1L);
        assertTrue(quiz1.equals(quiz2));
        quiz1 = new Quiz("Some Quiz Name1", "Description", 1L, 3, 1L);
        quiz2 = new Quiz("Some Quiz Name2", "Description", 1L, 3, 1L);
        assertFalse(quiz1.equals(quiz2));
        assertFalse(quiz1.equals(null));
        assertFalse(quiz1.equals(""));
    }

    @Test
    public void testHashCode(){
        Quiz quiz1 = new Quiz(1L, "Some Quiz Name", "Description", 1L, 1L, 1L, 3, LocalDateTime.now(), 1L, LocalDateTime.now(), 1L);
        Quiz quiz2 = new Quiz(1L, "Some Quiz Name", "Description", 1L, 1L, 1L, 3, LocalDateTime.now(), 1L, LocalDateTime.now(), 1L);
        assertThat(quiz1.hashCode(),equalTo(quiz2.hashCode()));
        quiz1 = new Quiz(1L, "Some Quiz Name1", "Description", 1L, 1L, 1L, 3, LocalDateTime.now(), 1L, LocalDateTime.now(), 1L);
        quiz2 = new Quiz(1L, "Some Quiz Name2", "Description", 1L, 1L, 1L, 3, LocalDateTime.now(), 1L, LocalDateTime.now(), 1L);
        assertNotEquals(quiz1.hashCode(),quiz2.hashCode());
        quiz1 = new Quiz("Some Quiz Name", "Description", 1L, 3, 1L);
        quiz2 = new Quiz("Some Quiz Name", "Description", 1L, 3, 1L);
        assertThat(quiz1.hashCode(),equalTo(quiz2.hashCode()));
        quiz1 = new Quiz("Some Quiz Name1", "Description", 1L, 3, 1L);
        quiz2 = new Quiz("Some Quiz Name2", "Description", 1L, 3, 1L);
        assertNotEquals(quiz1.hashCode(),quiz2.hashCode());
    }
}