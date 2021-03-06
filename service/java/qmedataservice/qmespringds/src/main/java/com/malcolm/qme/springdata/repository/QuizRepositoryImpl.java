/**
 * Name      : com.malcolm.qme.springdata.repository.QuizRepositoryImpl.java
 * Date      : 5/7/15
 * Developer : Malcolm
 * Purpose   : QMe Quiz Repository Implementation
 */
package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.core.domain.Category;
import com.malcolm.qme.core.domain.Quiz;
import com.malcolm.qme.core.domain.User;
import com.malcolm.qme.core.repository.PageSort;
import com.malcolm.qme.core.repository.QMeException;
import com.malcolm.qme.core.repository.QuizRepository;
import com.malcolm.qme.springdata.entity.QuizEntity;
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
@Repository(value = "QuizRepository")
public class QuizRepositoryImpl implements QuizRepository {
	/**
	 * Logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(QuizRepositoryImpl.class);

	/**
	 * Spring Data QuizRepository Repository
	 */
	@Autowired
	private QuizSpringDataRepository quizSpringDataRepository;

	@Override
	public Long count() throws QMeException {
		return quizSpringDataRepository.count();
	}

	@Override
	public List<Quiz> findAll() throws QMeException {
		try{
			return(getQuiz(quizSpringDataRepository.findAll()));
		}catch(Exception err){
			throw new QMeException(err);
		}
	}

