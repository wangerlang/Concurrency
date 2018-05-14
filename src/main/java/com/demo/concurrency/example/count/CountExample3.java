package com.demo.concurrency.example.count;


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
public class CountExample3 {
    //请求总数
    public static int clientTotal = 5000;
    //并发执行的线程数
    public static int threadTotal = 200;

    public static volatile int count = 0;

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
        log.info("count: {}", count);
    }

    public static void add(){
        //count被volatile关键字修饰
        count++;
        //1.先取出主内存中的count值
        //2.+1
        //3.重新写回主存
        /*
        这样会产生一个问题，当两个线程同时拿到这个count值，这时候值统一，但是他们各自执行
        +1操作后，在写回主内存时，就会产生两个同样的操作，等于丢失一次操作，导致最终执行的
        结果与我们预期的不符
        * */
    }
}
