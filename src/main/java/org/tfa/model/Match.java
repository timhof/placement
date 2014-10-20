package org.tfa.model;

import com.google.gson.annotations.Expose;

public class Match {

	@Expose private Integer matchId;
	@Expose private Cm cm;
	@Expose private Vacancy vacancy;
	@Expose private MatchStatus matchStatus;
	
	public Cm getCm() {
		return cm;
	}
	public void setCm(Cm cm) {
		this.cm = cm;
	}
	public MatchStatus getMatchStatus() {
		return matchStatus;
	}
	public void setMatchStatus(MatchStatus status) {
		this.matchStatus = status;
	}
	public Vacancy getVacancy() {
		return vacancy;
	}
	public void setVacancy(Vacancy vacancy) {
		this.vacancy = vacancy;
	}
	public Integer getMatchId() {
		return matchId;
	}
	public void setMatchId(Integer matchId) {
		this.matchId = matchId;
	}
}
