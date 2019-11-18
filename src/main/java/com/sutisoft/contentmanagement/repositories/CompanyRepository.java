package com.sutisoft.contentmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sutisoft.contentmanagement.domain.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer>{

	@Query("select companyId from Company where companyApiKey =:apiKey and status.statusId=1")
	Integer findByApiKey(@Param("apiKey") String apiKey);


}
