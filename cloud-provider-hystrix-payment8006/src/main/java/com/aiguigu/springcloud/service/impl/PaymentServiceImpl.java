package com.aiguigu.springcloud.service.impl;

import cn.hutool.core.util.IdUtil;
import com.aiguigu.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author aiiiiii
 * @create 2022/2/23 16:15
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Override
    public String paymentInfo(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + "    paymentInfo_OK     " + id;
    }

    @Override
    @HystrixCommand(fallbackMethod = "defaultTimeOut", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public String timeOut(Integer id) {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + "    paymentInfo_timeOut     " + id;
    }


    @Override
    @HystrixCommand(fallbackMethod = "paymentTestHystrixMethod", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
    })
    public String paymentTestHystrix(Integer id) {
        if (id < 0) {
            throw new RuntimeException("**********id不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t" + "ok" + serialNumber;
    }

    @SuppressWarnings("unused")
    public String paymentTestHystrixMethod(Integer id) {
        return "默认处理方法****id不能为负数";
    }

    @SuppressWarnings("unused")
    public String defaultTimeOut(Integer id) {
        return "8006系统繁忙";
    }
}
