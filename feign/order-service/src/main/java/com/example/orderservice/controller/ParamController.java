package com.example.orderservice.controller;

import com.example.orderservice.domain.Order;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * url
 * get 一个参数
 * get 多个参数
 * post 一个对象
 * post 一个对象+一个参数
 */
@RestController
public class ParamController {

    @GetMapping("testUrl/{name}/{age}")
    public String testUrl(@PathVariable("name")String name,@PathVariable("age")Integer age){
        System.out.println(name+":"+age);
        return "ok";
    }

    @GetMapping("oneParam")
    public String oneParam(@RequestParam(required = false) String name){
        System.out.println(name);
        return "ok";
    }

    @GetMapping("twoParam")
    public String twoParam(@RequestParam(required = false) String name,@RequestParam(required = false)Integer age){
        System.out.println(name);
        System.out.println(age);
        return "ok";
    }

    @PostMapping("oneObj")
    public String oneObj(@RequestBody Order order){
        System.out.println(order);
        return "ok";
    }

    @PostMapping("oneObjOneParam")
    public String oneObjOneParam(@RequestBody Order order,@RequestParam("name") String name){
        System.out.println(order);
        System.out.println(name);
        return "ok";
    }


    @GetMapping("testTime")
    public String testTime(@RequestParam Date date){
        System.out.println(date);
        return "ok";
    }

}
