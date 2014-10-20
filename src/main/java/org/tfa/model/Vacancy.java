package org.tfa.model;

import java.util.Map;

import com.google.gson.annotations.Expose;

public class Vacancy {

	@Expose private Integer vacancyId;
	@Expose private String name;
	private Map<MatchStatus, Match> matchStatusListMap;
	
	public Integer getVacancyId() {
		return vacancyId;
	}
	public void setVacancyId(Integer vacancyId) {
		this.vacancyId = vacancyId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Map<MatchStatus, Match> getMatchStatusListMap() {
		return matchStatusListMap;
	}
	public void setMatchStatusListMap(Map<MatchStatus, Match> matchStatusListMap) {
		this.matchStatusListMap = matchStatusListMap;
	}

}
