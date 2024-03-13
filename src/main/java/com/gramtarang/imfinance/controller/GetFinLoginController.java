package com.gramtarang.imfinance.controller;


import com.gramtarang.imfinance.dao.FinLoginLogsDao;
import com.gramtarang.imfinance.dao.GetFinLoginDao;
import com.gramtarang.imfinance.model.FinLoginLogs;
import com.gramtarang.imfinance.model.GetFinLogin;
import com.gramtarang.imfinance.model.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/fin/auth")
public class GetFinLoginController {

    private final PasswordEncoder passwordEncoder;
    ResponseObject<String> responseObject = null;
    @Autowired
    GetFinLoginDao getFinLoginDao;
    FinLoginLogsDao finLoginLogsDao;

    public GetFinLoginController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/login")
    public Object updatePasswordByUsernameBankAdmin(@Valid @RequestBody UserRequest vr) {
        GetFinLogin ad = getFinLoginDao.getHeadofficeLogin(vr.getUsername());
        if (ad == null) {
            return responseObject = new ResponseObject<>(404, "ERROR");
        }
        String db_pw = ad.getPassword();
        Boolean if_pw_matched = passwordEncoder.matches(vr.getPassword(), db_pw); ///////// ENCODING STRING PASSWORD
        System.out.println("PASSWORD from DB::: " + db_pw);
        System.out.println("PASSWORD from REQ::: " + vr.getPassword());
        String pw = "";

        if (if_pw_matched) {
            pw = db_pw;

        }
        System.out.println("Output of the pawssord matcher " + if_pw_matched);
        GetFinLogin llist1 = getFinLoginDao.findByUserNameAndPassword(vr.getUsername(), pw);
        System.out.println("LLSIT 1 out is as follows" + llist1);
        if (llist1 == null) {
            return responseObject = new ResponseObject<>(404, "ERROR");
        }
        return responseObject = new ResponseObject<>(200, "OK");
    }


    @CrossOrigin(origins = "*")
    @PostMapping("/getuserdata")
    public Object getHeadofficeLogin(@Valid @RequestBody UserRequest vr) {

        GetFinLogin ad = getFinLoginDao.getHeadofficeLogin(vr.getUsername());
        if (ad == null) {
            return responseObject = new ResponseObject<>(404, "ERROR");
        }
        GetFinLogin llist1 = getFinLoginDao.getHeadofficeLogin(vr.getUsername());
        System.out.println("LLSIT 1 out is as follows" + llist1);
        if (llist1 == null) {
            return responseObject = new ResponseObject<>(404, "ERROR");
        }
        return llist1;
    }
    @CrossOrigin(origins = "*")
    @GetMapping(path = "/updatePassword")
    public ResponseObject<String> updatePasswordByUsernameAdmin(@RequestBody GetFinLogin t) {
        GetFinLogin y = new GetFinLogin();
        GetFinLogin x = getFinLoginDao.getHeadofficeLogin(t.getUsername());
        y.setBranch(x.getBranch());
        y.setId(x.getId());
        y.setMobile(x.getMobile());
        y.setPassword(passwordEncoder.encode(t.getPassword()));
        y.setSession(x.getSession());
        y.setUpdationdate(x.getUpdationdate());
        y.setUsername(x.getUsername());
        getFinLoginDao.save(y);
        return responseObject = new ResponseObject<>(200, "OK");
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/loginlog")
    public FinLoginLogs insertLeaveRequest(@RequestBody FinLoginLogs finLoginLogs) {
        return finLoginLogsDao.save(finLoginLogs);

    }
}
