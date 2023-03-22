package Sync_Collections;

import java.util.concurrent.Exchanger;

class FirstThread implements Runnable {
    private int counter;
    private Exchanger<Integer> exchanger;

    public FirstThread(Exchanger<Integer> exchanger){
        this.exchanger = exchanger;
    }
    @Override
    public void run() {
        while (true) {
            counter++;
            System.out.println("FirstThread incremented the counter : " + counter);

            try {
                counter = exchanger.exchange(counter);
                System.out.println("FirstThread get the counter: " + counter);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class SecondThread implements Runnable {
    private int counter;
    private Exchanger<Integer> exchanger;

    public SecondThread(Exchanger<Integer> exchanger){
        this.exchanger = exchanger;
    }
    @Override
    public void run() {
        while (true) {
            counter--;
            System.out.println("Second Thread incremented the counter : " + counter);

            try {
                counter = exchanger.exchange(counter);
                System.out.println("Second Thread get the counter: " + counter);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
public class ExchangerExample {
    public static void main(String[] args) {
        Exchanger<Integer> exchanger = new Exchanger<>();
        FirstThread t1 = new FirstThread(exchanger);
        SecondThread t2 = new SecondThread(exchanger);

        new Thread(t1).start();
        new Thread(t2).start();
    }
}
