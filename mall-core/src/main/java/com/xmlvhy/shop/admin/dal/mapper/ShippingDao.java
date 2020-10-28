package com.xmlvhy.shop.admin.dal.mapper;

import com.xmlvhy.shop.admin.pojo.Shipping;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Author: 小莫
 * Date: 2019-03-23 11:39
 * Description:<描述>
 */
public interface ShippingDao {

    Shipping selectShippingByCustomerIdAndShippingId(@Param("customerId") Integer customerId,
                                                     @Param("shippingId") Integer shippingId);

    List<Shipping> selectAllShippings(@Param("customerId") Integer customerId, @Param("status") Integer status);

    int insertShipping(Shipping shipping);

    int deleteShippingByIdAndCustomerId(@Param("shippingId") Integer shippingId,
                                        @Param("customerId") Integer customerId,
                                        @Param("status") int status,
                                        @Param("updateTime") Date updateTime);

    int updateByShipping(Shipping shipping);
}
