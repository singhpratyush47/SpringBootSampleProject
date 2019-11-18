package com.sutisoft.contentmanagement.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sutisoft.contentmanagement.domain.StatusMain;
import com.sutisoft.contentmanagement.repositories.StatusMainRepository;

@Service
public class StatusMainServiceImpl implements StatusMainService {

	public static final Logger logger = LoggerFactory.getLogger(StatusMainServiceImpl.class.getName());
	
	private final StatusMainRepository statusMainRepo;
	
	public StatusMainServiceImpl(StatusMainRepository statusMainRepo) {
		super();
		this.statusMainRepo = statusMainRepo;
	}

	@Override
	public StatusMain findById(Integer statusId) {
		Optional<StatusMain> optionalStatusMain= statusMainRepo.findById(statusId);
		StatusMain statusMain= optionalStatusMain.get();
		return statusMain;
	}

	@Override
	public List<StatusMain> findAll() {
		logger.info("Start of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		List<StatusMain> listOfStatus=new ArrayList<>();
		listOfStatus=statusMainRepo.findAll();
		logger.info("End of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		return listOfStatus;
	}

	@Override
	@Transactional
	public StatusMain save(StatusMain status) {
		logger.info("Start of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		StatusMain savedStatusMain= statusMainRepo.save(status);
		logger.info("End of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		return savedStatusMain;
	}

}
