package org.tfa.dto;

import com.google.gson.annotations.Expose;

public class InputDTO {

	@Expose private String label;
	@Expose private String value;
	@Expose private Boolean selected;
	
	public InputDTO(String label, String value){
		this(label, value, true);
	}
	
	public InputDTO(String label, Integer value){
		this(label, value, true);
	}
	
	public InputDTO(String label, String value, Boolean selected){
		this.label = label;
		this.value = value;
		this.selected = selected;
	}
	
	public InputDTO(String label, Integer value, Boolean selected){
		this.label = label;
		this.value = value.toString();
		this.selected = selected;
	}
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Boolean getSelected() {
		return selected;
	}
	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
}
