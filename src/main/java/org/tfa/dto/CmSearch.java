package org.tfa.dto;

import java.util.List;

import org.tfa.model.Cm;

import com.google.gson.annotations.Expose;

public class CmSearch {

	@Expose private List<Cm> cmList;
	@Expose private List<InputDTO> regionsInput;
	@Expose private InputDTO selectedRegion;
	@Expose private List<InputDTO> timePeriodsInput;
	@Expose private InputDTO selectedTimePeriod;
	
	public CmSearch(List<Cm> cmList, List<InputDTO> regionsInput, List<InputDTO> timePeriodsInput){
		this.cmList = cmList;
		this.regionsInput = regionsInput;
		this.timePeriodsInput = timePeriodsInput;
	}
	public List<Cm> getCmList() {
		return cmList;
	}
	public void setCmList(List<Cm> cmList) {
		this.cmList = cmList;
	}
	public List<InputDTO> getRegionsInput() {
		return regionsInput;
	}
	public void setRegionsInput(List<InputDTO> regionsInput) {
		this.regionsInput = regionsInput;
	}
	public List<InputDTO> getTimePeriodsInput() {
		return timePeriodsInput;
	}
	public void setTimePeriodsInput(List<InputDTO> timePeriodsInput) {
		this.timePeriodsInput = timePeriodsInput;
	}
	public InputDTO getSelectedRegion() {
		return selectedRegion;
	}
	public void setSelectedRegionId(InputDTO selectedRegion) {
		this.selectedRegion = selectedRegion;
	}
	public InputDTO getSelectedTimePeriod() {
		return selectedTimePeriod;
	}
	public void setSelectedTimePeriod(InputDTO selectedTimePeriod) {
		this.selectedTimePeriod = selectedTimePeriod;
	}

}
