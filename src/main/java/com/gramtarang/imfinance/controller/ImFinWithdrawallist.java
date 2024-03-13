package com.gramtarang.imfinance.controller;


import com.gramtarang.imfinance.dao.ImWalletTransactionsDao;
import com.gramtarang.imfinance.model.ImWalletTransactions;
import com.gramtarang.imfinance.model.WithdrawlByAgentrequest;
import com.gramtarang.imfinance.model.versionrequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fin")
public class ImFinWithdrawallist {
    @Autowired
    ImWalletTransactionsDao imWalletTransactionsDao;
    @CrossOrigin(origins = "*")
    @GetMapping("/getallwithdrawal")
    public List<ImWalletTransactions> get(){
        return imWalletTransactionsDao.getAllwithdraw();
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/getbwdates")
    public List<ImWalletTransactions> getAllBetweendatesLoans(@RequestBody versionrequest vr) {
        List<ImWalletTransactions> x = imWalletTransactionsDao.getAllBetweendatesLoans(vr.getDate1(), vr.getDate2());
        return x;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/getbwdatesbyagent")
    public List<ImWalletTransactions> getAllBetweendateswithbyagentid(@RequestBody WithdrawlByAgentrequest td) {
        List<ImWalletTransactions> x = imWalletTransactionsDao.getAllBetweendatesByAgid(td.getAgentid(),td.getDate1(), td.getDate2());
        return x;
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/getaepswithdrawal")
    public int opencount() {
        int x = imWalletTransactionsDao.getaepswithdraw();
        return x;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/getaepsmini")
    public int MiniStatement() {
        int x = imWalletTransactionsDao.getaepsminist();
        return x;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/getaepsbal")
    public int Balanceen() {
        int x = imWalletTransactionsDao.getaepsbalcenq();
        return x;
    }
}
