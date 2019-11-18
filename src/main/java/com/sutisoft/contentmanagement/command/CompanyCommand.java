package com.sutisoft.contentmanagement.command;

public class CompanyCommand {

	private Integer companyId;
	private String companyApiKey;
	private String companyName;
	private Integer status;
	private Integer createdBy;
	private Integer lastModifiedBy;
	public String getCompanyApiKey() {
		return companyApiKey;
	}
	public void setCompanyApiKey(String companyApiKey) {
		this.companyApiKey = companyApiKey;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	public Integer getLastModifiedBy() {
		return lastModifiedBy;
	}
	public void setLastModifiedBy(Integer lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}
	@Override
	public String toString() {
		return "CompanyCommand [companyId=" + companyId + ", companyApiKey=" + companyApiKey + ", companyName="
				+ companyName + ", status=" + status + ", createdBy=" + createdBy + ", lastModifiedBy=" + lastModifiedBy
				+ "]";
	}
}
