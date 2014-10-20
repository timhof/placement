package org.tfa.model;

import com.google.gson.annotations.Expose;

public class MatchStatus {

	@Expose Integer matchStatusId;
	@Expose String name;
	@Expose String code;
	@Expose Integer displayOrder;
	
	public Integer getMatchStatusId() {
		return matchStatusId;
	}
	public void setMatchStatusId(Integer matchStatusId) {
		this.matchStatusId = matchStatusId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getDisplayOrder() {
		return displayOrder;
	}
	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}
}

