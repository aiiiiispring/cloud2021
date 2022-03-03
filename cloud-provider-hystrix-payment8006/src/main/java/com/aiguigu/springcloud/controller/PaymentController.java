package com.aiguigu.springcloud.controller;

import com.aiguigu.springcloud.service.PaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author aiiiiii
 * @create 2022/2/23 16:32
 */
@RestController
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo(@PathVariable("id") Integer id) {
        return paymentService.paymentInfo(id);
    }


    @GetMapping("/payment/hystrix/timeout/{id}")
    public String timeOut(@PathVariable("id") Integer id) {
        return paymentService.timeOut(id);
    }

    @GetMapping("/payment/hystrix/circuit/{id}")
    public String paymentTestHystrix(@PathVariable("id") Integer id) {
        return paymentService.paymentTestHystrix(id);
    }
}
