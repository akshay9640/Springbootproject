package com.gramtarang.imfinance.dao;

import com.gramtarang.imfinance.model.GetFinLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GetFinLoginDao extends JpaRepository<GetFinLogin, Long> {
    @Query(value = "Select bl from GetFinLogin bl where bl.username=?1")
    GetFinLogin getHeadofficeLogin(String username);
    @Query(value = "Select bm from GetFinLogin bm where bm.username=?1 and bm.password=?2")
    GetFinLogin findByUserNameAndPassword(String username,  String password);

}
