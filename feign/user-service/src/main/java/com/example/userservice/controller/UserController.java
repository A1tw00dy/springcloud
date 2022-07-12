package com.example.userservice.controller;


import com.example.userservice.domain.Order;
import com.example.userservice.feign.UserOrderFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@RestController
public class UserController {


    /**
     *  这个接口肯定是被创建出代理对象的
     *  动态代理jdk(java interface 接口$Proxy) cglib(subClass 子类)
     *  jdk动态代理 只要是代理对象调用的方法必须走 invoke  java.lang.reflect.InvocationHandler#invoke
     */
    @Autowired
    public UserOrderFeign userOrderFeign;

    /**
     * 浏览器--->user-service(/userDoOrder)----RPC(feign)--->order-service(/doOrder)
     * feign默认等待时间1s，超过1s直接报错超时
     * @return
     */
    @GetMapping("userDoOrder")
    public String userDoOrder(){
        System.out.println("用户请求");
        //发起一个远程调用 请求order-service
        String s = userOrderFeign.doOrder();
        return  s;
    }


    @GetMapping("testParam")
    public String testParam(){
        String testUrl = userOrderFeign.testUrl("testUrl", 18);
        System.out.println(testUrl);
        String oneParam = userOrderFeign.oneParam("oneParam");
        System.out.println(oneParam);
        String twoParam = userOrderFeign.twoParam("twoParam", 18);
        System.out.println(twoParam);
        Order order = Order.builder().name("abc").price(100D).time(new Date()).id(1).build();
        String oneObj = userOrderFeign.oneObj(order);
        System.out.println(oneObj);
        String oneObjOneParam = userOrderFeign.oneObjOneParam(order, "oneObjOneParam");
        System.out.println(oneObjOneParam);
        return "ok";
    }

    @GetMapping("time")
    public String testTime(){
        Date date = new Date();
        System.out.println(date);
        String s = userOrderFeign.testTime(date);

        LocalDate now = LocalDate.now();
        LocalDateTime now1 = LocalDateTime.now();

        return s;
    }
}
