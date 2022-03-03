package com.aiguigu.springcloud.service;

/**
 * @author aiiiiii
 * @create 2022/2/23 16:27
 */
public interface PaymentService {

    /**
     * 测试
     * @param id Integer
     * @return String
     */
    String paymentInfo(Integer id);

    /**
     * 测试
     * @param id Integer
     * @return String
     */
    String timeOut(Integer id);

    /**
     * 测试
     * @param id Integer
     * @return String
     */
     String paymentTestHystrix(Integer id);
}
