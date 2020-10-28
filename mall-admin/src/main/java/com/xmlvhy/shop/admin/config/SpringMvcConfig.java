package com.xmlvhy.shop.admin.config;

import com.xmlvhy.shop.admin.interceptor.BackSessionInteceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * <!--配置拦截器-->
 * <mvc:interceptors>
 * <mvc:interceptor>
 * <mvc:mapping path="/**"/>
 * <!--登录以及页面展示相关的接口放行-->
 * <mvc:exclude-mapping path="/front/customer/**"/>
 * <mvc:exclude-mapping path="/front/sms/**"/>
 * <mvc:exclude-mapping path="/front/product/**"/>
 * <mvc:exclude-mapping path="/front/cart/addToCart"/>
 * <!--不要拦截支付宝的异步通知接口-->
 * <mvc:exclude-mapping path="/front/pay/aliPayNotifyNotice"/>
 * <mvc:exclude-mapping path="/front/wxPay/goWxPay"/>
 * <mvc:exclude-mapping path="/front/wxPay/callback"/>
 * <mvc:exclude-mapping path="/css/**"/>
 * <mvc:exclude-mapping path="/fonts/**"/>
 * <mvc:exclude-mapping path="/iconfont/**"/>
 * <mvc:exclude-mapping path="/images/**"/>
 * <mvc:exclude-mapping path="/js/**"/>
 * <mvc:exclude-mapping path="/layer/**"/>
 * <bean class="com.xmlvhy.front.shop.interceptor.SessionInterceptor"/>
 * </mvc:interceptor>
 * </mvc:interceptors>
 * CreatedDate: 2020/10/28
 * Author: songjialin
 */
@Configuration
public class SpringMvcConfig extends WebMvcConfigurationSupport {
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(new BackSessionInteceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/front/customer/**")
                .excludePathPatterns("/front/sms/**")
                .excludePathPatterns("/front/product/**")
                .excludePathPatterns("/front/cart/addToCart")
                .excludePathPatterns("/front/pay/aliPayNotifyNotice")
                .excludePathPatterns("/front/wxPay/goWxPay")
                .excludePathPatterns("/front/wxPay/callback")
                .excludePathPatterns("/css/**")
                .excludePathPatterns("/fonts/**")
                .excludePathPatterns("/iconfont/**")
                .excludePathPatterns("/images/**")
                .excludePathPatterns("/js/**")
                .excludePathPatterns("/layer/**");
    }

}
