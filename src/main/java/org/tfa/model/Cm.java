package org.tfa.model;

import java.util.List;
import java.util.Map;

import com.google.gson.annotations.Expose;

public class Cm {

	@Expose private Integer cmPlacementStatusId;
	@Expose private String name;
	@Expose private Integer cmId;
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
	
}
