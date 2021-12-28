package com.jy.Thread.currentLimit;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author JIA.YU
 * @version 1.0.0
 * @date 2021-12-21 09:59
 * @info 信号量限流模拟
 */
public class SemaphoreExample {
    static   ExecutorService exec = Executors.newCachedThreadPool();

    public static void main(String[] args) {

        final Semaphore sem = new Semaphore(5);
        for (int i = 0; i < 20; i++) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {

                    try {
                        // 获得许可
                        sem.acquire();
                        //同时只有五个请求可以到达这里
                        Thread.sleep(10000);
                        //释放许可
                        sem.release();
                        System.out.println("剩余许可："+sem.availablePermits());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            exec.execute(runnable);
        }

        exec.shutdown();
    }
}
