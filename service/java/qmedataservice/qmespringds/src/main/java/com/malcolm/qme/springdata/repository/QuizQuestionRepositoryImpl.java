/**
 * Name      : com.malcolm.qme.springdata.repository.QuizQuestionRepositoryImpl.java
 * Date      : 5/7/15
 * Developer : Malcolm
 * Purpose   : QMe Quiz Question Repository Implementation
 */
package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.core.domain.QuizQuestion;
import com.malcolm.qme.core.repository.QMeException;
import com.malcolm.qme.core.repository.QuizQuestionRepository;
import com.malcolm.qme.springdata.entity.QuizQuestionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Malcolm
 */
@Repository(value = "QuizQuestionRepository")
public class QuizQuestionRepositoryImpl implements QuizQuestionRepository {
	
	/**
     * Spring Data QuestionEntity Repository
     */
    @Autowired
    private QuizQuestionSpringDataRepository quizQuestionSpringDataRepository;
	
	@Override
	public List<QuizQuestion> findAll() throws QMeException {
		try{
			return(getQuizQuestion(quizQuestionSpringDataRepository.findAll()));
		}catch(Exception err){
			throw new QMeException(err);
		}
	}
	
	@Override
	public List<QuizQuestion> findByQuizId(Long quizID) throws QMeException {
		try{
			return(getQuizQuestion(quizQuestionSpringDataRepository.findByQuizId(quizID)));
		}catch(Exception err){
			throw new QMeException(err);
		}
	}

	@Override
	public List<QuizQuestion> findByQuestionId(Long questionID) throws QMeException {
		try{
			return(getQuizQuestion(quizQuestionSpringDataRepository.findByQuestionId(questionID)));
		}catch(Exception err){
			throw new QMeException(err);
		}
	}
	
	@Override
	public QuizQuestion findById(Long id) throws QMeException {
		try{
			QuizQuestionEntity quizQuestionEntity = quizQuestionSpringDataRepository.findOne(id);
			if(quizQuestionEntity != null){
				return getQuizQuestion(quizQuestionEntity);
			}
			return null;
		}catch(Exception err){
			throw new QMeException(err);
		}
	}

	@Override
	public QuizQuestion save(QuizQuestion quizQuestion) throws QMeException {
		try{
			QuizQuestionEntity quizQuestionEntity = getQuizQuestionEntity(quizQuestion);
			quizQuestionEntity = quizQuestionSpringDataRepository.save(quizQuestionEntity);
			return getQuizQuestion(quizQuestionEntity);
		}catch(Exception err){
			throw new QMeException(err);
		}
	}


	@Override
	public QuizQuestion update(QuizQuestion quizQuestion, Long updateUserId) throws QMeException {
		try{
			QuizQuestionEntity quizQuestionEntity = getQuizQuestionEntity(quizQuestion);
			quizQuestionEntity = quizQuestionSpringDataRepository.save(quizQuestionEntity);
			return getQuizQuestion(quizQuestionEntity);
		}catch(Exception err){
			throw new QMeException(err);
		}
	}

	@Override
	public void delete(Long id) throws QMeException {
		try{
			quizQuestionSpringDataRepository.delete(id);
		}catch(Exception err){
			throw new QMeException(err);
		}

	}

	/**
     * Map QuizQuestion Domain Object to QuizQuestionEntity
     * 
     * @param quizQuestion QuizQuestion
     * @return QuizQuestionEntity
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
     * @param quizQuestionEntities QuizQuestionEntity List
     * @return QuizQuestion List
     */
    private List<QuizQuestion> getQuizQuestion(List<QuizQuestionEntity> quizQuestionEntities){
    	List<QuizQuestion> quizQuestionList = new ArrayList<>();
        if(quizQuestionEntities == null){
            return quizQuestionList;
        }
		quizQuestionList.addAll(quizQuestionEntities.stream().map(this::getQuizQuestion).collect(Collectors.toList()));
        return quizQuestionList;
    }

	/**
	 * Map QuizQuestionEntity to QuizQuestion Domain Object
	 * 
     * @param quizQuestionEntity QuizQuestionEntity
     * @return QuizQuestion
     */
    private QuizQuestion getQuizQuestion(QuizQuestionEntity quizQuestionEntity){
    	return new QuizQuestion(
    			quizQuestionEntity.getQuizQuestionId(),
				quizQuestionEntity.getQuizId(),
    			quizQuestionEntity.getQuestionId());
    }


}
