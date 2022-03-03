package com.atguigu.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author aiiiiii
 * @create 2022/2/23 17:12
 */
@Component
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT",fallback = PaymentHystrixServiceImpl.class)
public interface PaymentHystrixService {
    /**
     * 测试
     * @param id Integer
     * @return String
     */
    @GetMapping("/payment/hystrix/ok/{id}")
    String paymentInfo(@PathVariable("id") Integer id);

    /**
     * 测试
     * @param id Integer
     * @return String
     */
    @GetMapping("/payment/hystrix/timeout/{id}")
    String timeOut(@PathVariable("id") Integer id);
}

