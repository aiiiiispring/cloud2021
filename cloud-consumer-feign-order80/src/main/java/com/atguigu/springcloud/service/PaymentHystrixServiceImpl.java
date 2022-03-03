package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @author aiiiiii
 * @create 2022/2/24 16:17
 */
@Component
public class PaymentHystrixServiceImpl implements PaymentHystrixService{
    @Override
    public String paymentInfo(Integer id) {
        return "okHystrix接口处理方式";
    }

    @Override
    public String timeOut(Integer id) {
        return "超时Hystrix接口处理方式";
    }
}
