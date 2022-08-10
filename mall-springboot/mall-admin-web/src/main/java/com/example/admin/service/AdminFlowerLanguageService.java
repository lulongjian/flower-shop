package com.example.admin.service;

import com.example.common.utils.JsonModel;
import com.example.mall.dao.FlowerLanguageDOMapper;
import com.example.mall.domain.FlowerLanguageDO;
import com.example.mall.domain.form.FlowerLanguageForm;
import com.example.mall.utils.DateFormatUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Objects;

@Service
@Transactional
public class AdminFlowerLanguageService {

    @Autowired
    private FlowerLanguageDOMapper flowerLanguageDOMapper;

    /**
     * 管理员获取每日花语
     *
     * @param page
     * @param number
     * @param flowerLanguageForm
     * @return
     */
    public JsonModel getFlowerLanguageList(Integer page, Integer number, FlowerLanguageForm flowerLanguageForm) {
        //开启分页
        PageHelper.startPage(page, number);
        Page<FlowerLanguageDO> voPage = (Page<FlowerLanguageDO>) flowerLanguageDOMapper.getFlowerLanguageList(flowerLanguageForm);
        PageInfo<FlowerLanguageDO> pageInfo = voPage.toPageInfo();
        //返回数据
        JsonModel jsonModel = new JsonModel();
        jsonModel.setCount(pageInfo.getTotal());
        jsonModel.setData(pageInfo.getList());
        return jsonModel;
    }

    /**
     * 新增每日花语
     *
     * @param flowerLanguageForm
     * @return
     */
    public JsonModel addFlowerLanguage(FlowerLanguageForm flowerLanguageForm) {
        JsonModel jsonModel = new JsonModel();
        try {
            Integer count = flowerLanguageDOMapper.getFlowerLanguageByReTime(flowerLanguageForm.getReTime());
            if (count > 0) {
                jsonModel.setCode(5000);
                jsonModel.setMsg("该日期已有推荐，请重新选择！");
                return jsonModel;
            }
            FlowerLanguageDO flowerLanguageDO = new FlowerLanguageDO();
            flowerLanguageDO.setReTime(DateFormatUtils.StrToDate(flowerLanguageForm.getReTime()));
            flowerLanguageDO.setTitle1(flowerLanguageForm.getTitle1());
            flowerLanguageDO.setTitle2(flowerLanguageForm.getTitle2());
            flowerLanguageDO.setCreateTime(new Date());
            flowerLanguageDOMapper.insertSelective(flowerLanguageDO);
        } catch (Exception e) {
            e.printStackTrace();
            jsonModel.setCode(5000);
            jsonModel.setMsg("操作失败");
        }
        return jsonModel;
    }

    /**
     * 更新每日花语
     *
     * @param flowerLanguageForm
     * @return
     */
    public JsonModel updateFlowerLanguage(FlowerLanguageForm flowerLanguageForm) {
        JsonModel jsonModel = new JsonModel();
        try {
            Integer count = flowerLanguageDOMapper.getFlowerLanguageByReTime(flowerLanguageForm.getReTime());
            FlowerLanguageDO flowerLanguageDO = flowerLanguageDOMapper.selectByPrimaryKey(flowerLanguageForm.getId());
            if (count > 0) {
                if (null != flowerLanguageDO && !Objects.equals(DateFormatUtils.DateToStr(flowerLanguageDO.getReTime()), flowerLanguageForm.getReTime())) {
                    jsonModel.setCode(5000);
                    jsonModel.setMsg("该日期已有推荐，请重新选择！");
                    return jsonModel;
                }
            }
            flowerLanguageDO.setReTime(DateFormatUtils.StrToDate(flowerLanguageForm.getReTime()));
            flowerLanguageDO.setTitle1(flowerLanguageForm.getTitle1());
            flowerLanguageDO.setTitle2(flowerLanguageForm.getTitle2());
            flowerLanguageDOMapper.updateByPrimaryKeySelective(flowerLanguageDO);
        } catch (Exception e) {
            e.printStackTrace();
            jsonModel.setCode(5000);
            jsonModel.setMsg("操作失败");
        }
        return jsonModel;
    }

    /**
     * 删除每日花语
     *
     * @param id
     * @return
     */
    public JsonModel deleteFlowerLanguage(Integer id) {
        JsonModel jsonModel = new JsonModel();
        try {
            flowerLanguageDOMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            jsonModel.setCode(5000);
            jsonModel.setMsg("操作失败");
        }
        return jsonModel;
    }
}
