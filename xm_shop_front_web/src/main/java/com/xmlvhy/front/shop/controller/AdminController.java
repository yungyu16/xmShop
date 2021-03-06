package com.xmlvhy.front.shop.controller;

import com.alibaba.fastjson.JSON;
import com.xmlvhy.front.shop.crawler.ProductCollector;
import com.xmlvhy.shop.dao.ProductDao;
import com.xmlvhy.shop.dao.ProductTypeDao;
import com.xmlvhy.shop.pojo.Product;
import com.xmlvhy.shop.pojo.ProductType;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Yungyu
 * @description Created by Yungyu on 2020/11/22.
 */
@Slf4j
@RestController
@RequestMapping("admin")
@RequiredArgsConstructor
public class AdminController {
    private final List<ProductCollector> collectors;
    private final ProductTypeDao productTypeDao;
    private final ProductDao productDao;

    @RequestMapping("add-products")
    public String addProducts(RefreshActionParam actionParam) {
        log.info("请求参数：{}", JSON.toJSONString(actionParam));
        if (CollectionUtils.isEmpty(collectors)) {
            return "no any collector";
        }
        String keyword = actionParam.getKeyword();
        String typeName = actionParam.getTypeName();
        Integer typeId = actionParam.getTypeId();
        if (keyword == null || keyword.length() == 0) {
            return "keyword is null";
        }
        ProductType productType;
        if (typeId != null) {
            productType = productTypeDao.selectProductTypeById(typeId);
        } else {
            if (typeName == null || typeName.length() == 0) {
                return "typeName is null";
            }
            synchronized (this) {
                productType = productTypeDao.selectProductTypeByName(typeName);
                if (productType == null) {
                    productTypeDao.insertProductType(typeName, 1);
                    productType = productTypeDao.selectProductTypeByName(typeName);
                }
            }
        }
        if (productType == null) {
            return "no matched typeName";
        }
        keyword = keyword.trim();
        if (keyword.isEmpty()) {
            return "keyword is null";
        }
        String finalKeyword = keyword;
        ProductType finalProductType = productType;
        log.info("finalKeyword：{} finalProductType：{}", finalKeyword, finalProductType.getName());
        List<Product> products = collectors.stream()
                .flatMap(it -> it.collect(finalKeyword).stream())
                .map(it -> {
                    Product product = new Product();
                    product.setName(it.getItemName());
                    product.setPrice(BigDecimal.valueOf(it.getItemPrice()));
                    product.setInfo(it.getItemDesc());
                    product.setImage(it.getItemImage());
                    product.setProductType(finalProductType);
                    return product;
                }).collect(Collectors.toList());
        products.forEach(it -> {
            try {
                productDao.insertProduct(it);
            } catch (Exception e) {
                log.error("插入商品异常", e);
            }
        });
        return "finish! total size:" + products.size();
    }

    @Data
    public static class RefreshActionParam {
        private String keyword;
        private String typeName;
        private Integer typeId;
    }
}
