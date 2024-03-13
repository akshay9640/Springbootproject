package com.gramtarang.imfinance.dao;

import com.gramtarang.imfinance.model.FinLoginLogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinLoginLogsDao extends JpaRepository <FinLoginLogs, Integer>{

}
