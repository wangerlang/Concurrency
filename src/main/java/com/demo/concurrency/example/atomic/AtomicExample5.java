package com.demo.concurrency.example.atomic;


import com.demo.concurrency.annotation.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * create by wangzhiqian on 2018/5/13
 */
@Slf4j
@ThreadSafe
public class AtomicExample5 {
   private static AtomicIntegerFieldUpdater<AtomicExample5> updater
           = AtomicIntegerFieldUpdater.newUpdater(AtomicExample5.class,"count");

   @Getter
   public volatile int count = 100;



    public static void main(String[] args) {
        AtomicExample5 example5 = new AtomicExample5();
        if(updater.compareAndSet(example5,100,120)){
            log.info("updater success ; {}",example5.getCount());
        }

        if(updater.compareAndSet(example5,100,120)){
            log.info("updater success ; {}",example5.getCount());
        }else{
            log.info("updater failed ; {}",example5.getCount());
        }
    }
}
