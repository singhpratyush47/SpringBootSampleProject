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
@Table(name="category")
public class Category {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="category_id")
	private Integer categoryId;
	@Column(name="name")
	private String name;
	@Column(name="type")
	private String type;
	@Column(name="company_id")
	private Integer companyId;
	@Column(name="created_date")
	private Date createdDate;
	@Column(name="created_by")
	private Integer createdBy;
	@ManyToOne
	@JoinColumn(name="status_id")
	private StatusMain status;
	@Column(name="last_modified_date")
	private Date lastModifiedDate;
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
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
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	public StatusMain getStatus() {
		return status;
	}
	public void setStatus(StatusMain status) {
		this.status = status;
	}
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", name=" + name + ", type=" + type + ", companyId=" + companyId
				+ ", createdDate=" + createdDate + ", createdBy=" + createdBy + ", status=" + status
				+ ", lastModifiedDate=" + lastModifiedDate + "]";
	}
}
