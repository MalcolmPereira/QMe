package com.malcolm.qme.springdata.entity;

// Generated May 4, 2015 10:39:47 PM by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * QuestionHitEntity generated by hbm2java
 */
@Entity
@Table(name = "QUESTION_HIT", catalog = "qme")
public class QuestionHitEntity implements java.io.Serializable {

	private static final long serialVersionUID = 5786219888513685818L;
	
	private Long questionId;
	private Long catId;
	private Long questionHit;
	private Long rightCount;
	private Long wrongCount;

	public QuestionHitEntity() {
	}

	public QuestionHitEntity(Long questionId, Long catId, Long questionHit,
			Long rightCount, Long wrongCount) {
		this.questionId = questionId;
		this.catId = catId;
		this.questionHit = questionHit;
		this.rightCount = rightCount;
		this.wrongCount = wrongCount;
	}

	@Id
	@Column(name = "QUESTION_ID", unique = true, nullable = false)
	public Long getQuestionId() {
		return this.questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	@Column(name = "CAT_ID", nullable = false)
	public Long getCatId() {
		return this.catId;
	}

	public void setCatId(Long catId) {
		this.catId = catId;
	}

	@Column(name = "QUESTION_HIT", nullable = false)
	public Long getQuestionHit() {
		return this.questionHit;
	}

	public void setQuestionHit(Long questionHit) {
		this.questionHit = questionHit;
	}

	@Column(name = "RIGHT_COUNT", nullable = false)
	public Long getRightCount() {
		return this.rightCount;
	}

	public void setRightCount(Long rightCount) {
		this.rightCount = rightCount;
	}

	@Column(name = "WRONG_COUNT", nullable = false)
	public Long getWrongCount() {
		return this.wrongCount;
	}

	public void setWrongCount(Long wrongCount) {
		this.wrongCount = wrongCount;
	}

}
