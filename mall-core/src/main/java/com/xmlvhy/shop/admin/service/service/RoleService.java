package com.xmlvhy.shop.admin.service.service;

import com.xmlvhy.shop.admin.pojo.Role;

import java.util.List;

/**
 * Author: 小莫
 * Date: 2019-03-11 11:32
 * Description: 角色业务层接口
 */
public interface RoleService {

    List<Role> findAllRoles();

}
