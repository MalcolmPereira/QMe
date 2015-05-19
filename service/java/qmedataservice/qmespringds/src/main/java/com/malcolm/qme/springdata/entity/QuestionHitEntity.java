/**
 * Name      : com.malcolm.qme.springdata.entity.QuestionHitEntity.java
 * Date      : 5/18/2015
 * Developer : Malcolm
 * Purpose   : Question Hit Entity
 */
package com.malcolm.qme.springdata.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author: Malcolm
 */
@Entity
@Table(name = "QUESTION_HIT", catalog = "qme")
public class QuestionHitEntity implements java.io.Serializable {

	private static final long serialVersionUID = 5786219888513685818L;

	/**
	 * Question Id
	 */
	@Id
	@Column(name = "QUESTION_ID", unique = true, nullable = false)
	private Long questionId;

	/**
	 * Category ID
	 */
	@Column(name = "CAT_ID", nullable = false)
	private Long catId;

	/**
	 * Question Hit
	 */
	@Column(name = "QUESTION_HIT", nullable = false)
	private Long questionHit;

	/**
	 * Right Count
	 */
	@Column(name = "RIGHT_COUNT", nullable = false)
	private Long rightCount;

	/**
	 * Wrong Count
	 */
	@Column(name = "WRONG_COUNT", nullable = false)
	private Long wrongCount;

	/**
	 * Public Constructor
	 */
	public QuestionHitEntity() {
	}

	/**
	 * Public Constructor
	 *
	 * @param questionId
	 * @param catId
	 * @param questionHit
	 * @param rightCount
	 * @param wrongCount
	 */
	public QuestionHitEntity(Long questionId, Long catId, Long questionHit,
			Long rightCount, Long wrongCount) {
		this.questionId = questionId;
		this.catId = catId;
		this.questionHit = questionHit;
		this.rightCount = rightCount;
		this.wrongCount = wrongCount;
	}

	/**
	 * @return the questionId
	 */
	public Long getQuestionId() {
		return questionId;
	}

	/**
	 * @param questionId the questionId to set
	 */
	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	/**
	 * @return the catId
	 */
	public Long getCatId() {
		return catId;
	}

	/**
	 * @param catId the catId to set
	 */
	public void setCatId(Long catId) {
		this.catId = catId;
	}

	/**
	 * @return the questionHit
	 */
	public Long getQuestionHit() {
		return questionHit;
	}

	/**
	 * @param questionHit the questionHit to set
	 */
	public void setQuestionHit(Long questionHit) {
		this.questionHit = questionHit;
	}

	/**
	 * @return the rightCount
	 */
	public Long getRightCount() {
		return rightCount;
	}

	/**
	 * @param rightCount the rightCount to set
	 */
	public void setRightCount(Long rightCount) {
		this.rightCount = rightCount;
	}

	/**
	 * @return the wrongCount
	 */
	public Long getWrongCount() {
		return wrongCount;
	}

	/**
	 * @param wrongCount the wrongCount to set
	 */
	public void setWrongCount(Long wrongCount) {
		this.wrongCount = wrongCount;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((catId == null) ? 0 : catId.hashCode());
		result = (prime * result)
				+ ((questionHit == null) ? 0 : questionHit.hashCode());
		result = (prime * result)
				+ ((questionId == null) ? 0 : questionId.hashCode());
		result = (prime * result)
				+ ((rightCount == null) ? 0 : rightCount.hashCode());
		result = (prime * result)
				+ ((wrongCount == null) ? 0 : wrongCount.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final QuestionHitEntity other = (QuestionHitEntity) obj;
		if (catId == null) {
			if (other.catId != null) {
				return false;
			}
		} else if (!catId.equals(other.catId)) {
			return false;
		}
		if (questionHit == null) {
			if (other.questionHit != null) {
				return false;
			}
		} else if (!questionHit.equals(other.questionHit)) {
			return false;
		}
		if (questionId == null) {
			if (other.questionId != null) {
				return false;
			}
		} else if (!questionId.equals(other.questionId)) {
			return false;
		}
		if (rightCount == null) {
			if (other.rightCount != null) {
				return false;
			}
		} else if (!rightCount.equals(other.rightCount)) {
			return false;
		}
		if (wrongCount == null) {
			if (other.wrongCount != null) {
				return false;
			}
		} else if (!wrongCount.equals(other.wrongCount)) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QuestionHitEntity [questionId=" + questionId + ", catId="
				+ catId + ", questionHit=" + questionHit + ", rightCount="
				+ rightCount + ", wrongCount=" + wrongCount + "]";
	}

}
