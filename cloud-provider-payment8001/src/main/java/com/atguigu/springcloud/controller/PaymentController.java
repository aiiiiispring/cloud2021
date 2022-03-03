package com.atguigu.springcloud.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int i = paymentService.create(payment);
        if (i > 0) {
            return new CommonResult(200, "成功," + serverPort);
        } else {
            return new CommonResult(404, "失败");
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment result = paymentService.getPaymentById(id);
        if (result != null) {
            return new CommonResult(200, "成功," + serverPort, result);
        } else {
            return new CommonResult(404, "失败");
        }
    }

    @GetMapping("/payment/page")
    public CommonResult getAllPayment(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageCount) {
        Page<Payment> page = new Page(pageNum, pageCount);
        Page<Payment> paymentPage = paymentService.page(page);
        CommonResult commonResult = new CommonResult(200, "成功," + serverPort, paymentPage);
        return commonResult;
    }


    @GetMapping("test/redis")
    public void testRedis(@RequestParam Integer id) {
        Map<String,Payment> map = new HashMap(10);
        Payment payment = new Payment(1L,"1");
        Payment payment2 = new Payment(2L,"2");
        map.put("payment",payment);
        map.put("payment2",payment2);
        redisTemplate.opsForHash().putAll("payment",map);
        log.info(redisTemplate.opsForHash().entries("payment").toString());
    }
    @GetMapping("payment/lb")
    public String getPaymentLB() {
        return serverPort;
    }
}
