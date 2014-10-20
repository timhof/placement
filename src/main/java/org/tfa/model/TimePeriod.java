package org.tfa.model;

import com.google.gson.annotations.Expose;

public class TimePeriod {

	@Expose private Integer timePeriodId;
	@Expose private String name;
	@Expose private String description;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getTimePeriodId() {
		return timePeriodId;
	}
	public void setTimePeriodId(Integer timePeriodId) {
		this.timePeriodId = timePeriodId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
