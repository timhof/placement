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
	@Expose private List<InputDTO> corpsYearsInput;
	@Expose private List<InputDTO> stagesInput;
	@Expose private List<InputDTO> stepsInput;
	@Expose private List<InputDTO> releaseCodesInput;
	@Expose private List<InputDTO> releaseStepsInput;
	
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
	public List<InputDTO> getCorpsYearsInput() {
		return corpsYearsInput;
	}
	public void setCorpsYearsInput(List<InputDTO> corpsYearInput) {
		this.corpsYearsInput = corpsYearInput;
	}
	public void setSelectedRegion(InputDTO selectedRegion) {
		this.selectedRegion = selectedRegion;
	}
	public List<InputDTO> getStagesInput() {
		return stagesInput;
	}
	public void setStagesInput(List<InputDTO> stagesInput) {
		this.stagesInput = stagesInput;
	}
	public List<InputDTO> getStepsInput() {
		return stepsInput;
	}
	public void setStepsInput(List<InputDTO> stepsInput) {
		this.stepsInput = stepsInput;
	}
	public List<InputDTO> getReleaseCodesInput() {
		return releaseCodesInput;
	}
	public void setReleaseCodesInput(List<InputDTO> releaseCodesInput) {
		this.releaseCodesInput = releaseCodesInput;
	}
	public List<InputDTO> getReleaseStepsInput() {
		return releaseStepsInput;
	}
	public void setReleaseStepsInput(List<InputDTO> releaseStepsInput) {
		this.releaseStepsInput = releaseStepsInput;
	}

}
