package org.tfa.dto;

import java.util.List;

import org.tfa.model.Cm;
import org.tfa.model.Vacancy;

import com.google.gson.annotations.Expose;

public class VacancySearch {

	@Expose private List<Vacancy> vacancyList;
	@Expose private List<InputDTO> regionsInput;
	@Expose private InputDTO selectedRegion;
	@Expose private List<InputDTO> timePeriodsInput;
	@Expose private InputDTO selectedTimePeriod;
	
	public VacancySearch(List<Vacancy> vacancyList, List<InputDTO> regionsInput, List<InputDTO> timePeriodsInput){
		this.vacancyList = vacancyList;
		this.regionsInput = regionsInput;
		this.timePeriodsInput = timePeriodsInput;
	}
	public List<Vacancy> getVacancyList() {
		return vacancyList;
	}
	public void setVacancyList(List<Vacancy> vacancyList) {
		this.vacancyList = vacancyList;
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
