package com.demo.concurrency.example.atomic;


import com.demo.concurrency.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * create by wangzhiqian on 2018/5/13
 */
@Slf4j
@ThreadSafe
public class AtomicExample1 {
    //请求总数
    public static int clientTotal = 5000;
    //并发执行的线程数
    public static int threadTotal = 200;

    public static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                    } catch (Exception e) {
                        log.error("exception: {}",e.getMessage());
                    }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("count: {}", count.get());
    }

    public static void add(){
        count.getAndIncrement();    //先获取当前值然后在增加
        //count.incrementAndGet();  先增加然后获取当前值
    }
}
