package com.learning.asynclearn;

import com.netflix.hystrix.HystrixCommandProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author zx
 * @date 2018/11/13 11:09
 */
@Configuration
public class HystrixCacheConfig {

    public HystrixCommandProperties.Setter commandProperties = HystrixCommandProperties.Setter()
            .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD)
            .withRequestCacheEnabled(true);
}
