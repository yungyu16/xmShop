package com.xmlvhy.shop.site.controller;

import com.xmlvhy.shop.core.common.utils.ResponseResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Author: 小莫
 * Date: 2019-03-12 20:51
 * Description: 短信接口
 */
@Controller
@RequestMapping("/front/sms")
public class SmsController {

    @RequestMapping("sendVerifyCode")
    @ResponseBody
    public ResponseResult sendVerifyCode(String phone, HttpSession session) {
        try {
            //生成 6 位随机验证码
            Random random = new Random();
            int smsCode = random.nextInt(899999) + 100000;
            //将随机验证码存放到session中
            session.setAttribute("smsVerifyCode", smsCode);
            //TODO 发送验证码
            return ResponseResult.success("验证码发送成功");
        } catch (Exception e) {
            return ResponseResult.fail("验证码发送失败");
        }
    }

    @RequestMapping("CheckSmsCode")
    @ResponseBody
    public Map<String, Object> CheckSmsCode(String verifyCode, HttpSession session) {
        Object smsVerifyCode = session.getAttribute("smsVerifyCode");
        Map<String, Object> map = new HashMap<>();
        if (verifyCode.equals(smsVerifyCode)) {
            map.put("valid", true);
        } else {
            map.put("valid", false);
            map.put("message", "输入的验证码不正确");
        }
        return map;
    }

}
