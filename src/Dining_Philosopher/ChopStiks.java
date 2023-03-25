package Dining_Philosopher;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ChopStiks {
    private int id;
    private Lock lock;

    public ChopStiks(int id){
        this.id = id;
        this.lock = new ReentrantLock();
    }

    public boolean PickUp(Philosopher philosopher,State state) throws InterruptedException{
        if(lock.tryLock(10, TimeUnit.MILLISECONDS)){
            System.out.println("Philosopher "+ philosopher.getId()+" picked up "+state.toString()+" "+this.id);
            return true;
        }
        return false;
    }

    public void PutDown(Philosopher philosopher,State state){
        lock.unlock();
        System.out.println("Philosopher put down "+state.toString()+" "+this.id);
    }
}
