package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author aiiiiii
 * @create 2022/2/28 16:51
 */
@SpringBootApplication
@EnableEurekaClient
public class PaymentGatewayMain9527 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentGatewayMain9527.class, args);
    }
}
