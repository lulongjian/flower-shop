package com.example.mall.service;

import com.example.common.utils.JsonModel;
import com.example.mall.dao.CarMapper;
import com.example.mall.dao.UserMapper;
import com.example.mall.domain.CarDO;
import com.example.mall.domain.UserDO;
import com.example.mall.domain.form.CarForm;
import com.example.mall.domain.form.OrderForm;
import com.example.mall.domain.form.ProductForm;
import com.example.mall.domain.vo.CarVO;
import com.example.mall.dao.ProductMapper;
import com.example.mall.domain.ProductDO;
import com.example.mall.utils.Base64Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarMapper carMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CarService carService;

    /**
     * 根据用户id和商品id查询商品时候存在购物车中
     *
     * @param carForm
     * @return
     */
    public List<CarDO> selectCarByUserId(CarForm carForm) {
        CarDO carDO = new CarDO();
        carDO.setUserId(carForm.getUserId());
        carDO.setProductId(carForm.getProductId());
        return carMapper.select(carDO);
    }

    /**
     * 添加购物车
     *
     * @param carForm
     */
    public void addCar(CarForm carForm) {
        //更新商品得收藏数
        ProductDO productDO = productMapper.selectByPrimaryKey(carForm.getProductId());
        ProductDO newProduct = new ProductDO();
        newProduct.setId(productDO.getId());
        newProduct.setCollectNum(productDO.getCollectNum() + 1);
        productMapper.updateByPrimaryKeySelective(newProduct);

        //判断商品是否已存在购物车，存在则返回
        List<CarDO> carList = carService.selectCarByUserId(carForm);
        if (carList.size() != 0) {
            for (CarDO carDO : carList) {
                carDO.setNumber(carDO.getNumber() + 1);
                carMapper.updateByPrimaryKeySelective(carDO);
            }
            return;
        }
        CarDO carDO = new CarDO();
        carDO.setUserId(carForm.getUserId());
        carDO.setProductId(carForm.getProductId());
        carDO.setNumber(1);
        carMapper.insert(carDO);

        return;
    }

    /**
     * 查询商品列表
     *
     * @param carForm
     * @return
     */
    public List<CarVO> findCarList(CarForm carForm) {
        List<CarVO> carVOList = carMapper.findCarList(carForm);
        //封装购物车中商品的用户名
        if (carVOList.size() > 0) {
            for (int i = 0; i < carVOList.size(); i++) {
                UserDO userDO = userMapper.selectByPrimaryKey(carVOList.get(i).getProductUserId());
                if (userDO != null) {
                    carVOList.get(i).setProductUserName(userDO.getName());
                }
                // 补充base64

                String imageStr = Base64Utils.GetImageStr(carVOList.get(i).getImgUrl());
                carVOList.get(i).setImgUrlBase64(imageStr);

            }
        }

        return carVOList;
    }

    /**
     * 删除购物车商品
     */
    public void delete(String id) {
        CarDO carDO = carMapper.selectByPrimaryKey(id);
        carMapper.deleteByPrimaryKey(id);
        //更新商品收藏数
        ProductDO productDO = productMapper.selectByPrimaryKey(carDO.getProductId());
        ProductDO newProduct = new ProductDO();
        newProduct.setId(productDO.getId());
        newProduct.setCollectNum(productDO.getCollectNum() - 1);
        productMapper.updateByPrimaryKeySelective(newProduct);
    }

    /**
     * 更新购物车
     *
     * @param carForm
     */
    public void updateCar(CarForm carForm) {
        CarDO carDO = carMapper.selectByPrimaryKey(carForm.getId());
        carDO.setNumber(carForm.getNumber());
        carMapper.updateByPrimaryKeySelective(carDO);
    }

    /**
     * 获取选择列表的价格
     *
     * @param orderForm
     * @return
     */
    public JsonModel getSelectPrice(OrderForm orderForm) {
        JsonModel jsonModel = new JsonModel();
        BigDecimal allMoney = new BigDecimal(0);
        List<ProductForm> productFormList = orderForm.getProductFormList();
        for (ProductForm productForm : productFormList) {
            BigDecimal multiply = productForm.getPrice().multiply(new BigDecimal(productForm.getNumber()));
            allMoney = allMoney.add(multiply);
        }
        jsonModel.setData(allMoney);
        return jsonModel;
    }
}
