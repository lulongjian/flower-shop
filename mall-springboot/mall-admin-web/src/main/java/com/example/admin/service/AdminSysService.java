package com.example.admin.service;

import com.example.admin.domain.vo.EchartsVO;
import com.example.admin.domain.vo.SysMsgVO;
import com.example.mall.dao.ProductMapper;
import com.example.mall.dao.RecommendMapper;
import com.example.mall.dao.UserMapper;
import com.example.mall.dao.UserOrderDOMapper;
import com.example.mall.domain.ProductDO;
import com.example.mall.domain.UserDO;
import com.example.mall.domain.UserOrderDO;
import com.example.mall.domain.form.RecommendForm;
import com.example.mall.domain.vo.RecommendVO;
import com.example.mall.utils.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class AdminSysService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private UserOrderDOMapper userOrderDOMapper;

    @Autowired
    private RecommendMapper recommendMapper;

    /**
     * 管理员首页获取信息
     *
     * @return
     */
    public SysMsgVO getSystemData() {
        //已授权用户数
        Example authUserExample = new Example(UserDO.class);
        authUserExample.createCriteria().andEqualTo("permission", "1");
        int authUserNum = userMapper.selectCountByExample(authUserExample);

        //已授权商品数
        Example authProductExample = new Example(ProductDO.class);
        authProductExample.createCriteria().andEqualTo("permission", "1");
        int authProductNum = productMapper.selectCountByExample(authProductExample);

        // 全部订单
        int userOrderNum = userOrderDOMapper.selectCount(new UserOrderDO());

        // 今日推荐
        RecommendForm recommendForm = new RecommendForm();
        recommendForm.setReTime(DateFormatUtils.DateToStr(new Date()));
        List<RecommendVO> recommendList = recommendMapper.getRecommendList(recommendForm);

        // 有效和无效订单
        UserOrderDO userOrderDO = new UserOrderDO();
        userOrderDO.setStatus(1);
        int orderNum1 = userOrderDOMapper.selectCount(userOrderDO);
        userOrderDO.setStatus(2);
        int orderNum2 = userOrderDOMapper.selectCount(userOrderDO);
        userOrderDO.setStatus(3);
        int orderNum3 = userOrderDOMapper.selectCount(userOrderDO);
        userOrderDO.setStatus(0);
        int orderNum0 = userOrderDOMapper.selectCount(userOrderDO);

        // 折线图列表（近七天）
        List<EchartsVO> echartsVOList = new ArrayList<>();
        List<String> listPastDay = this.getListPastDay();
        for (String date : listPastDay) {

            int num = userOrderDOMapper.getOrderNumByCrtDate(date);

            EchartsVO echartsVO = new EchartsVO();
            echartsVO.setDate(date);
            echartsVO.setNum(num);

            echartsVOList.add(echartsVO);
        }

        SysMsgVO sysMsgVO = new SysMsgVO();
        sysMsgVO.setAuthUserNum(authUserNum);
        sysMsgVO.setAuthProductNum(authProductNum);
        sysMsgVO.setUserOrderNum(userOrderNum);
        sysMsgVO.setRecommendNum(recommendList.size());
        sysMsgVO.setOrderNum1(orderNum1);
        sysMsgVO.setOrderNum2(orderNum2);
        sysMsgVO.setOrderNum3(orderNum3);
        sysMsgVO.setOrderNum0(orderNum0);
        sysMsgVO.setOrderList(echartsVOList);

        return sysMsgVO;
    }

    /**
     * 获取过去7天内的日期数组
     *
     * @return 日期数组
     */
    public List<String> getListPastDay() {

        List<String> pastDaysList = new ArrayList<>();

        try {
            Date date = new Date();

            for (int i = 6; i >= 0; i--) {

                pastDaysList.add(getPastDate(i, date));

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return pastDaysList;

    }

    /**
     * 获取过去第几天的日期
     *
     * @param past
     * @return
     */
    public static String getPastDate(int past, Date date) {

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);

        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - past);

        Date today = calendar.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String result = sdf.format(today);

        return result;

    }
}
