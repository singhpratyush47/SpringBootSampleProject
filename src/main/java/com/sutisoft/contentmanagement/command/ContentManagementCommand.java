package com.sutisoft.contentmanagement.command;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL) 
public class ContentManagementCommand {

	private String categoryName;
	private String oldCategoryName;
	private String productName;
	private String oldProductName;
	private String categoryType;
	private String updatedBy;
	private String description;
	private String contentId;
	private String oldContentId;
	private Integer status;
	private Integer oldStatus;
	private Integer modifiedBy;
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getCategoryType() {
		return categoryType;
	}
	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getContentId() {
		return contentId;
	}
	public void setContentId(String contentId) {
		this.contentId = contentId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getOldCategoryName() {
		return oldCategoryName;
	}
	public void setOldCategoryName(String oldCategoryName) {
		this.oldCategoryName = oldCategoryName;
	}
	public String getOldProductName() {
		return oldProductName;
	}
	public void setOldProductName(String oldProductName) {
		this.oldProductName = oldProductName;
	}
	public String getOldContentId() {
		return oldContentId;
	}
	public void setOldContentId(String oldContentId) {
		this.oldContentId = oldContentId;
	}
	public Integer getOldStatus() {
		return oldStatus;
	}
	public void setOldStatus(Integer oldStatus) {
		this.oldStatus = oldStatus;
	}
	public Integer getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	@Override
	public String toString() {
		return "ContentManagementCommand [categoryName=" + categoryName + ", oldCategoryName=" + oldCategoryName
				+ ", productName=" + productName + ", oldProductName=" + oldProductName + ", categoryType="
				+ categoryType + ", updatedBy=" + updatedBy + ", description=" + description + ", contentId="
				+ contentId + ", oldContentId=" + oldContentId + ", status=" + status + ", oldStatus=" + oldStatus
				+ ", modifiedBy=" + modifiedBy + "]";
	}
}
