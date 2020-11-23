package com.xmlvhy.shop.dao;

import java.util.List;

/**
 * CreatedDate: 2020/11/23
 * Author: songjialin
 */
public interface BannerDao {
    List<String> collectAllBanner();

    String getDefaultBanner();
}
