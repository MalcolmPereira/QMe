/**
 * Name      : com.malcolm.qme.springdata.entity.QuizEntity.java
 * Date      : 5/18/2015
 * Developer : Malcolm
 * Purpose   : Quiz Entity
 */
package com.malcolm.qme.springdata.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author Malcolm
 */
@Entity
@Table(name = "QUIZ", catalog = "qme", uniqueConstraints = @UniqueConstraint(columnNames = "QUIZ_NAME"))
public class QuizEntity implements java.io.Serializable {

	private static final long serialVersionUID = -6004675333716243501L;

	/**
	 * Quiz Id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "QUIZ_ID", unique = true, nullable = false)
	private Long quizId;

	/**
	 * Quiz Name
	 */
	@Column(name = "QUIZ_NAME", unique = true, nullable = false)
	private String quizName;

	/**
	 * Quiz Desc
	 */
	@Column(name = "QUIZ_DESC")
	private String quizDesc;

	/**
	 * Category ID
	 */
	@Column(name = "CAT_ID", nullable = false)
	private Long catId;

	/**
	 * Quiz Hits
	 */
	@Column(name = "QUIZ_HITS")
	private Long quizHits;

	/**
	 * Quiz Likes
	 */
	@Column(name = "QUIZ_LIKES", nullable = false)
	private Long quizLikes;

	/**
	 * Quiz Max Attempts
	 */
	@Column(name = "MAX_ATTEMPTS", nullable = false)
	private Integer maxAttempts;

	/**
	 * Quiz Create Date
	 */
	@Column(name = "QUIZ_CREATE_DATE", nullable = false, length = 19)
	private LocalDateTime quizCreateDate;

	/**
	 * Quiz Create User
	 */
	@Column(name = "QUIZ_CREATE_USER", nullable = false)
	private Long quizCreateUser;

	/**
	 * Quiz Update Date
	 */
	@Column(name = "QUIZ_UPDATE_DATE", nullable = false, length = 19)
	private LocalDateTime quizUpdateDate;

	/**
	 * Quiz Update User
	 */
	@Column(name = "QUIZ_UPDATE_USER", nullable = false)
	private Long quizUpdateUser;

	/**
	 * public Constructor
	 */
	public QuizEntity() {
	}

	/**
	 * public Constructor
	 *
	 * @param quizName Quiz Name
	 * @param quizDesc Quiz Desc
	 * @param catId Category ID
	 * @param quizHits Quiz Hits
	 * @param quizLikes Quiz Likes
	 * @param maxAttempts Quiz Max Attempts
	 * @param quizCreateDate Quiz Create Date
	 * @param quizCreateUser Quiz Create User
	 * @param quizUpdateDate Quiz Update Date
	 * @param quizUpdateUser Quiz Update User
	 */
	public QuizEntity(String quizName, String quizDesc, Long catId,
			Long quizHits, Long quizLikes, Integer maxAttempts,
			LocalDateTime quizCreateDate, Long quizCreateUser, LocalDateTime quizUpdateDate,
			Long quizUpdateUser) {
		this.quizName = quizName;
		this.quizDesc = quizDesc;
		this.catId = catId;
		this.quizHits = quizHits;
		this.quizLikes = quizLikes;
		this.maxAttempts = maxAttempts;
		this.quizCreateDate = quizCreateDate;
		this.quizCreateUser = quizCreateUser;
		this.quizUpdateDate = quizUpdateDate;
		this.quizUpdateUser = quizUpdateUser;
	}

	/**
	 * @return the quizId
	 */
	public Long getQuizId() {
		return quizId;
	}

	/**
	 * @param quizId the quizId to set
	 */
	public void setQuizId(Long quizId) {
		this.quizId = quizId;
	}

	/**
	 * @return the quizName
	 */
	public String getQuizName() {
		return quizName;
	}

	/**
	 * @param quizName the quizName to set
	 */
	public void setQuizName(String quizName) {
		this.quizName = quizName;
	}

	/**
	 * @return the quizDesc
	 */
	public String getQuizDesc() {
		return quizDesc;
	}

	/**
	 * @param quizDesc the quizDesc to set
	 */
	public void setQuizDesc(String quizDesc) {
		this.quizDesc = quizDesc;
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
	 * @return the quizHits
	 */
	public Long getQuizHits() {
		return quizHits;
	}

	/**
	 * @param quizHits the quizHits to set
	 */
	public void setQuizHits(Long quizHits) {
		this.quizHits = quizHits;
	}

	/**
	 * @return the quizLikes
	 */
	public Long getQuizLikes() {
		return quizLikes;
	}

	/**
	 * @param quizLikes the quizLikes to set
	 */
	public void setQuizLikes(Long quizLikes) {
		this.quizLikes = quizLikes;
	}

	/**
	 * @return the maxAttempts
	 */
	public Integer getMaxAttempts() {
		return maxAttempts;
	}

	/**
	 * @param maxAttempts the maxAttempts to set
	 */
	public void setMaxAttempts(Integer maxAttempts) {
		this.maxAttempts = maxAttempts;
	}

	/**
	 * @return the quizCreateDate
	 */
	public LocalDateTime getQuizCreateDate() {
		return quizCreateDate;
	}

	/**
	 * @param quizCreateDate the quizCreateDate to set
	 */
	public void setQuizCreateDate(LocalDateTime quizCreateDate) {
		this.quizCreateDate = quizCreateDate;
	}

	/**
	 * @return the quizCreateUser
	 */
	public Long getQuizCreateUser() {
		return quizCreateUser;
	}

	/**
	 * @param quizCreateUser the quizCreateUser to set
	 */
	public void setQuizCreateUser(Long quizCreateUser) {
		this.quizCreateUser = quizCreateUser;
	}

	/**
	 * @return the quizUpdateDate
	 */
	public LocalDateTime getQuizUpdateDate() {
		return quizUpdateDate;
	}

	/**
	 * @param quizUpdateDate the quizUpdateDate to set
	 */
	public void setQuizUpdateDate(LocalDateTime quizUpdateDate) {
		this.quizUpdateDate = quizUpdateDate;
	}

	/**
	 * @return the quizUpdateUser
	 */
	public Long getQuizUpdateUser() {
		return quizUpdateUser;
	}

	/**
	 * @param quizUpdateUser the quizUpdateUser to set
	 */
	public void setQuizUpdateUser(Long quizUpdateUser) {
		this.quizUpdateUser = quizUpdateUser;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuizEntity that = (QuizEntity) o;
        return Objects.equals(quizId, that.quizId) &&
                Objects.equals(quizName, that.quizName) &&
                Objects.equals(catId, that.catId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quizId, quizName, catId);
    }

    /* (non-Javadoc)
         * @see java.lang.Object#toString()
         */
	@Override
	public String toString() {
		return "QuizEntity [quizId=" + quizId + ", quizName=" + quizName
				+ ", quizDesc=" + quizDesc + ", catId=" + catId + ", quizHits="
				+ quizHits + ", quizLikes=" + quizLikes + ", maxAttempts="
				+ maxAttempts + ", quizCreateDate=" + quizCreateDate
				+ ", quizCreateUser=" + quizCreateUser + ", quizUpdateDate="
				+ quizUpdateDate + ", quizUpdateUser=" + quizUpdateUser + "]";
	}

}
