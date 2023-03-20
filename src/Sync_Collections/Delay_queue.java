package Sync_Collections;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

class DelayedWorker implements Delayed {
    private long duration;
    private String message;

    public DelayedWorker(String message, long duration){
        this.message = message;
        this.duration = System.currentTimeMillis() + duration;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(duration - System.currentTimeMillis(),TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed other) {
        if(duration < ((DelayedWorker) other).getDuration()) return -1;
        if(duration > ((DelayedWorker) other).getDuration()) return +1;
        return 0;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
public class Delay_queue {
    /*
    *  delay queue won't allow element to remove until time limit has reached,
    *  sorted order based on time limit, first having the lowest time left.
    *  if time not completed, return null
    * */
    public static void main(String[] args) {

        BlockingQueue<DelayedWorker> queue = new DelayQueue<>();

        try {
            queue.put(new DelayedWorker("This is the first message...",2000));
            queue.put(new DelayedWorker("This is the second message...",10000));
            queue.put(new DelayedWorker("This is the third message...",4500));
            queue.put(new DelayedWorker("This is the fourth message...",20000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        while (!queue.isEmpty()) {
            try {
                System.out.println(queue.take().getMessage());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
