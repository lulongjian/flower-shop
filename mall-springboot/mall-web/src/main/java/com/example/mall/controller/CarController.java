package com.example.mall.controller;

import com.example.common.utils.JsonModel;
import com.example.mall.domain.form.CarForm;
import com.example.mall.domain.CarDO;
import com.example.mall.domain.form.OrderForm;
import com.example.mall.domain.vo.CarVO;
import com.example.mall.service.CarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("car")
@Slf4j
public class CarController {

    @Autowired
    private CarService carService;

    /**
     * 获取选择列表的价格
     */
    @RequestMapping(value = "getSelectPrice", method = RequestMethod.POST)
    public JsonModel getSelectPrice(@RequestBody OrderForm orderForm) {
        return carService.getSelectPrice(orderForm);
    }

    /**
     * 添加购物车
     */
    @RequestMapping(value = "addCar", method = RequestMethod.POST)
    public JsonModel addCar(CarForm carForm) {
        JsonModel jsonModel = new JsonModel();
        //添加购物车
        carService.addCar(carForm);
        jsonModel.setMsg("添加购物车成功");
        return jsonModel;
    }

    /**
     * 更新购物车
     *
     * @param carForm
     * @return
     */
    @RequestMapping(value = "updateCar", method = RequestMethod.POST)
    public JsonModel updateCar(CarForm carForm) {
        JsonModel jsonModel = new JsonModel();
        //添加购物车
        carService.updateCar(carForm);
        jsonModel.setMsg("更新购物车成功");
        return jsonModel;
    }

    /**
     * 查询购物车列表
     */
    @RequestMapping(value = "carList", method = RequestMethod.POST)
    public JsonModel findCarList(CarForm carForm) {
        JsonModel jsonModel = new JsonModel();
        List<CarVO> carList = carService.findCarList(carForm);
        jsonModel.setData(carList);
        jsonModel.setCode(0);
        jsonModel.setMsg("查询成功");
        jsonModel.setCount((long) carList.size());
        return jsonModel;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "deleteCar", method = RequestMethod.POST)
    public JsonModel delete(String id) {
        JsonModel jsonModel = new JsonModel();
        carService.delete(id);
        jsonModel.setMsg("删除成功");
        return jsonModel;
    }
}
