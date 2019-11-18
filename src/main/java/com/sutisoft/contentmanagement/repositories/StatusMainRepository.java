package com.sutisoft.contentmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sutisoft.contentmanagement.domain.StatusMain;
@Repository
public interface StatusMainRepository extends JpaRepository<StatusMain, Integer> {

}
