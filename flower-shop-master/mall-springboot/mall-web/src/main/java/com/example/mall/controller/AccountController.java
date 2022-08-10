package com.example.mall.controller;

import com.example.common.utils.JsonModel;
import com.example.mall.domain.AccountDO;
import com.example.mall.service.AccountRecordService;
import com.example.mall.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("account")
@Slf4j
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRecordService accountRecordService;

    /**
     * 获取交易记录
     *
     * @param page
     * @param number
     * @param userId
     * @return
     */
    @RequestMapping(value = "getAccountRecord", method = RequestMethod.POST)
    public JsonModel getAccountRecord(Integer page, Integer number, String userId) {
        JsonModel jsonModel = new JsonModel();
        try {
            jsonModel = accountRecordService.getAccountRecord(page, number, userId);
            jsonModel.setMsg("加载成功");
        } catch (Exception e) {
            jsonModel.setMsg("网络错误！请刷新重试");
        }
        return jsonModel;
    }

    /**
     * 查看我的账户余额
     */
    @RequestMapping(value = "myAccount", method = RequestMethod.POST)
    public JsonModel myAccount(String userId) {
        JsonModel jsonModel = new JsonModel();
        //查询账户余额
        AccountDO query = new AccountDO();
        query.setUserId(Integer.parseInt(userId));
        AccountDO accountDO = accountService.findAccountByUserId(query);
        jsonModel.setData(accountDO.getBalance());
        return jsonModel;
    }

    /**
     * 我的账户充值/提现
     */
    @RequestMapping(value = "addAccountByUserId", method = RequestMethod.POST)
    public JsonModel addAccountByUserId(String userId, String type, String money, String productUserId) {
        JsonModel jsonModel = new JsonModel();
        switch (type) {
            case "3":
                //充值
                //余额添加
                accountService.addAccountByUserId(userId, money);
                jsonModel.setMsg("恭喜你成功充值 " + money + " 元");
                break;
            case "4":
                //提现
                //余额减少
                AccountDO qu = new AccountDO();
                qu.setUserId(Integer.parseInt(userId));
                AccountDO ac = accountService.findAccountByUserId(qu);
                if (ac.getBalance().compareTo(new BigDecimal(money)) == -1) {
                    jsonModel.setCode(1);
                    jsonModel.setMsg("余额不足,请先前往个人中心充值");
                    break;
                }
                accountService.redAccountByUserId(userId, money);
                jsonModel.setMsg("恭喜你成功提现 " + money + " 元");
                break;
            default:
                return jsonModel;
        }
        return jsonModel;
    }
}
