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
import java.util.Objects;

/**
 * @author Malcolm
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
	 * @param questionId Question ID
	 * @param catId Category ID
	 * @param questionHit Question Hit
	 * @param rightCount Right Count
	 * @param wrongCount Wrong Count
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionHitEntity that = (QuestionHitEntity) o;
        return Objects.equals(questionId, that.questionId) &&
                Objects.equals(catId, that.catId) &&
                Objects.equals(questionHit, that.questionHit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId, catId, questionHit);
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
