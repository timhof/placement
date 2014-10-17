package org.tfa.dto;

import java.util.List;

import org.tfa.model.Region;

public class RegionSearch {

	private List<Region> regionList;
	private List<InputDTO> regionTypesInput;
	
	public RegionSearch(List<Region> regionList, List<InputDTO> regionTypesInput){
		this.regionList = regionList;
		this.regionTypesInput = regionTypesInput;
	}
	
	public List<Region> getRegionList() {
		return regionList;
	}
	public void setRegionList(List<Region> regionList) {
		this.regionList = regionList;
	}
	public List<InputDTO> getRegionTypesInput() {
		return regionTypesInput;
	}
	public void setRegionTypesInput(List<InputDTO> regionTypesInput) {
		this.regionTypesInput = regionTypesInput;
	}
}
