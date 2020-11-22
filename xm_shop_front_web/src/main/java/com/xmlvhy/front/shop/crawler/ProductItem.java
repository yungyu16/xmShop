package com.xmlvhy.front.shop.crawler;

import lombok.Getter;

import java.util.regex.Pattern;

/**
 * @author Yungyu
 * @description Created by Yungyu on 2020/11/22.
 */
@Getter
public class ProductItem {
    private static Pattern pattern = Pattern.compile("/n[\\d]/");
    private String itemName;
    private String itemDesc;
    private Double itemPrice;
    private String itemImage;

    public static ProductItem valueOf(String name, String price, String desc, String img) {
        ProductItem item = new ProductItem();
        name = name.replace("京东", "");
        desc = desc.replace("京东", "");
        //高清图
        img = pattern.matcher(img).replaceFirst("/cv/");
        item.itemName = formatProductName(name);
        item.itemPrice = Double.valueOf(price);
        item.itemDesc = desc;
        item.itemImage = img;
        return item;
    }

    private static String formatProductName(String name) {
        if (name == null) {
            return null;
        }
        return name;
    }
}
