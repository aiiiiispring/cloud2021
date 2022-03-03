package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.entities.Payment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author aiiiiii
 */
//@Mapper
public interface PaymentDao extends BaseMapper<Payment> {

     int create (Payment payment);


     Payment getPaymentById(@Param("id") Long id);
}
