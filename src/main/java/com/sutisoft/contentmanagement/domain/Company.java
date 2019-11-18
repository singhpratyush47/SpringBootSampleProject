package com.sutisoft.contentmanagement.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="company")
public class Company {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="company_id")
	private Integer companyId;
	@Column(name="company_api_key")
	private String companyApiKey;
	@Column(name="company_name")
	private String companyName;
	@Column(name="created_date")
	private Date createdDate;
	@Column(name="last_modified_date")
	private Date lastModifiedDate;
	@ManyToOne
	@JoinColumn(name="status_id")
	private StatusMain status;
	@Column(name="created_by")
	private Integer createdBy;
	@Column(name="last_modified_by")
	private Integer lastModifiedBy;
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public String getCompanyApiKey() {
		return companyApiKey;
	}
	public void setCompanyApiKey(String companyApiKey) {
		this.companyApiKey = companyApiKey;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	public StatusMain getStatus() {
		return status;
	}
	public void setStatus(StatusMain status) {
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
		return "Company [companyId=" + companyId + ", companyApiKey=" + companyApiKey + ", companyName=" + companyName
				+ ", createdDate=" + createdDate + ", lastModifiedDate=" + lastModifiedDate + ", status=" + status
				+ ", createdBy=" + createdBy + ", lastModifiedBy=" + lastModifiedBy + "]";
	}
}
