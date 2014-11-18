package org.tfa.model;

import com.google.gson.annotations.Expose;

public class HiringManager {

	public enum EntityType{
		SCHOOL("School"), PLACEMENT_PARTNER("Placement Partner");
		
		@Expose private String label;
		private EntityType(String label){
			this.label = label;
		}
		public String getLabel(){
			return this.label;
		}
	}
	
	@Expose private Integer hiringManagerId;
	@Expose private String name;
	@Expose private String role;
	@Expose private EntityType entityType;
	public EntityType getEntityType() {
		return entityType;
	}
	public void setEntityType(EntityType entityType) {
		this.entityType = entityType;
	}
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	@Expose private String entityName;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getHiringManagerId() {
		return hiringManagerId;
	}
	public void setHiringManagerId(Integer hiringManagerId) {
		this.hiringManagerId = hiringManagerId;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
}
