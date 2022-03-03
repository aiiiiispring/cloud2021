package com.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author aiiiiii
 * @create 2022/2/16 17:54
 */
@Configuration
public class MySelfRule {
    @Bean
    public IRule myRule() {
        //随机
        return new RandomRule();
    }
}
