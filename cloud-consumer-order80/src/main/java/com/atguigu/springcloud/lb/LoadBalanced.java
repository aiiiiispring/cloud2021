package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author aiiiiii
 * @create 2022/2/21 16:53
 */
public interface LoadBalanced {
    /**
     * 负载均衡算法，轮询
     * @param serviceInstances
     * @return
     */
    ServiceInstance choose(List<ServiceInstance> serviceInstances);

}
