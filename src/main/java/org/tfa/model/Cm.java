package org.tfa.model;

import java.util.List;
import java.util.Map;

import com.google.gson.annotations.Expose;

public class Cm {

	@Expose private Integer cmPlacementStatusId;
	@Expose private String name;
	@Expose private Integer cmId;
	@Expose private Integer personId;
	@Expose private Integer corpsYear;
	@Expose private String stage;
	@Expose private String step;
	@Expose private String releaseStep;
	@Expose private String releaseCode;
	List<Match> matchList;
	@Expose int matchCount;
	private Map<String, Match> matchStatusListMap;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCmPlacementStatusId() {
		return cmPlacementStatusId;
	}
	public void setCmPlacementStatusId(Integer cmPlacementStatusId) {
		this.cmPlacementStatusId = cmPlacementStatusId;
	}
	public Map<String, Match> getMatchStatusListMap() {
		return matchStatusListMap;
	}
	public void setMatchStatusListMap(Map<String, Match> matchStatusListMap) {
		this.matchStatusListMap = matchStatusListMap;
	}
	public Integer getCmId() {
		return cmId;
	}
	public void setCmId(Integer cmId) {
		this.cmId = cmId;
	}
	public List<Match> getMatchList() {
		return matchList;
	}
	public void setMatchList(List<Match> matchList) {
		this.matchList = matchList;
		this.matchCount = matchList.size();
	}
	public int getMatchCount() {
		return matchCount;
	}
	public void setMatchCount(int matchCount) {
		this.matchCount = matchCount;
	}
	public Integer getPersonId() {
		return personId;
	}
	public void setPersonId(Integer personId) {
		this.personId = personId;
	}
	public Integer getCorpsYear() {
		return corpsYear;
	}
	public void setCorpsYear(Integer corpsYear) {
		this.corpsYear = corpsYear;
	}
	public String getStage() {
		return stage;
	}
	public void setStage(String stage) {
		this.stage = stage;
	}
	public String getStep() {
		return step;
	}
	public String getReleaseStep() {
		return releaseStep;
	}
	public void setReleaseStep(String releaseStep) {
		this.releaseStep = releaseStep;
	}
	public String getReleaseCode() {
		return releaseCode;
	}
	public void setReleaseCode(String releaseCode) {
		this.releaseCode = releaseCode;
	}
	public void setStep(String step) {
		this.step = step;
	}
	
}
