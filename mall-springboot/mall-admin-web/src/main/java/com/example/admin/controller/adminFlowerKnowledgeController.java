package com.example.admin.controller;

import com.example.admin.service.AdminFlowerKnowledgeService;
import com.example.common.utils.JsonModel;
import com.example.mall.domain.form.FlowerKnowledgeForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/flower/knowledge")
public class adminFlowerKnowledgeController {

    @Autowired
    private AdminFlowerKnowledgeService adminFlowerKnowledgeService;

    /**
     * 管理员获取养花知识
     *
     * @return
     */
    @RequestMapping(value = "getFlowerKnowledgeList", method = RequestMethod.POST)
    public JsonModel getFlowerKnowledgeList(Integer page, Integer number, FlowerKnowledgeForm flowerKnowledgeForm) {
        JsonModel jsonModel = new JsonModel();
        try {
            jsonModel = adminFlowerKnowledgeService.getFlowerKnowledgeList(page, number, flowerKnowledgeForm);
            jsonModel.setMsg("加载成功");
        } catch (Exception e) {
            e.printStackTrace();
            jsonModel.setMsg("网络错误！请刷新重试");
        }
        return jsonModel;
    }

    /**
     * 新增养花知识
     *
     * @param flowerKnowledgeForm
     * @return
     */
    @RequestMapping(value = "/addFlowerKnowledge", method = RequestMethod.POST)
    public JsonModel addFlowerKnowledge(FlowerKnowledgeForm flowerKnowledgeForm) {
        return adminFlowerKnowledgeService.addFlowerKnowledge(flowerKnowledgeForm);
    }

    /**
     * 更新养花知识
     *
     * @param flowerKnowledgeForm
     * @return
     */
    @RequestMapping(value = "/updateFlowerKnowledge", method = RequestMethod.POST)
    public JsonModel updateFlowerKnowledge(FlowerKnowledgeForm flowerKnowledgeForm) {
        return adminFlowerKnowledgeService.updateFlowerKnowledge(flowerKnowledgeForm);
    }

    /**
     * 删除养花知识
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteFlowerKnowledge/{id}", method = RequestMethod.GET)
    public JsonModel deleteFlowerKnowledge(@PathVariable Integer id) {
        return adminFlowerKnowledgeService.deleteFlowerKnowledge(id);
    }

}
