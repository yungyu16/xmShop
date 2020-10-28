package com.xmlvhy.shop.core.service.service.impl;

import com.xmlvhy.shop.core.common.constant.SystemUserConstant;
import com.xmlvhy.shop.core.common.exception.SystemUserLoginException;
import com.xmlvhy.shop.core.dal.mapper.SystemUserDao;
import com.xmlvhy.shop.core.dal.params.SystemUserParam;
import com.xmlvhy.shop.core.pojo.Role;
import com.xmlvhy.shop.core.pojo.SystemUser;
import com.xmlvhy.shop.core.service.service.SystemUserService;
import com.xmlvhy.shop.core.service.vo.SystemUserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Author: 小莫
 * Date: 2019-03-11 10:30
 * Description: 系统用户管理业务
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class SystemUserServiceImpl implements SystemUserService {

    @Autowired
    private SystemUserDao systemUserDao;

    /**
     * 功能描述: 查询所有系统用户
     *
     * @return java.util.List<com.xmlvhy.shop.pojo.SystemUser>
     * @Author 小莫
     * @Date 10:32 2019/03/11
     * @Param []
     */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public List<SystemUser> findAllSytemUsers() {
        return systemUserDao.selectAllSytemUsers();
    }

    /**
     * 功能描述: 根据 id查询某一具体系统用户信息
     *
     * @return com.xmlvhy.shop.pojo.SystemUser
     * @Author 小莫
     * @Date 10:32 2019/03/11
     * @Param [id]
     */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public SystemUser findSystemUserById(int id) {
        return systemUserDao.selectSystemUserById(id);
    }

    /**
     * 功能描述: 插入一条系统用户信息
     *
     * @return int
     * @Author 小莫
     * @Date 10:32 2019/03/11
     * @Param [systemUser]
     */
    @Override
    public int addSystemUser(SystemUserVo systemUserVo) {

        SystemUser systemUser = new SystemUser();
        Role role = new Role();

        BeanUtils.copyProperties(systemUserVo, systemUser);

        //默认新创建的用户有效
        systemUser.setIsValid(SystemUserConstant.SYSTEM_USER_IS_VALIDAT);

        role.setId(systemUserVo.getRoleId());
        systemUser.setRole(role);
        //当前时间即为创建时间
        systemUser.setCreateDate(new Date());

        return systemUserDao.insertSystemUser(systemUser);
    }

    /**
     * 功能描述: 修改系统用户信息
     *
     * @return int
     * @Author 小莫
     * @Date 17:46 2019/03/11
     * @Param [systemUserVo]
     */
    @Override
    public int modifySystemUser(SystemUserVo systemUserVo) {

        SystemUser systemUser = systemUserDao.selectSystemUserById(systemUserVo.getId());
        if (systemUser != null) {
            Role role = new Role();
            role.setId(systemUserVo.getRoleId());
            systemUser.setRole(role);
            systemUser.setName(systemUserVo.getName());
            systemUser.setEmail(systemUserVo.getEmail());
            systemUser.setPhone(systemUserVo.getPhone());
        }
        return systemUserDao.updateSystemUser(systemUser);
    }

    /**
     * 功能描述: 启用 禁用系统用户状态
     *
     * @return int
     * @Author 小莫
     * @Date 17:47 2019/03/11
     * @Param [id]
     */
    @Override
    public int modifySystemUserStatus(int id) {
        SystemUser systemUser = systemUserDao.selectSystemUserById(id);
        int isValid = systemUser.getIsValid();
        if (systemUser != null) {
            if (isValid == SystemUserConstant.SYSTEM_USER_IS_VALIDAT) {
                //如果是启用状态，则改为禁用
                isValid = SystemUserConstant.SYSTEM_USER_IS_INVALIDAT;
            } else {
                isValid = SystemUserConstant.SYSTEM_USER_IS_VALIDAT;
            }
        }
        return systemUserDao.updateSystemUserStatus(id, isValid);
    }

    /**
     * 功能描述: 多条件查询系统用户列表
     *
     * @return java.util.List<com.xmlvhy.shop.pojo.SystemUser>
     * @Author 小莫
     * @Date 17:47 2019/03/11
     * @Param [systemUserParam]
     */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public List<SystemUser> findSystemUsersByParams(SystemUserParam systemUserParam) {
        return systemUserDao.selectSystemUserByParams(systemUserParam);
    }

    /**
     * 功能描述: 查询登录名是否可用
     *
     * @return com.xmlvhy.shop.pojo.SystemUser
     * @Author 小莫
     * @Date 20:18 2019/03/11
     * @Param [loginName]
     */
    @Override
    public SystemUser findSystemUserByLoginName(String loginName) {
        return systemUserDao.selectSystemUserByLoginName(loginName);
    }

    /**
     * 功能描述: 实现登录功能
     *
     * @return com.xmlvhy.shop.pojo.SystemUser
     * @Author 小莫
     * @Date 11:35 2019/03/12
     * @Param [loginName, password]
     */
    @Override
    public SystemUser login(String loginName, String password) throws SystemUserLoginException {
        SystemUser systemUser = systemUserDao.selectSystemUserByLoginNameAndPassword(loginName,
                password, SystemUserConstant.SYSTEM_USER_IS_VALIDAT);
        if (systemUser != null) {
            return systemUser;
        }
        throw new SystemUserLoginException("用户名或密码不正确");
    }
}
