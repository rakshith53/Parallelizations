package Sync_Collections;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.lang.Thread;

class BlockingQueueWorker1 implements Runnable {
    /*
    * BlockingQueue -> an interface that represents a queue
    * Eg - onr thread putting items into the queue and another thread taking items from same time
    * 2 methods - put(), take()
    * */
    private BlockingQueue<Integer> queue;
    public BlockingQueueWorker1(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }
    public void run(){
        int counter = 0;
        while(true) {
            try {
                queue.put(counter);
                System.out.println("Putting item into the queue..." + counter);
                counter++;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class BlockingQueueWorker2 implements Runnable {
    private BlockingQueue<Integer> queue;
    private int counter;
    public BlockingQueueWorker2(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }
    public void run(){
        while(true) {
            try {
                counter = queue.take();
                System.out.println("Taking item into the queue..." + counter);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
public class BlockingQueueExample {
    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

        BlockingQueueWorker1 firstWorker = new BlockingQueueWorker1(queue);
        BlockingQueueWorker2 secondWorker = new BlockingQueueWorker2(queue);

        new Thread(firstWorker).start();
        new Thread(secondWorker).start();
    }

}
