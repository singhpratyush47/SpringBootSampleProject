package com.sutisoft.contentmanagement.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="status_main")
public class StatusMain {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="status_id")
	private Integer statusId;
	@Column(name="status_value")
	private String statusValue;
	public Integer getStatusId() {
		return statusId;
	}
	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}
	public String getStatusValue() {
		return statusValue;
	}
	public void setStatusValue(String statusValue) {
		this.statusValue = statusValue;
	}
	@Override
	public String toString() {
		return "StatusMain [statusId=" + statusId + ", statusValue=" + statusValue + "]";
	}
}
