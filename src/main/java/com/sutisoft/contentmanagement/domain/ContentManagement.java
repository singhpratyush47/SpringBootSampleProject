package com.sutisoft.contentmanagement.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="content_management")
public class ContentManagement {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="content_management_id")
	private Integer contentManagementId;
	@Column(name="content_id")
	private String contentId;
	
	@ManyToOne
	@JoinColumn(name = "products_id")
	private Product product;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	
	@Column(name="company_id")
	private Integer companyId;
	@Column(name="category_type")
	private String categoryType;
	@Column(name="updated_by")
	private String updatedBy;
	@Column(name="updated_date")
	private Date updatedDate;
	
	@ManyToOne
	@JoinColumn(name = "status_id")
	private StatusMain status;
	@Lob
	@Column(name="description",columnDefinition="LONGTEXT")
	private String description;
	@Column(name="modified_by")
	private Integer modifiedBy;
	public Integer getContentManagementId() {
		return contentManagementId;
	}
	public void setContentManagementId(Integer contentManagementId) {
		this.contentManagementId = contentManagementId;
	}
	public String getContentId() {
		return contentId;
	}
	public void setContentId(String contentId) {
		this.contentId = contentId;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
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
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public StatusMain getStatus() {
		return status;
	}
	public void setStatus(StatusMain status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	@Override
	public String toString() {
		return "ContentManagement [contentManagementId=" + contentManagementId + ", contentId=" + contentId
				+ ", product=" + product + ", category=" + category + ", companyId=" + companyId + ", categoryType="
				+ categoryType + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + ", status=" + status
				+ ", description=" + description + ", modifiedBy=" + modifiedBy + "]";
	}
}
