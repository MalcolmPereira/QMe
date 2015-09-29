/**
 * Name      : com.malcolm.qme.springdata.repository.QuestionRepositoryImpl.java
 * Date      : 5/7/15
 * Developer : Malcolm
 * Purpose   : QMe Question Repository Implementation
 */
package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.core.domain.Question;
import com.malcolm.qme.core.repository.QMeException;
import com.malcolm.qme.core.repository.QuestionRepository;
import com.malcolm.qme.springdata.entity.QuestionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Malcolm
 */
@Repository(value = "QuestionRepository")
public class QuestionRepositoryImpl implements QuestionRepository {
	
	/**
     * Spring Data QuestionEntity Repository
     */
    @Autowired
    private QuestionSpringDataRepository questionSpringDataRepository;
	
    @Override
	public List<Question> findByCategoryId(Long categoryID) throws QMeException {
		try{
    	    return(getQuestion(questionSpringDataRepository.findByCatId(categoryID)));
        }catch(Exception err){
            throw new QMeException(err);
        }
	}

	@Override
	public List<Question> findByMostLiked() throws QMeException {
        try{
		    return(getQuestion(questionSpringDataRepository.findTop50ByOrderByQuestionLikesDesc()));
        }catch(Exception err){
            throw new QMeException(err);
        }
	}
    
	@Override
	public List<Question> findAll() throws QMeException {
        try{
		    return(getQuestion(questionSpringDataRepository.findAll()));
        }catch(Exception err){
            throw new QMeException(err);
        }
	}

	@Override
	public Question findById(Long id) throws QMeException {
        try{
		    QuestionEntity questionEntity = questionSpringDataRepository.findOne(id);
		    if(questionEntity != null){
                return getQuestion(questionEntity);
            }
            return null;
        }catch(Exception err){
            throw new QMeException(err);
        }
	}

	@Override
	public Question save(Question question) throws QMeException {
        try{
		    QuestionEntity questionEntity =  getQuestionEntity(question);
		    questionEntity.setQuestionCreateDate(LocalDateTime.now());
		    questionEntity.setQuestionUpdateDate(LocalDateTime.now());
		    questionEntity.setQuestionUpdateUser(question.getCreateUserID());
		    questionEntity = questionSpringDataRepository.save(questionEntity);
            return getQuestion(questionEntity);
        }catch(Exception err){
            throw new QMeException(err);
        }
	}

	@Override
	public Question update(Question question, Long updateUserId) throws QMeException {
        try{
            QuestionEntity questionEntity =  getQuestionEntity(question);
            questionEntity.setQuestionUpdateDate(LocalDateTime.now());
            questionEntity.setQuestionUpdateUser(updateUserId);
            questionEntity = questionSpringDataRepository.save(questionEntity);
            return getQuestion(questionEntity);
        }catch(Exception err){
            throw new QMeException(err);
        }
	}

	@Override
	public void delete(Long id) throws QMeException {
        try{
		    questionSpringDataRepository.delete(id);
        }catch(Exception err){
            throw new QMeException(err);
        }
	}

	/**
     * Map QuestionHit Domain Object to QuestionHitEntity
     *
     * @param question Question
     * @return QuestionEntity
     */
    private QuestionEntity getQuestionEntity(Question question){
    	QuestionEntity questionEntity = new QuestionEntity();
    	if(question.getQuestionID() > 0){
    		questionEntity.setQuestionId(question.getQuestionID());
    	}
    	if(question.getCategoryID() > 0){
    		questionEntity.setCatId(question.getCategoryID());
    	}
    	questionEntity.setQuestionText(question.getQuestionText());
    	questionEntity.setQuestionAnswer(question.getAnswer());
    	questionEntity.setQuestionLikes(question.getLikes());
		questionEntity.setQuestionPoint(question.getQuestionPoint());
		questionEntity.setQuestionCreateDate(question.getQuestionCreateDate());
		questionEntity.setQuestionCreateUser(question.getCreateUserID());
		questionEntity.setQuestionUpdateDate(question.getQuestionUpdateDate());
		questionEntity.setQuestionUpdateUser(question.getUpdateUserID());
    	return questionEntity;
    }
	
	/**
     * Map QuestionEntity to Question Domain Object
     *
     * @param questionEntities Question Entities
     * @return List of Questions
     */
    private List<Question> getQuestion(List<QuestionEntity> questionEntities){
    	 List<Question> questionList = new ArrayList<>();
         if(questionEntities == null){
             return questionList;
         }
		questionList.addAll(questionEntities.stream().map(this::getQuestion).collect(Collectors.toList()));
         return questionList;
    }
	
	/**
     * Map QuestionEntity to Question Domain Object
     *
     * @param questionEntity  QuestionEntity
     * @return Question
     */
    private Question getQuestion(QuestionEntity questionEntity){
        return new Question (
        		questionEntity.getQuestionId(),
        		questionEntity.getCatId(),
        		questionEntity.getQuestionText(),
        		questionEntity.getQuestionAnswer(),
				questionEntity.getQuestionPoint(),
        		questionEntity.getQuestionLikes(),
        		questionEntity.getQuestionCreateDate(),
        		questionEntity.getQuestionCreateUser(),
        		questionEntity.getQuestionUpdateDate(),
        		questionEntity.getQuestionUpdateUser()
        );
    }
	
}
