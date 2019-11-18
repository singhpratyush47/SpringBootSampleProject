package com.sutisoft.contentmanagement.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sutisoft.contentmanagement.domain.StatusMain;


public interface StatusMainService {


	public StatusMain findById(Integer statusId);
	
	public List<StatusMain> findAll();
	
	public StatusMain save(StatusMain status);

}
