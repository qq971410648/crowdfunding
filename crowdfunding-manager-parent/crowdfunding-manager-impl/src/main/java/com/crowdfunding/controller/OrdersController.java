package com.crowdfunding.controller;

import com.crowdfunding.ajax.AjaxResult;
import com.crowdfunding.domain.Orders;
import com.crowdfunding.service.OrdersService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    static Logger logger = Logger.getLogger(OrdersController.class);

    /**
     * 查询指定Orders的信息  及  对应的product信息       多对多
     * @param id
     * @return
     */
    @RequestMapping("/getOrdersAndProduct")
    public AjaxResult getOrdersAndProduct(int id){
        AjaxResult ajaxResult = new AjaxResult();

        try {
            Orders orders = ordersService.getOrdersAndProduct(id);
            logger.info(orders);
            ajaxResult.setData(orders);
            ajaxResult.setSuccess(true);
            return ajaxResult;
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(false,"查询失败");
        }
    }
}
