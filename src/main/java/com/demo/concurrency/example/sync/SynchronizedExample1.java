package com.demo.concurrency.example.sync;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * create by wangzhiqian on 2018/5/13
 */
@Slf4j
public class SynchronizedExample1 {

    //修饰一个代码块
    public void test1(){
        synchronized (this){
            for (int i = 0; i < 10; i++) {
                log.info("test1.i={}",i);
            }
        }
    }

    //修饰一个方法成为同步方法
    public synchronized void test2(){
        for (int i = 0; i < 10; i++) {
            log.info("test1.i={}",i);
        }
    }

    public static void main(String[] args) {
        SynchronizedExample1 example1 = new SynchronizedExample1();
        ExecutorService service = Executors.newCachedThreadPool();

        service.execute(() ->{
            example1.test1();
        });

        service.execute(() ->{
            example1.test1();
        });
    }
}
