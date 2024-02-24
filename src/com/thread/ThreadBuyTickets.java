package com.thread;

/**
 * 多个线程同时操作同一个对象
 * 购买车票
 *
 * 并发问题：多个线程操作同一个资源，线程不安全，出现数据紊乱
 */
public class ThreadBuyTickets implements Runnable{

    //
    private int ticketsNum = 10;

    @Override
    public void run() {
        while(true){
            if(ticketsNum<=0){
                break;
            }

            //延时
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
//                e.printStackTrace();
                throw new RuntimeException(e);
            }

            System.out.println(Thread.currentThread().getName()+"拿到了第"+ticketsNum--+"张票");
        }
    }


    //
    public static void main(String[] args) {
        ThreadBuyTickets threadBuyTickets = new ThreadBuyTickets();

        new Thread(threadBuyTickets,"1号").start();
        new Thread(threadBuyTickets,"2号").start();
        new Thread(threadBuyTickets,"3号").start();

    }
}
