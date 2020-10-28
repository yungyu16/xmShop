package com.xmlvhy.shop.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xmlvhy.shop.common.constant.PaginationConstant;
import com.xmlvhy.shop.common.exception.CustomerNotFoundException;
import com.xmlvhy.shop.common.utils.ResponseResult;
import com.xmlvhy.shop.params.CustomerParam;
import com.xmlvhy.shop.pojo.Customer;
import com.xmlvhy.shop.service.CustomerService;
import com.xmlvhy.shop.vo.CustomerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Author: 小莫
 * Date: 2019-03-11 21:51
 * Description: 客户相关接口
 */
@Controller
@RequestMapping("/admin/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /**
     * 功能描述: 获取所有的用户信息
     *
     * @return java.lang.String
     * @Author 小莫
     * @Date 11:11 2019/03/14
     * @Param [pageNum, model]
     */
    @RequestMapping("getAllCustomers")
    public String getAllCustomers(Integer pageNum, Model model) {
        if (ObjectUtils.isEmpty(pageNum)) {
            pageNum = PaginationConstant.PAGE_NUM;
        }
        PageHelper.startPage(pageNum, PaginationConstant.PAGE_SIZE);
        List<Customer> customerList = customerService.findAllCustomers();

        PageInfo<Customer> pageInfo = new PageInfo<>(customerList);
        model.addAttribute("pageInfo", pageInfo);
        return "customerManager";
    }

    /**
     * 功能描述: 通过多条件查询获取获取用户信息
     *
     * @return java.lang.String
     * @Author 小莫
     * @Date 11:11 2019/03/14
     * @Param [customerParam, pageNum, model]
     */
    @RequestMapping("getAllCustomersByParams")
    public String getAllCustomersByParams(CustomerParam customerParam, Integer pageNum, Model model) {
        if (ObjectUtils.isEmpty(pageNum)) {
            pageNum = PaginationConstant.PAGE_NUM;
        }
        PageHelper.startPage(pageNum, PaginationConstant.PAGE_SIZE);
        List<Customer> customerList = customerService.findCustomersByParams(customerParam);
        PageInfo<Customer> pageInfo = new PageInfo<>(customerList);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("params", customerParam);

        return "customerManager";
    }

    /**
     * 功能描述: 展示一个用户的信息
     *
     * @return com.xmlvhy.shop.common.utils.ResponseResult
     * @Author 小莫
     * @Date 11:11 2019/03/14
     * @Param [id]
     */
    @RequestMapping("showCustomer")
    @ResponseBody
    public ResponseResult showCustomer(int id) {
        try {
            Customer customer = customerService.findCustomerId(id);
            return ResponseResult.success("获取用户信息成功", customer);
        } catch (CustomerNotFoundException e) {
            return ResponseResult.fail(e.getMessage());
        }
    }

    /**
     * 功能描述: 修改客户信息
     *
     * @return java.lang.String
     * @Author 小莫
     * @Date 13:55 2019/03/14
     * @Param [customerVo, pageNum, model]
     */
    @RequestMapping("modifyCustomer")
    public String modifyCustomer(CustomerVo customerVo, Integer pageNum, Model model) {
        if (ObjectUtils.isEmpty(pageNum)) {
            pageNum = PaginationConstant.PAGE_NUM;
        }
        if (customerService.modifyCustomer(customerVo)) {
            model.addAttribute("successMsg", "修改成功");
        } else {
            model.addAttribute("failMsg", "修改失败");
        }
        return "forward:getAllCustomers?pageNum=" + pageNum;
    }

    /**
     * 功能描述: 禁用启用客户账户
     *
     * @return com.xmlvhy.shop.common.utils.ResponseResult
     * @Author 小莫
     * @Date 13:56 2019/03/14
     * @Param [id]
     */
    @RequestMapping("modifyCustomerStatus")
    @ResponseBody
    public ResponseResult modifyCustomerStatus(int id) {
        if (customerService.modifyCustomerStatus(id)) {
            return ResponseResult.success("状态修改成功");
        } else {
            return ResponseResult.success("状态修改失败");
        }
    }
}
