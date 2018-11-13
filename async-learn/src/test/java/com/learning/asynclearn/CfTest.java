package com.learning.asynclearn;

import com.google.common.collect.Maps;
import com.lmax.disruptor.EventHandler;
import org.junit.Test;

import java.awt.*;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * @author zx
 * @date 2018/11/13 9:56
 */
public class CfTest {

    public void setEventHandlerMap(Map<EventQueue, EventHandler> eventHandlerMap) {
    }

    @Test
    public void test2() {
        AsyncService service = new AsyncService();
        CompletableFuture<String> future1 = AsyncService.getHttpData("http://www.baidu.com");
        CompletableFuture<String> future2 = AsyncService.getHttpData("http://www.jd.com");
        future1.thenAcceptBothAsync(future2, (f1, f2) ->{
        //        do something
        });
    }

    public void test3() {
        CompletableFuture<String> future1 = AsyncService.getHttpData("http://www.baidu.com");
        //CompletableFuture<String> future2 = future1.thenApplyAsync(r->{});


    }
}
