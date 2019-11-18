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
@Table(name="products")
public class Product {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="products_id")
	private Integer productId;
	@Column(name="name")
	private String name;
	@Column(name="company_id")
	private Integer companyId;
	@ManyToOne
	@JoinColumn(name="status_id")
	private StatusMain status;
	@Column(name="description")
	private String description;
	@Column(name="created_date")
	private Date createdDate;
	@Column(name="updated_date")
	private Date updatedDate;
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
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
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", name=" + name + ", companyId=" + companyId + ", status=" + status
				+ ", description=" + description + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate
				+ "]";
	}
}
