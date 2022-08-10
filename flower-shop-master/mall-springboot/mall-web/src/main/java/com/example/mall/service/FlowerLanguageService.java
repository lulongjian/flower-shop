package com.example.mall.service;

import com.example.common.utils.JsonModel;
import com.example.mall.dao.FlowerLanguageDOMapper;
import com.example.mall.domain.FlowerLanguageDO;
import com.example.mall.domain.form.FlowerLanguageForm;
import com.example.mall.utils.DateFormatUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class FlowerLanguageService {

    @Autowired
    private FlowerLanguageDOMapper flowerLanguageDOMapper;


    /**
     * 获取每日花语
     *
     * @return
     */
    public JsonModel getFlowerLanguageList() {
        JsonModel jsonModel = new JsonModel();
        try {
            FlowerLanguageForm flowerLanguageForm = new FlowerLanguageForm();
            flowerLanguageForm.setReTime(DateFormatUtils.DateToStr(new Date()));
            List<FlowerLanguageDO> flowerLanguageList = flowerLanguageDOMapper.getFlowerLanguageList(flowerLanguageForm);
            if (CollectionUtils.isNotEmpty(flowerLanguageList)) {
                FlowerLanguageDO flowerLanguageDO = flowerLanguageList.get(0);
                jsonModel.setData(flowerLanguageDO);
            }
        } catch (Exception e) {
            jsonModel.setCode(5000);
            jsonModel.setMsg("操作失败");
        }
        return jsonModel;
    }
}
