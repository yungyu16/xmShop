package com.xmlvhy.shop.admin.dal.mapper;

import com.xmlvhy.shop.admin.dal.params.ProductParam;
import com.xmlvhy.shop.admin.pojo.Product;

import java.util.List;

/**
 * Author: 小莫
 * Date: 2019-03-10 10:39
 * Description: 商品数据操作层接口
 */
public interface ProductDao {

    int insertProduct(Product product);

    Product selectByProductName(String name);

    List<Product> selectAllProducts();

    Product selectProductById(int id);

    int updateProduct(Product product);

    int deleteProductById(int id);

    List<Product> selectByProductParams(ProductParam productParam);
}
