package com.xmlvhy.front.shop.controller;

import com.beust.jcommander.internal.Maps;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xmlvhy.shop.common.constant.PaginationConstant;
import com.xmlvhy.shop.dao.BannerDao;
import com.xmlvhy.shop.params.ProductParam;
import com.xmlvhy.shop.pojo.Product;
import com.xmlvhy.shop.pojo.ProductType;
import com.xmlvhy.shop.service.ProductService;
import com.xmlvhy.shop.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Author: 小莫
 * Date: 2019-03-12 13:42
 * Description: 前台商品接口
 */
@Controller
@RequestMapping("/front/product")
public class ProductController {

    @Autowired
    private ProductTypeService productTypeService;

    @Autowired
    private ProductService productService;

    @Autowired
    private BannerDao bannerDao;

    /**
     * 功能描述: 加载所有商品列表
     *
     * @return java.lang.String
     * @Author 小莫
     * @Date 9:47 2019/03/14
     * @Param [productParam, pageName, model]
     */
    @RequestMapping("/searchAllProducts")
    public String searchAllProducts(HttpServletRequest httpRequest, ProductParam productParam, Integer pageName, Model model) {
        if (ObjectUtils.isEmpty(pageName)) {
            pageName = PaginationConstant.PAGE_NUM;
        }
        PageHelper.startPage(pageName, PaginationConstant.FRONT_PAGE_SIZE);
        List<Product> productList = productService.findByProductParams(productParam);
        PageInfo<Product> pageInfo = new PageInfo<>(productList);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("curPage", pageName);
        model.addAttribute("productParam", productParam);
        Map<String, String> banners = Maps.newHashMap();
        banners.put("banner1", httpRequest.getContextPath() + "/images/banner1.png");
        banners.put("banner2", httpRequest.getContextPath() + "/images/banner2.png");
        banners.put("banner3", httpRequest.getContextPath() + "/images/banner3.png");
        banners.put("banner4", httpRequest.getContextPath() + "/images/banner4.png");
        AtomicInteger idx = new AtomicInteger(1);
        bannerDao.collectAllBanner()
                .forEach(it -> banners.put("banner" + idx.getAndIncrement(), it));
        model.addAttribute("banners", banners);
        return "main";
    }

    /**
     * 功能描述: 页面初始化
     *
     * @return java.util.List<com.xmlvhy.shop.pojo.ProductType>
     * @Author 小莫
     * @Date 9:47 2019/03/14
     * @Param []
     */
    @ModelAttribute("productTypes")
    public List<ProductType> loadProductTypes() {
        List<ProductType> productTypes = productTypeService.findAllEnableProductTypes();
        return productTypes;
    }

    /**
     * 功能描述: 展示商品详情
     *
     * @return com.xmlvhy.shop.common.utils.ResponseResult
     * @Author 小莫
     * @Date 14:46 2019/03/19
     * @Param [model, id]
     */
    @RequestMapping("showProductDetail")
    public String showProductDetail(HttpServletRequest httpRequest, Model model, Integer id) {

        Product product = productService.findProductById(id);
        if (product != null) {
            model.addAttribute("product", product);
        }
        String defaultBanner = bannerDao.getDefaultBanner();
        defaultBanner = Optional.ofNullable(defaultBanner).orElse(httpRequest.getContextPath() + "/images/banner.jpg");
        model.addAttribute("banner", defaultBanner);
        return "productDetail";
    }
}
