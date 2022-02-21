package com.jy.Thread.Exit_;

/**
 * @author JIA.YU
 * @version 1.0.0
 * @date 2022-01-19 14:05
 *
 * 通知线程退出
 * 应用场景可为线程1通知线程2退出
 */
public class ThreadExit {

    public static void main(String[] args) throws InterruptedException {
        Pig pig = new Pig();

        new Thread(pig).start();//开启线程

        //希望用main线程去控制pig 线程的终止可以修改控制
        //让pig 退出run方法即可控制线程终止
        Thread.sleep(1000*10);
        pig.setLoop(false);
    }


  static  class Pig implements Runnable{

        //设置一个控制遍历
        private boolean loop = true;

        @Override
        public void run() {
            while (loop){
                System.out.println(Thread.currentThread().getName()+": 小猪噜噜噜~~~~");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

      public boolean isLoop() {
          return loop;
      }

      public void setLoop(boolean loop) {
          this.loop = loop;
      }
  }
}
