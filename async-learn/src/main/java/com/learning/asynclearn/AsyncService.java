package com.learning.asynclearn;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.nio.client.HttpAsyncClient;
import org.apache.http.nio.client.methods.HttpAsyncMethods;
import org.apache.http.nio.protocol.BasicAsyncResponseConsumer;
import org.apache.http.nio.protocol.HttpAsyncRequestProducer;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author zx
 * @date 2018/11/13 8:46
 */
public class AsyncService {

    public static HttpAsyncClient httpAsyncClient;

    public static CompletableFuture<String> getHttpData(String url) {

        CompletableFuture asyncfFuture = new CompletableFuture();
        HttpAsyncRequestProducer producer = HttpAsyncMethods.create(new HttpPost(url));
        BasicAsyncResponseConsumer consumer = new BasicAsyncResponseConsumer();

        FutureCallback callback = new FutureCallback<HttpResponse>() {

            @Override
            public void completed(HttpResponse result) {
                asyncfFuture.complete(result);
            }

            @Override
            public void failed(Exception e) {
                asyncfFuture.completeExceptionally(e);
            }

            @Override
            public void cancelled() {
                asyncfFuture.cancel(true);
            }
        };

        httpAsyncClient.execute(producer, consumer, callback);
        return asyncfFuture;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = AsyncService.getHttpData("http://www.baidu.com");
        System.out.println("--------");
        String result = future.get();
        System.out.println(result.substring(0, 10));
    }

}
