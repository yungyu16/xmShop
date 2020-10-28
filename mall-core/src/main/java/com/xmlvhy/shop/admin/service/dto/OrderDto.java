package com.xmlvhy.shop.admin.service.dto;

import com.xmlvhy.shop.admin.pojo.Order;
import com.xmlvhy.shop.admin.pojo.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Author: 小莫
 * Date: 2019-03-26 14:52
 * Description:<描述>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private List<Order> orderList;
    private List<OrderItem> orderItemList;
}
