package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * @author aiiiiii
 * @create 2022/2/23 17:11
 */
@RestController
@DefaultProperties(defaultFallback = "goGalTimeOut")
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/payment/hystrix/ok/{id}")
  //  @HystrixCommand
    public String paymentInfo(@PathVariable("id") Integer id) {
        return paymentHystrixService.paymentInfo(id);
    }

    @HystrixCommand(fallbackMethod = "defaultTimeOut",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")
    })
    @GetMapping("/payment/hystrix/timeout/{id}")
    public String timeOut(@PathVariable("id") Integer id) {
        return paymentHystrixService.timeOut(id);
    }

    /**
     * 超时返回方法
     * @param id Integer
     * @return  String
     */
    @SuppressWarnings("unused")
    public String defaultTimeOut(Integer id) {
        return "80系统繁忙";
    }
    /**
     * 全局默认返回方法
     * @return  String
     */
    @SuppressWarnings("unused")
    public String goGalTimeOut() {
        return "全局80系统繁忙";
    }
}
