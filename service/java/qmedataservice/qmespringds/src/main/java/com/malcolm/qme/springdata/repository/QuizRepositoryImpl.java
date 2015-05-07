/**
 * Name      : com.malcolm.qme.springdata.repository.QuizRepositoryImpl.java
 * Date      : 5/7/15
 * Developer : Malcolm
 * Purpose   : QMe Quiz Repository Implementation
 */
package com.malcolm.qme.springdata.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.malcolm.qme.core.domain.Quiz;
import com.malcolm.qme.core.repository.QuizRepository;
import com.malcolm.qme.springdata.entity.QuizEntity;

/**
 * @Author: Malcolm
 */
@Repository("QuizRepository")
public class QuizRepositoryImpl implements QuizRepository {
	
	/**
     * Spring Data QuizRepository Repository
     */
    @Autowired
    private QuizSpringDataRepository quizSpringDataRepository;
	
	@Override
	public List<Quiz> findAll() {
		return(getQuiz(quizSpringDataRepository.findAll()));
	}
	
	@Override
	public List<Quiz> findByCategoryId(Long categoryID) {
		return(getQuiz(quizSpringDataRepository.findByCatId(categoryID)));
	}

	@Override
	public List<Quiz> findByMostLiked() {
		return(getQuiz(quizSpringDataRepository.findTop50ByOrderByLikes()));
	}

	@Override
	public List<Quiz> findQuizNameLike(String quizName) {
		return(getQuiz(quizSpringDataRepository.findByQuizNameIgnoreCaseLike(quizName)));
	}

	@Override
	public Quiz findById(Long id) {
		QuizEntity quizEntity = quizSpringDataRepository.findOne(id);
		if(quizEntity != null){
            return getQuiz(quizEntity);
        }
        return null;
	}

	@Override
	public Quiz save(Quiz quiz) {
		QuizEntity quizEntity = getQuizEntity(quiz);
		quizEntity.setQuizCreateDate(new Date());
		quizEntity.setQuizUpdateDate(new Date());
		quizEntity.setQuizUpdateUser(quizEntity.getQuizCreateUser());
		quizEntity = quizSpringDataRepository.save(quizEntity);
		return getQuiz(quizEntity);
	}

	@Override
	public Quiz update(Quiz quiz, Long updateUserId) {
		QuizEntity quizEntity = getQuizEntity(quiz);
		quizEntity.setQuizUpdateDate(new Date());
		quizEntity.setQuizUpdateUser(updateUserId);
		quizEntity = quizSpringDataRepository.save(quizEntity);
		return getQuiz(quizEntity);
	}

	@Override
	public void delete(Long id) {
		quizSpringDataRepository.delete(id);
	}

	/**
     * Map Quiz Domain Object to QuizEntity
     * 
     * @param quiz
     * @return
     */
    private QuizEntity getQuizEntity(Quiz quiz){
    	QuizEntity quizEntity = new QuizEntity();
    	if(quiz.getQuizID() > 0){
    		quizEntity.setQuizId(quiz.getQuizID());
    	}
    	quizEntity.setQuizName(quiz.getQuizName());
		quizEntity.setQuizDesc(quiz.getQuizDesc());
		quizEntity.setCatId(quiz.getCategoryID());
		if(quiz.getLikes() > 0){
			quizEntity.setQuizLikes(quiz.getLikes());
		}	
		if(quiz.getQuizHit() > 0){
			quizEntity.setQuizHits(quiz.getQuizHit());
		}
		if(quiz.getQuizMaxAttempts() > 0){
			quizEntity.setMaxAttempts(quiz.getQuizMaxAttempts());
		}
		quizEntity.setQuizCreateDate(quiz.getQuizCreateDate());
		quizEntity.setQuizCreateUser(quiz.getCreateUserID());
		quizEntity.setQuizUpdateDate(quiz.getQuizUpdateDate());
		quizEntity.setQuizUpdateUser(quiz.getUpdateUserID());
    	return quizEntity;
    }
	
	/**
     * Map QuizEntity to Quiz Domain Object
     * 
     * @param quizQuestionEntities
     * @return
     */
    private List<Quiz> getQuiz(List<QuizEntity> quizEntities){
    	List<Quiz> quizList = new ArrayList<Quiz>();
        if(quizEntities == null){
            return quizList;
        }
        for (QuizEntity quizEntity : quizEntities){
        	quizList.add(getQuiz(quizEntity));
        }
        return quizList;
    }
	
	/**
	 * Map QuizEntity to Quiz Domain Object
	 * 
     * @param quizEntity
     * @return
     */
    private Quiz getQuiz(QuizEntity quizEntity){
    	return new Quiz(
    			quizEntity.getQuizId(),
    			quizEntity.getQuizName(),
    			quizEntity.getQuizDesc(),
    			quizEntity.getCatId(),
    			quizEntity.getQuizLikes(),
    			quizEntity.getQuizHits(),
    			quizEntity.getMaxAttempts(),
    			quizEntity.getQuizCreateDate(),
    			quizEntity.getQuizCreateUser(),
    			quizEntity.getQuizUpdateDate(),
    			quizEntity.getQuizUpdateUser());
    }


}
