package com.example.admin.controller;

import com.example.admin.service.AdminRecommendService;
import com.example.common.utils.JsonModel;
import com.example.mall.domain.RecommendDO;
import com.example.mall.domain.UserDO;
import com.example.mall.domain.form.RecommendForm;
import com.example.mall.domain.form.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/recommend")
public class adminRecommendController {

    @Autowired
    private AdminRecommendService adminRecommendService;

    /**
     * 管理员获取每日推荐
     *
     * @return
     */
    @RequestMapping(value = "getRecommendList", method = RequestMethod.POST)
    public JsonModel getRecommendList(Integer page, Integer number, RecommendForm recommendForm) {
        JsonModel jsonModel = new JsonModel();
        try {
            jsonModel = adminRecommendService.getRecommendList(page, number, recommendForm);
            jsonModel.setMsg("加载成功");
        } catch (Exception e) {
            jsonModel.setMsg("网络错误！请刷新重试");
        }
        return jsonModel;
    }

    /**
     * 新增每日推荐
     *
     * @param recommendForm
     * @return
     */
    @RequestMapping(value = "/addRecommend", method = RequestMethod.POST)
    public JsonModel addRecommendRequest(RecommendForm recommendForm) {
        return adminRecommendService.addRecommendRequest(recommendForm);
    }

    /**
     * 更新每日推荐
     *
     * @param recommendForm
     * @return
     */
    @RequestMapping(value = "/updateRecommend", method = RequestMethod.POST)
    public JsonModel updateRecommendRequest(RecommendForm recommendForm) {
        return adminRecommendService.updateRecommendRequest(recommendForm);
    }

    /**
     * 删除每日推荐
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteRecommend/{id}", method = RequestMethod.GET)
    public JsonModel deleteRecommend(@PathVariable Integer id) {
        return adminRecommendService.deleteRecommend(id);
    }

}
