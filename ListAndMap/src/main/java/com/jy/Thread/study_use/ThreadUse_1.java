package com.jy.Thread.study_use;

/**
 * @author JIA.YU
 * @version 1.0.0
 * @date 2022-01-19 10:04
 */
public class ThreadUse_1 {
    public static void main(String[] args) {


        Cat cat = new Cat();
        cat.start();//启动线程 此时再main的主线程的基础上 启动子线程 Thread-0 不会阻塞主线程

        System.out.println("主线程在执行！！！");

        while (true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 1.当一个类继承线程类 这个类就可以当作线程类来使用
     * 2.重新run方法来写自己的业务代码
     * 3.run Thread  类 实现了Runnable接口的run方法
     */
  static  class Cat extends Thread{
        @Override
        public void run() {
            while (true){
                System.out.println(Thread.currentThread().getName()+": 小猫喵喵喵~~~~");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
