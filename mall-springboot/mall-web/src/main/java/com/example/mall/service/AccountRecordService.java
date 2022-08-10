package com.example.mall.service;
import java.math.BigDecimal;
import java.util.Date;

import com.example.common.utils.JsonModel;
import com.example.mall.dao.UserMapper;
import com.example.mall.domain.AccountRecordDO;
import com.example.mall.dao.AccountRecordMapper;
import com.example.mall.domain.UserDO;
import com.example.mall.domain.form.AccountRecordForm;
import com.example.mall.domain.vo.AccountRecordVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class AccountRecordService {

	@Autowired
	private AccountRecordMapper accountRecordMapper;

	@Autowired
	private UserMapper userMapper;

	/**
	 *根据用户id查询交易记录
	 */
	public List<AccountRecordDO> findAccountRecordByUserId(AccountRecordDO accountRecordDO) {
		//根据时间排序
		Example example = new Example(AccountRecordDO.class);
		example.orderBy("createTime").desc();
		example.createCriteria().andEqualTo("userId",accountRecordDO.getUserId());
		return accountRecordMapper.selectByExample(example);
	}

	public JsonModel getAccountRecord(Integer page, Integer number, String userId) {
		AccountRecordForm accountRecordForm = new AccountRecordForm();
		accountRecordForm.setUserId(Integer.parseInt(userId));

		PageHelper.startPage(page, number);
		Page<AccountRecordVO> accountRecordVOPage = (Page<AccountRecordVO>) accountRecordMapper.getAccountRecord(accountRecordForm);
		PageInfo<AccountRecordVO> accountRecordVOPageInfo = accountRecordVOPage.toPageInfo();
		//返回数据
		List<AccountRecordVO> list = accountRecordVOPageInfo.getList();
		for (int i = 0; i<list.size(); i++) {
			//获取付款账户学号和手机号码
			UserDO userDO = userMapper.selectByPrimaryKey(list.get(i).getUserId());
			accountRecordVOPageInfo.getList().get(i).setUserSno(userDO.getSno());
			accountRecordVOPageInfo.getList().get(i).setUserPhone(userDO.getPhone());
			//获取被付款账户学号和手机号码
			if(list.get(i).getSellerName().equals("系统")){
				//系统管理员
				accountRecordVOPageInfo.getList().get(i).setSellerSno("00000001");
				accountRecordVOPageInfo.getList().get(i).setSellerPhone("13978999127");
			}else {
				UserDO seller = userMapper.selectByPrimaryKey(list.get(i).getUserId());
				accountRecordVOPageInfo.getList().get(i).setSellerSno(seller.getSno());
				accountRecordVOPageInfo.getList().get(i).setSellerPhone(seller.getPhone());
			}
		}

		JsonModel jsonModel = new JsonModel();
		jsonModel.setCount(accountRecordVOPageInfo.getTotal());
		jsonModel.setData(accountRecordVOPageInfo.getList());
		return jsonModel;
	}
}
