package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.Payment;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

/**
 * @author aiiiiii
 */
public interface PaymentService extends IService<Payment> {
     int create (Payment payment);

     Payment getPaymentById(@Param("id") Long id);
}
