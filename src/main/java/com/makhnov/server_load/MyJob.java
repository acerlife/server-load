package com.makhnov.server_load;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class MyJob implements Runnable {
    private final CountDownLatch countDownLatch;
    private final String url;

    public MyJob(CountDownLatch countDownLatch, String url){
        this.countDownLatch = countDownLatch;
        this.url = url;
    };
    public void run() {
        countDownLatch.countDown();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        HttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);

        try {
            httpClient.execute(httpGet);
            System.out.println(Thread.currentThread().getName());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}