package com.jy.Thread.Exit_;

/**
 * @author JIA.YU
 * @version 1.0.0
 * @date 2022-01-19 14:22
 */
public class ThreadInterrupt_ {

    public static void main(String[] args) throws InterruptedException {
        Child child = new Child();


        Thread thread = new Thread(child);

        thread.start();//开启线程

        //希望用main线程去控制pig 线程的终止可以修改控制
        //让pig 退出run方法即可控制线程终止
        Thread.sleep(500 * 10);
        System.out.println("主线程打断。。。。。。。。。。。");
        thread.interrupt();//中断子线程的休眠继续执行
    }


    static class Child implements Runnable {


        @Override
        public void run() {
            while (true) {
                for (int i = 0; i < 100; i++) {
                    System.out.println(Thread.currentThread().getName() + ": 吃第" + i + "个包子~~~~");

                }
                try {
                    System.out.println(Thread.currentThread().getName() + "休眠中！！");
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    //当该线程执行到interrupt方法时，就会catch一个异常，可以加入自己的代码
                    //InterruptedException捕获达到一个中断异常
                    System.out.println(Thread.currentThread().getName() + "吃包子被中断了。。。。。。。。。。。。");
                }
            }
        }


    }
}
