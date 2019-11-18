package com.sutisoft.contentmanagement.command;

public class CategoryCommand {

	private String name;
	private String oldName;
	private String type;
	private Integer createdBy;
	private Integer statusId;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	public Integer getStatusId() {
		return statusId;
	}
	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}
	public String getOldName() {
		return oldName;
	}
	public void setOldName(String oldName) {
		this.oldName = oldName;
	}
	@Override
	public String toString() {
		return "CategoryCommand [name=" + name + ", oldName=" + oldName + ", type=" + type + ", createdBy=" + createdBy
				+ ", statusId=" + statusId + "]";
	}
}
