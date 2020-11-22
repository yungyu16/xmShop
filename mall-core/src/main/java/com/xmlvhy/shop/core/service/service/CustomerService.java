package com.xmlvhy.shop.core.service.service;

import com.xmlvhy.shop.core.dal.params.CustomerParam;
import com.xmlvhy.shop.core.pojo.Customer;
import com.xmlvhy.shop.core.service.vo.CustomerVo;

import java.util.List;

/**
 * Author: 小莫
 * Date: 2019-03-12 17:26
 * Description: 客户相关业务接口
 */
public interface CustomerService {
    Customer login(String loginName, String password);

    Customer findByPhone(String phone);

    Customer regist(CustomerVo customerVo);

    Boolean modifyCustomerPassword(Customer customer);

    Boolean findByLoginName(String loginName);

    List<Customer> findAllCustomers();

    List<Customer> findCustomersByParams(CustomerParam customerParam);

    Customer findCustomerId(int id);

    Boolean modifyCustomer(CustomerVo customerVo);

    Boolean modifyCustomerStatus(int id);

    Boolean modifyCenterCustomer(Integer customerId, String mobile, String address);

}
