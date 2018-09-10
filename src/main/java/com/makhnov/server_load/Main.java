package com.makhnov.server_load;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;


public class Main {
    public static void main(String[] args) throws Exception {
        Properties property = new Properties();
        FileInputStream fileInputStream = new FileInputStream("src/main/resources/config.properties");
        property.load(fileInputStream);
        int requestQuantity = Integer.parseInt(property.getProperty("requestQuantity"));
        String url = property.getProperty("url");
        CountDownLatch countDownLatch = new CountDownLatch(requestQuantity);
        for(int j = 0; j < requestQuantity; j++) {
            new Thread(new MyJob(countDownLatch, url)).start();
        }
    }
}