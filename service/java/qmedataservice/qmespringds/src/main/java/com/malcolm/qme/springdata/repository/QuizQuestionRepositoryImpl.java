/**
 * Name      : com.malcolm.qme.springdata.repository.QuizQuestionRepositoryImpl.java
 * Date      : 5/7/15
 * Developer : Malcolm
 * Purpose   : QMe Quiz Question Repository Implementation
 */
package com.malcolm.qme.springdata.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.malcolm.qme.core.domain.QuizQuestion;
import com.malcolm.qme.core.repository.QuizQuestionRepository;
import com.malcolm.qme.springdata.entity.QuizQuestionEntity;

/**
 * @Author: Malcolm
 */
@Repository("QuizQuestionRepository")
public class QuizQuestionRepositoryImpl implements QuizQuestionRepository {
	
	/**
     * Spring Data QuestionEntity Repository
     */
    @Autowired
    private QuizQuestionSpringDataRepository quizQuestionSpringDataRepository;
	
	@Override
	public List<QuizQuestion> findAll() {
		return(getQuizQuestion(quizQuestionSpringDataRepository.findAll()));
	}
	
	@Override
	public List<QuizQuestion> findByQuizId(Long quizID) {
		return(getQuizQuestion(quizQuestionSpringDataRepository.findByQuizId(quizID)));
	}

	@Override
	public List<QuizQuestion> findByQuestionId(Long questionID) {
		return(getQuizQuestion(quizQuestionSpringDataRepository.findByQuestionId(questionID)));
	}
	
	@Override
	public QuizQuestion findById(Long id) {
		QuizQuestionEntity quizQuestionEntity = quizQuestionSpringDataRepository.findOne(id);
		if(quizQuestionEntity != null){
            return getQuizQuestion(quizQuestionEntity);
        }
        return null;
	}

	@Override
	public QuizQuestion save(QuizQuestion quizQuestion) {
		QuizQuestionEntity quizQuestionEntity = getQuizQuestionEntity(quizQuestion); 
		quizQuestionEntity = quizQuestionSpringDataRepository.save(quizQuestionEntity);
		return getQuizQuestion(quizQuestionEntity);
	}

	@Override
	public QuizQuestion update(QuizQuestion quizQuestion, Long updateUserId) {
		QuizQuestionEntity quizQuestionEntity = getQuizQuestionEntity(quizQuestion); 
		quizQuestionEntity = quizQuestionSpringDataRepository.save(quizQuestionEntity);
		return getQuizQuestion(quizQuestionEntity);
	}

	@Override
	public void delete(Long id) {
		quizQuestionSpringDataRepository.delete(id);

	}

	/**
     * Map QuizQuestion Domain Object to QuizQuestionEntity
     * 
     * @param quizQuestion
     * @return
     */
    private QuizQuestionEntity getQuizQuestionEntity(QuizQuestion quizQuestion){
    	QuizQuestionEntity quizQuestionEntity = new QuizQuestionEntity();
    	if(quizQuestion.getQuizQuestionID() > 0){
    		quizQuestionEntity.setQuizQuestionId(quizQuestion.getQuizQuestionID());
    	}
    	quizQuestionEntity.setQuestionId(quizQuestion.getQuestionID());
		quizQuestionEntity.setQuizId(quizQuestion.getQuizID());
    	return quizQuestionEntity;
    }
	
	/**
     * Map QuizQuestionEntity to QuizQuestion Domain Object
     * 
     * @param quizQuestionEntities
     * @return
     */
    private List<QuizQuestion> getQuizQuestion(List<QuizQuestionEntity> quizQuestionEntities){
    	List<QuizQuestion> quizQuestionList = new ArrayList<QuizQuestion>();
        if(quizQuestionEntities == null){
            return quizQuestionList;
        }
        for (QuizQuestionEntity quizQuestionEntity : quizQuestionEntities){
        	quizQuestionList.add(getQuizQuestion(quizQuestionEntity));
        }
        return quizQuestionList;
    }

	/**
	 * Map QuizQuestionEntity to QuizQuestion Domain Object
	 * 
     * @param quizQuestionEntity
     * @return
     */
    private QuizQuestion getQuizQuestion(QuizQuestionEntity quizQuestionEntity){
    	return new QuizQuestion(
    			quizQuestionEntity.getQuizQuestionId(),
    			quizQuestionEntity.getQuestionId(),
    			quizQuestionEntity.getQuizId());
    }


}
