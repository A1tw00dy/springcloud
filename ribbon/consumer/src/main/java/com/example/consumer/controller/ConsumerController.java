package com.example.consumer.controller;

import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    /**
     * ribbon将http://provider/hello路径变成http://127.0.0.1:8080/hello
     * 1.拦截请求
     * 2.截取主机名称
     * 3.借助eureka来做服务发现list<>
     * 4.通过负载均衡算法 拿到一个服务ip port
     * 5.reConstructURL 重构url
     * 6.发起请求
     * @param serviceName
     * @return
     */
    @GetMapping("testRibbon")
    public String testRibbon(String serviceName){
        //需要拿到ip 端口和路径
        String result = restTemplate.getForObject("http://" + serviceName + "/hello", String.class);
        return result;
    }

    /**
     * 核心是负载均衡
     * @param serviceName
     * @return
     */
    @GetMapping("testRibbonRule")
    public String testRibbonRule(String serviceName){
        //choose方法里面就是负载均衡算法
        ServiceInstance choose = loadBalancerClient.choose(serviceName);
        return choose.toString();
    }
}
