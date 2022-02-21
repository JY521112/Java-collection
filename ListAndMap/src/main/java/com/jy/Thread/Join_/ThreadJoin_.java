package com.jy.Thread.Join_;

/**
 * @author JIA.YU
 * @version 1.0.0
 * @date 2022-01-19 16:15
 */
public class ThreadJoin_ {
    public static void main(String[] args) throws InterruptedException {
        T1 t1 = new T1();
        T2 t2 = new T2();

        new Thread(t1).start(); 
        Thread thread = new Thread(t2);
        for (int i = 0; i < 20; i++) {
            System.out.println(Thread.currentThread().getName()+": 主线程吃"+i+"个包子！");
            Thread.sleep(1000);
            if (i==5){
                thread.start();
                System.out.println("让给子线程T2来吃包子!!");
                thread.join();//将T2线程进行插队 此时main线程将停止 待T2线程执行结束 再回到主线程
            }
        }

    }

    static class T1 implements Runnable{
        @Override
        public void run() {
            for (int i = 0; i < 20; i++) {
                System.out.println(Thread.currentThread().getName()+": T1吃"+i+"个包子！");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class T2 implements Runnable{
        @Override
        public void run() {
            for (int i = 0; i < 20; i++) {
                System.out.println(Thread.currentThread().getName()+": T2吃"+i+"个包子！");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
