package com.example.admin.controller;

import com.example.admin.service.AdminFlowerLanguageService;
import com.example.common.utils.JsonModel;
import com.example.mall.domain.form.FlowerLanguageForm;
import com.example.mall.domain.form.FlowerLanguageForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/flower/language")
public class adminFlowerLanguageController {

    @Autowired
    private AdminFlowerLanguageService adminFlowerLanguageService;

    /**
     * 管理员获取每日花语
     *
     * @return
     */
    @RequestMapping(value = "getFlowerLanguageList", method = RequestMethod.POST)
    public JsonModel getFlowerLanguageList(Integer page, Integer number, FlowerLanguageForm flowerLanguageForm) {
        JsonModel jsonModel = new JsonModel();
        try {
            jsonModel = adminFlowerLanguageService.getFlowerLanguageList(page, number, flowerLanguageForm);
            jsonModel.setMsg("加载成功");
        } catch (Exception e) {
            jsonModel.setMsg("网络错误！请刷新重试");
        }
        return jsonModel;
    }

    /**
     * 新增每日花语
     *
     * @param flowerLanguageForm
     * @return
     */
    @RequestMapping(value = "/addFlowerLanguage", method = RequestMethod.POST)
    public JsonModel addFlowerLanguage(FlowerLanguageForm flowerLanguageForm) {
        return adminFlowerLanguageService.addFlowerLanguage(flowerLanguageForm);
    }

    /**
     * 更新每日花语
     *
     * @param flowerLanguageForm
     * @return
     */
    @RequestMapping(value = "/updateFlowerLanguage", method = RequestMethod.POST)
    public JsonModel updateFlowerLanguage(FlowerLanguageForm flowerLanguageForm) {
        return adminFlowerLanguageService.updateFlowerLanguage(flowerLanguageForm);
    }

    /**
     * 删除每日花语
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteFlowerLanguage/{id}", method = RequestMethod.GET)
    public JsonModel deleteFlowerLanguage(@PathVariable Integer id) {
        return adminFlowerLanguageService.deleteFlowerLanguage(id);
    }

}
