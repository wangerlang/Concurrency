package com.demo.concurrency.example.sync;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * create by wangzhiqian on 2018/5/13
 */
@Slf4j
public class SynchronizedExample2 {

    //修饰一个类
    public static void test1(){
        synchronized (SynchronizedExample2.class){
            for (int i = 0; i < 10; i++) {
                log.info("test1.i={}",i);
            }
        }
    }

    //修饰一个静态方法成为同步方法
    public static synchronized void test2(int j){
        for (int i = 0; i < 10; i++) {
            log.info("test1.{} - {}",j,i);
        }
    }

    public static void main(String[] args) {
        SynchronizedExample2 example1 = new SynchronizedExample2();
        ExecutorService service = Executors.newCachedThreadPool();

        service.execute(() ->{
            example1.test2(9);
        });

        service.execute(() ->{
            example1.test2(9);
        });
    }
}
