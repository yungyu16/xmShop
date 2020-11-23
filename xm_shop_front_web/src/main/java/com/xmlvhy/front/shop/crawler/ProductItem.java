package com.xmlvhy.front.shop.crawler;

import lombok.Getter;

/**
 * @author Yungyu
 * @description Created by Yungyu on 2020/11/22.
 */
@Getter
public class ProductItem {
    private String itemName;
    private String itemDesc;
    private Double itemPrice;
    private String itemImage;

    public static ProductItem valueOf(String name, Double price, String desc, String img) {
        ProductItem item = new ProductItem();

        item.itemName = name;
        item.itemPrice = price;
        item.itemDesc = desc;
        item.itemImage = img;
        return item;
    }
}
