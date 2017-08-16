/**
 * Name      : com.malcolm.qme.springdata.repository.QuestionRepositoryImpl.java
 * Date      : 5/7/15
 * Developer : Malcolm
 * Purpose   : QMe Question Repository Implementation
 */
package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.core.domain.Category;
import com.malcolm.qme.core.domain.Question;
import com.malcolm.qme.core.domain.User;
import com.malcolm.qme.core.repository.PageSort;
import com.malcolm.qme.core.repository.QMeException;
import com.malcolm.qme.core.repository.QuestionRepository;
import com.malcolm.qme.springdata.entity.QuestionEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
     * Logger
     */
    private static final Logger LOG = LoggerFactory.getLogger(QuestionRepositoryImpl.class);

    /**
     * Spring Data QuestionEntity Repository
     */
    @Autowired
    private QuestionSpringDataRepository questionSpringDataRepository;

	@Override
	public Long count() throws QMeException {
		return questionSpringDataRepository.count();
	}

    @Override
	public List<Question> findByCategoryId(Long categoryID) throws QMeException {
		try{
    	    return(getQuestion(questionSpringDataRepository.findByCatId(categoryID)));
        }catch(Exception err){
            throw new QMeException(err);
        }
	}

    @Override
    public List<Question> findByCategoryId(Long categoryID, PageSort pageSort) throws QMeException {
        PageRequest pageRequest = getPageRequest(pageSort);
        Page<QuestionEntity> questionList = questionSpringDataRepository.findByCatId(categoryID,pageRequest);
        return (getQuestion(questionList.getContent()));
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
	public List<Question> findAll(PageSort pageSort) throws QMeException {
        PageRequest pageRequest = getPageRequest(pageSort);
        Page<QuestionEntity> questionList = questionSpringDataRepository.findAll(pageRequest);
        return (getQuestion(questionList.getContent()));
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
        Question question = new Question (
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

        if(questionEntity.getCreateUser() != null){
            question.setCreateUser(new User(
                    questionEntity.getCreateUser().getUserId(),questionEntity.getCreateUser().getUserName(),
                    questionEntity.getCreateUser().getUserPasscode(), questionEntity.getCreateUser().getUserFirstName(),
                    questionEntity.getCreateUser().getUserLastName(), questionEntity.getCreateUser().getUserEmail(),
                    questionEntity.getCreateUser().getUserRegisteredDate(),
                    questionEntity.getCreateUser().getUserUpdatedDate(),
                    questionEntity.getCreateUser().getUserLastLoginDate(),
                    questionEntity.getCreateUser().getUserLoginDate(),
                    questionEntity.getCreateUser().getUpdateUser()));
        }

        if(questionEntity.getUpdateUser() != null){
            question.setUpdateUser(new User(
                    questionEntity.getUpdateUser().getUserId(),questionEntity.getUpdateUser().getUserName(),
                    questionEntity.getUpdateUser().getUserPasscode(), questionEntity.getUpdateUser().getUserFirstName(),
                    questionEntity.getUpdateUser().getUserLastName(), questionEntity.getUpdateUser().getUserEmail(),
                    questionEntity.getUpdateUser().getUserRegisteredDate(),
                    questionEntity.getUpdateUser().getUserUpdatedDate(),
                    questionEntity.getUpdateUser().getUserLastLoginDate(),
                    questionEntity.getUpdateUser().getUserLoginDate(),
                    questionEntity.getUpdateUser().getUpdateUser()));
        }

        if(questionEntity.getCategory() != null){
            question.setCategory(new Category(questionEntity.getCategory().getCatId(),
                    questionEntity.getCategory().getCatParentId(),
                    questionEntity.getCategory().getCatName(),
                    questionEntity.getCategory().getCatLikes(),
                    questionEntity.getCategory().getCatCreateDate(),
                    questionEntity.getCategory().getCatCreateUser()

            ));
        }

        return question;
    }

    /**
     * Get PageRequest for PageSorting
     *
     * @param pageSort
     * @return PageRequest
     */
    private PageRequest getPageRequest(PageSort pageSort) {
        Sort.Direction direction = (pageSort.getSort())? Sort.Direction.ASC:Sort.Direction.DESC;
        List<String> sortFieldList = new ArrayList<>();
        if(pageSort.getSortFields() != null && pageSort.getSortFields().length > 0) {
            String[] sortFields = pageSort.getSortFields();
            for (String sortField : sortFields) {
                try {
                    sortFieldList.add(QUESTIONSORTFIELDS.valueOf(sortField.toUpperCase()).getQuestionSortField());

                } catch (IllegalArgumentException err) {
                    LOG.debug("Invalid Sort Field "+sortField.toUpperCase()+" Will be ignored");
                }
            }
        }
        PageRequest pageRequest;
        if(!sortFieldList.isEmpty()){
            pageRequest  =  new PageRequest(pageSort.getPageIndex(), pageSort.getMaxRows(), direction,sortFieldList.toArray(new String[sortFieldList.size()]));
        }else{
            pageRequest  =  new PageRequest(pageSort.getPageIndex(), pageSort.getMaxRows());
        }
        return pageRequest;
    }
}
