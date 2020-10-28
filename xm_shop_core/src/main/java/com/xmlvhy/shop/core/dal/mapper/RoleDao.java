package com.xmlvhy.shop.core.dal.mapper;

import com.xmlvhy.shop.core.pojo.Role;

import java.util.List;

/**
 * Author: 小莫
 * Date: 2019-03-11 11:29
 * Description: 角色接口层
 */
public interface RoleDao {
    List<Role> selectAllRoles();
}
