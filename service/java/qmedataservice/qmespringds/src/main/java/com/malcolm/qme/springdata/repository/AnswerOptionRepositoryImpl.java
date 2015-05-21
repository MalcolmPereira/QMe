/**
 * Name      : com.malcolm.qme.springdata.repository.AnswerOptionRepositoryImpl.java
 * Date      : 5/5/2015
 * Developer : Malcolm
 * Purpose   : QMe AnswerOptionEntity Repository Implementation
 */
package com.malcolm.qme.springdata.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.malcolm.qme.core.domain.AnswerOption;
import com.malcolm.qme.core.repository.AnswerOptionRepository;
import com.malcolm.qme.springdata.entity.AnswerOptionEntity;

/**
 * @author Malcolm
 */
@Repository("AnswerOptionRepository")
public class AnswerOptionRepositoryImpl implements AnswerOptionRepository {

	/**
	 * Spring Data AnswerOptionEntity Repository
	 */
	@Autowired
	private AnswerOptionSpringDataRepository answerOptionSpringDataRepository;

	/**
	 * Wrong Option
	 */
	private static final String WRONG = "0";

	/**
	 * Correct Option
	 */
	private static final String CORRECT = "1";

	@Override
	public List<AnswerOption> findByQuestionId(Long questionID) {
		return(getAnswerOption(answerOptionSpringDataRepository.findByQuestionId(questionID)));
	}

	@Override
	public List<AnswerOption> findAll() {
		return(getAnswerOption(answerOptionSpringDataRepository.findAll()));
	}

	@Override
	public AnswerOption findById(Long id) {
		final AnswerOptionEntity answerOptionEntity = answerOptionSpringDataRepository.findOne(id);
		if (answerOptionEntity != null){
			return getAnswerOption(answerOptionEntity);
		}
		return null;
	}

	@Override
	public AnswerOption save(AnswerOption answerOption) {
		AnswerOptionEntity answerOptionEntity = getAnswerOptionEntity(answerOption);
		answerOptionEntity = answerOptionSpringDataRepository.save(answerOptionEntity);
		return getAnswerOption(answerOptionEntity);
	}

	@Override
	public AnswerOption update(AnswerOption answerOption, Long updateUserId) {
		AnswerOptionEntity answerOptionEntity = getAnswerOptionEntity(answerOption);
		answerOptionEntity = answerOptionSpringDataRepository.save(answerOptionEntity);
		return getAnswerOption(answerOptionEntity);
	}

	@Override
	public void delete(Long id) {
		answerOptionSpringDataRepository.delete(id);
	}

	/**
	 * Map AnswerOption Domain Object to AnswerOptionEntity
	 *
	 * @param answerOption AnswerOption
	 * @return AnswerOptionEntity
	 */
	private AnswerOptionEntity getAnswerOptionEntity(AnswerOption answerOption){
		final AnswerOptionEntity answerOptionEntity = new AnswerOptionEntity();
		if(answerOption.getAnswerOptionID() > 0){
			answerOptionEntity.setOptionId(answerOption.getAnswerOptionID());
		}
		answerOptionEntity.setQuestionId(answerOption.getQuestionID());
		answerOptionEntity.setOptionText(answerOption.getOptionText());
		if(answerOption.isCorrect()){
			answerOptionEntity.setIscorrect(Byte.parseByte(CORRECT));
		}else{
			answerOptionEntity.setIscorrect(Byte.parseByte(WRONG));
		}
		return answerOptionEntity;
	}

	/**
	 * Map AnswerOptionEntity to AnswerOption Domain Object
	 *
	 * @param answerOptionEntities AnswerOptionEntity List
	 * @return AnswerOption List
	 */
	private List<AnswerOption> getAnswerOption(List<AnswerOptionEntity> answerOptionEntities){
		final List<AnswerOption> optionList = new ArrayList<AnswerOption>();
		if(answerOptionEntities == null){
			return optionList;
		}
		for (final AnswerOptionEntity answerOptionEntity : answerOptionEntities){
			optionList.add(getAnswerOption(answerOptionEntity));
		}
		return optionList;
	}

	/**
	 * Map AnswerOptionEntity to AnswerOption Domain Object
	 *
	 * @param answerOptionEntity AnswerOptionEntity
	 * @return AnswerOption
	 */
	private AnswerOption getAnswerOption(AnswerOptionEntity answerOptionEntity){
		Boolean optionCorrect = Boolean.FALSE;
		if(answerOptionEntity.getIscorrect() == 0){
			optionCorrect = Boolean.FALSE;
		}else{
			optionCorrect = Boolean.TRUE;
		}
		return new AnswerOption(answerOptionEntity.getOptionId(),answerOptionEntity.getQuestionId(),answerOptionEntity.getOptionText(),optionCorrect);
	}
}