	@Override
	public List<Quiz> findAll(PageSort pageSort) throws QMeException {
		Sort.Direction direction = (pageSort.getSort())? Sort.Direction.ASC:Sort.Direction.DESC;
		List<String> sortFieldList = new ArrayList<>();
		if(pageSort.getSortFields() != null && pageSort.getSortFields().length > 0) {
			String[] sortFields = pageSort.getSortFields();
			for (String sortField : sortFields) {
				try {
					sortFieldList.add(QUIZSORTFIELDS.valueOf(sortField.toUpperCase()).getQuizSortField());

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
		Page<QuizEntity> quizList = quizSpringDataRepository.findAll(pageRequest);
		return (getQuiz(quizList .getContent()));
	}

	@Override
	public List<Quiz> findByCategoryId(Long categoryID) throws QMeException {
		try{
			return(getQuiz(quizSpringDataRepository.findByCatId(categoryID)));
		}catch(Exception err){
			throw new QMeException(err);
		}
	}

	@Override
	public List<Quiz> findByMostLiked() throws QMeException {
		try{
			return(getQuiz(quizSpringDataRepository.findTop50ByOrderByQuizLikesDesc()));
		}catch(Exception err){
			throw new QMeException(err);
		}
	}

	@Override
	public List<Quiz> findQuizNameLike(String quizName) throws QMeException {
		try{
			return(getQuiz(quizSpringDataRepository.findByQuizNameIgnoreCaseLike(quizName)));
		}catch(Exception err){
			throw new QMeException(err);
		}
	}

	@Override
	public Quiz findById(Long id) throws QMeException {
		try{
			final QuizEntity quizEntity = quizSpringDataRepository.findOne(id);
			if(quizEntity != null){
				return getQuiz(quizEntity);
			}
			return null;
		}catch(Exception err){
			throw new QMeException(err);
		}
	}

	@Override
	public Quiz save(Quiz quiz) throws QMeException {
		try{
			QuizEntity quizEntity = getQuizEntity(quiz);
			quizEntity.setQuizCreateDate(LocalDateTime.now());
			quizEntity.setQuizUpdateDate(LocalDateTime.now());
			quizEntity.setQuizUpdateUser(quizEntity.getQuizCreateUser());
			quizEntity = quizSpringDataRepository.save(quizEntity);
			return getQuiz(quizEntity);
		}catch(Exception err){
			throw new QMeException(err);
		}
	}

	@Override
	public Quiz update(Quiz quiz, Long updateUserId) throws QMeException {
		try{
			QuizEntity quizEntity = getQuizEntity(quiz);
			quizEntity.setQuizUpdateDate(LocalDateTime.now());
			quizEntity.setQuizUpdateUser(updateUserId);
			quizEntity = quizSpringDataRepository.save(quizEntity);
			return getQuiz(quizEntity);
		}catch(Exception err){
			throw new QMeException(err);
		}
	}

	@Override
	public void delete(Long id) throws QMeException {
		try{
			quizSpringDataRepository.delete(id);
		}catch(Exception err){
			throw new QMeException(err);
		}
	}

	/**
	 * Map Quiz Domain Object to QuizEntity
	 *
	 * @param quiz Quiz
	 * @return QuizEntity
	 */
	private QuizEntity getQuizEntity(Quiz quiz){
		final QuizEntity quizEntity = new QuizEntity();
		if(quiz.getQuizID() > 0){
			quizEntity.setQuizId(quiz.getQuizID());
		}
		quizEntity.setQuizName(quiz.getQuizName());
		quizEntity.setQuizDesc(quiz.getQuizDesc());
		quizEntity.setCatId(quiz.getCategoryID());
		quizEntity.setQuizLikes(quiz.getLikes());
		quizEntity.setQuizHits(quiz.getQuizHit());
		quizEntity.setMaxAttempts(quiz.getQuizMaxAttempts());
		quizEntity.setQuizCreateDate(quiz.getQuizCreateDate());
		quizEntity.setQuizCreateUser(quiz.getCreateUserID());
		quizEntity.setQuizUpdateDate(quiz.getQuizUpdateDate());
		quizEntity.setQuizUpdateUser(quiz.getUpdateUserID());
		return quizEntity;
	}

	/**
	 * Map QuizEntity to Quiz Domain Object
	 *
	 * @param quizEntities QuizEntity List
	 * @return Quiz List
	 */
	private List<Quiz> getQuiz(List<QuizEntity> quizEntities){
		final List<Quiz> quizList = new ArrayList<>();
		if(quizEntities == null){
			return quizList;
		}
		quizList.addAll(quizEntities.stream().map(this::getQuiz).collect(Collectors.toList()));
		return quizList;
	}

	/**
	 * Map QuizEntity to Quiz Domain Object
	 *
	 * @param quizEntity QuizEntity
	 * @return Quiz
	 */
	private Quiz getQuiz(QuizEntity quizEntity){
		Quiz quiz = new Quiz(
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

		if(quizEntity.getCreateUser() != null){
            quiz.setCreateUser(new User(
                    quizEntity.getCreateUser().getUserId(),quizEntity.getCreateUser().getUserName(),
                    quizEntity.getCreateUser().getUserPasscode(), quizEntity.getCreateUser().getUserFirstName(),
                    quizEntity.getCreateUser().getUserLastName(), quizEntity.getCreateUser().getUserEmail(),
                    quizEntity.getCreateUser().getUserRegisteredDate(),
                    quizEntity.getCreateUser().getUserUpdatedDate(),
                    quizEntity.getCreateUser().getUserLastLoginDate(),
                    quizEntity.getCreateUser().getUserLoginDate(),
                    quizEntity.getCreateUser().getUpdateUser()));
        }
        if(quizEntity.getUpdateUser() != null){
            quiz.setUpdateUser(new User(
                    quizEntity.getUpdateUser().getUserId(),quizEntity.getUpdateUser().getUserName(),
                    quizEntity.getUpdateUser().getUserPasscode(), quizEntity.getUpdateUser().getUserFirstName(),
                    quizEntity.getUpdateUser().getUserLastName(), quizEntity.getUpdateUser().getUserEmail(),
                    quizEntity.getUpdateUser().getUserRegisteredDate(),
                    quizEntity.getUpdateUser().getUserUpdatedDate(),
                    quizEntity.getUpdateUser().getUserLastLoginDate(),
                    quizEntity.getUpdateUser().getUserLoginDate(),
                    quizEntity.getUpdateUser().getUpdateUser()));
        }
        if(quizEntity.getCategory() != null){
            quiz.setCategory(new Category(quizEntity.getCategory().getCatId(),
                    quizEntity.getCategory().getCatParentId(),
                    quizEntity.getCategory().getCatName(),
                    quizEntity.getCategory().getCatLikes(),
                    quizEntity.getCategory().getCatCreateDate(),
                    quizEntity.getCategory().getCatCreateUser()

            ));
        }
		return quiz;
	}


}
