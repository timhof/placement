package org.tfa.dto;

import java.util.List;

import org.tfa.model.Cm;
import org.tfa.model.HiringManager;
import org.tfa.model.Vacancy;

import com.google.gson.annotations.Expose;

public class HiringManagerSearch {

	@Expose private List<HiringManager> hiringManagerList;
	@Expose private List<InputDTO> regionsInput;
	@Expose private InputDTO selectedRegion;
	@Expose private List<InputDTO> entityTypesInput;
	
	public HiringManagerSearch(List<InputDTO> regionsInput, List<InputDTO> entityTypesInput){
		this.regionsInput = regionsInput;
		this.entityTypesInput = entityTypesInput;
	}
	
	public List<InputDTO> getRegionsInput() {
		return regionsInput;
	}
	public void setRegionsInput(List<InputDTO> regionsInput) {
		this.regionsInput = regionsInput;
	}
	public InputDTO getSelectedRegion() {
		return selectedRegion;
	}
	public void setSelectedRegionId(InputDTO selectedRegion) {
		this.selectedRegion = selectedRegion;
	}
	public List<HiringManager> getHiringManagerList() {
		return hiringManagerList;
	}
	public void setHiringManagerList(List<HiringManager> hiringManagerList) {
		this.hiringManagerList = hiringManagerList;
	}

	public List<InputDTO> getEntityTypesInput() {
		return entityTypesInput;
	}

	public void setEntityTypesInput(List<InputDTO> entityTypesInput) {
		this.entityTypesInput = entityTypesInput;
	}

	public void setSelectedRegion(InputDTO selectedRegion) {
		this.selectedRegion = selectedRegion;
	}
}
