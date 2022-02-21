package com.jy.Thread.study_use;

/**
 * @author JIA.YU
 * @version 1.0.0
 * @date 2022-01-19 10:57
 */
public class ThreadUse_2 {
    public static void main(String[] args) {
        Thread thread = new Thread(new Dog());
        thread.start();

        ThreadUse_1.Cat cat = new ThreadUse_1.Cat();
        cat.start();        //开启两个子线程


    }


    static  class Dog implements Runnable{

        @Override
        public void run() {
            while (true){
                System.out.println(Thread.currentThread().getName()+": 小狗汪汪汪~~~~");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
