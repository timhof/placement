package org.tfa.dto;

import java.util.List;

import org.tfa.model.Cm;
import org.tfa.model.TimePeriod;

public class TimePeriodSearch {

	private List<TimePeriod> timePeriodList;
	private Integer startTimePeriodId;
	private Integer endTimePeriodId;

	public TimePeriodSearch(){
		this.startTimePeriodId = 25;
		this.endTimePeriodId = 27;
	}
	
	public TimePeriodSearch(List<TimePeriod> timePeriodList, Integer startTimePeriodId, Integer endTimePeriodId){
		this.timePeriodList = timePeriodList;
		this.startTimePeriodId = startTimePeriodId;
		this.endTimePeriodId = endTimePeriodId;
	}

	public List<TimePeriod> getTimePeriodList() {
		return timePeriodList;
	}

	public void setTimePeriodList(List<TimePeriod> timePeriodList) {
		this.timePeriodList = timePeriodList;
	}

	public Integer getStartTimePeriodId() {
		return startTimePeriodId;
	}

	public void setStartTimePeriodId(Integer startTimePeriodId) {
		this.startTimePeriodId = startTimePeriodId;
	}

	public Integer getEndTimePeriodId() {
		return endTimePeriodId;
	}

	public void setEndTimePeriodId(Integer endTimePeriodId) {
		this.endTimePeriodId = endTimePeriodId;
	}
}
