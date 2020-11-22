package com.xmlvhy.shop.service.impl;

import com.xmlvhy.shop.dao.OrderItemDao;
import com.xmlvhy.shop.pojo.OrderItem;
import com.xmlvhy.shop.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Author: 小莫
 * Date: 2019-03-24 16:03
 * Description:<描述>
 */
@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemDao orderItemDao;

    /**
     *功能描述: 保存订单明细
     * @Author 小莫
     * @Date 23:14 2019/03/25
     * @Param [orderItem]
     * @return java.lang.Boolean
     */
    @Override
    public Boolean saveOrderItem(OrderItem orderItem) {
        int rows = orderItemDao.insertOrderItem(orderItem);
        if (rows >= 1) {
            return true;
        }
        return false;
    }

    /**
     *功能描述: 根据订单id 查询所属的所有订单明细
     * @Author 小莫
     * @Date 23:15 2019/03/25
     * @Param [id]
     * @return java.util.List<com.xmlvhy.shop.pojo.OrderItem>
     */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public List<OrderItem> findOrderItemsByOrderId(Integer id) {
        List<OrderItem> orderItems = orderItemDao.selectOrderItemsByOrder(id);
        return orderItems;
    }
}
