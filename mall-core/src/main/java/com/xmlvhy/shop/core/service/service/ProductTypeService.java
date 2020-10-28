package com.xmlvhy.shop.core.service.service;

import com.xmlvhy.shop.core.common.exception.ProductTypeExistException;
import com.xmlvhy.shop.core.pojo.ProductType;

import java.util.List;

/**
 * Author: 小莫
 * Date: 2019-03-09 11:34
 * Description: 商品类型信息业务层接口
 */
public interface ProductTypeService {

    /**
     * 功能描述: 查询所有商品类型信息
     *
     * @return java.util.List<com.xmlvhy.shop.pojo.ProductType>
     * @Author 小莫
     * @Date 11:35 2019/03/09
     * @Param []
     */
    List<ProductType> findAll();

    List<ProductType> findAllValidStatus();

    /**
     * 功能描述: 添加商品类型
     *
     * @return void
     * @Author 小莫
     * @Date 21:47 2019/03/09
     * @Param [name]
     */
    void addProductType(String name) throws ProductTypeExistException;

    /**
     * 功能描述: 根据 id 查询商品类型信息
     *
     * @return com.xmlvhy.shop.pojo.ProductType
     * @Author 小莫
     * @Date 21:47 2019/03/09
     * @Param [id]
     */
    ProductType findProductTypeById(Integer id);

    /**
     * 功能描述: 修改商品类型的名称
     *
     * @return int
     * @Author 小莫
     * @Date 22:13 2019/03/09
     * @Param [id, name]
     */
    int modifyProductTypeName(int id, String name);

    /**
     * 功能描述: 修改商品类型的状态
     *
     * @return int
     * @Author 小莫
     * @Date 22:13 2019/03/09
     * @Param [id, status]
     */
    int modifyProductTypeStatus(int id);

    /**
     * 功能描述: 根据id删除商品类型信息
     *
     * @return int
     * @Author 小莫
     * @Date 22:14 2019/03/09
     * @Param [id]
     */
    int removeProductTypeById(int id);

    List<ProductType> findAllEnableProductTypes();
}
