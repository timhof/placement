package org.tfa.model;

import java.util.Map;

import com.google.gson.annotations.Expose;

public class Vacancy {

	@Expose private Integer vacancyId;
	@Expose private String name;
	@Expose private String placementPartner;
	@Expose private String school;
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
	public String getPlacementPartner() {
		return placementPartner;
	}
	public void setPlacementPartner(String placementPartner) {
		this.placementPartner = placementPartner;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}

}
