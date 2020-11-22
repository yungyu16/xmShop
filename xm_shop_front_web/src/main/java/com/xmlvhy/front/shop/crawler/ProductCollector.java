package com.xmlvhy.front.shop.crawler;

import java.util.List;

/**
 * @author Yungyu
 * @description Created by Yungyu on 2020/11/22.
 */
public interface ProductCollector {
    List<ProductItem> collect(String keyword);
}
