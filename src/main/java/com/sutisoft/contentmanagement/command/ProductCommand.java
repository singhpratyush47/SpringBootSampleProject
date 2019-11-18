package com.sutisoft.contentmanagement.command;

public class ProductCommand {

	private String productName;
	private String oldProductName;
	private Integer statusId;
	private String description;
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getStatusId() {
		return statusId;
	}
	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getOldProductName() {
		return oldProductName;
	}
	public void setOldProductName(String oldProductName) {
		this.oldProductName = oldProductName;
	}
	@Override
	public String toString() {
		return "ProductCommand [productName=" + productName + ", statusId=" + statusId + ", description=" + description
				+ "]";
	}
}
