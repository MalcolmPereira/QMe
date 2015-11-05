/**
 * Name      : com.malcolm.qme.springdata.entity.UserQuizGameEntity.java
 * Date      : 5/18/2015
 * Developer : Malcolm
 * Purpose   : User Quiz Game Entity
 */
package com.malcolm.qme.springdata.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author Malcolm
 */
@Entity
@Table(name = "USER_QUIZ_GAME", catalog = "qme")
public class UserQuizGameEntity implements java.io.Serializable {

	private static final long serialVersionUID = 6369583277714554743L;

	/**
	 * UserQuizGameEntity Id
	 */
	@EmbeddedId
	@AttributeOverrides({
		@AttributeOverride(name = "userId", column = @Column(name = "USER_ID", nullable = false)),
		@AttributeOverride(name = "catId", column = @Column(name = "CAT_ID", nullable = false)),
		@AttributeOverride(name = "quizGameToken", column = @Column(name = "QUIZ_GAME_TOKEN", nullable = false)) })
	private UserQuizGameEntityId id;

	/**
	 * Start Date
	 */
	@Column(name = "START_DATE", nullable = false, length = 19)
	private LocalDateTime startDate;

	/**
	 * End Date
	 */
	@Column(name = "END_DATE", length = 19)
	private LocalDateTime endDate;

	/**
	 * User Score
	 */
	@Column(name = "USER_SCORE", nullable = false)
	private Integer userScore;

	/**
	 * Public Constructor
	 */
	public UserQuizGameEntity() {
	}

    /**
     * Public Constructor
     *
     * @param id Id
     * @param startDate Start Date
     * @param endDate End Date
     * @param userScore User Score
     */
	public UserQuizGameEntity(UserQuizGameEntityId id, LocalDateTime startDate, LocalDateTime endDate, Integer userScore) {
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.userScore = userScore;
	}

	/**
	 * @return the id
	 */
	public UserQuizGameEntityId getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(UserQuizGameEntityId id) {
		this.id = id;
	}

	/**
	 * @return the startDate
	 */
	public LocalDateTime getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public LocalDateTime getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the userScore
	 */
	public Integer getUserScore() {
		return userScore;
	}

	/**
	 * @param userScore the userScore to set
	 */
	public void setUserScore(Integer userScore) {
		this.userScore = userScore;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UserQuizGameEntity that = (UserQuizGameEntity) o;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	/* (non-Javadoc)
         * @see java.lang.Object#toString()
         */
	@Override
	public String toString() {
		return "UserQuizGameEntity [id=" + id + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", userScore=" + userScore + "]";
	}

}
