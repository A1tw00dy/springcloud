package com.example.eurekaclienta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DiscoveryController {

    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * 通过应用名称找到服务的ip和端口
     * @param serviceName
     * @return
     */
    @GetMapping("test")
    public String doDiscovery(String serviceName){
        //通过服务的应用名称，找到服务的具体信息，一个应用可以有多个实例
        List<ServiceInstance> instances = discoveryClient.getInstances(serviceName);
        instances.forEach(System.out::println);
        ServiceInstance serviceInstance = instances.get(0);
        String ip = serviceInstance.getHost();
        int port = serviceInstance.getPort();
        System.out.println(ip+":"+port);
        return instances.get(0).toString();
    }
}
