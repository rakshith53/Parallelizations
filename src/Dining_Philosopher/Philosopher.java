package Dining_Philosopher;

import java.util.Random;

public class Philosopher implements Runnable{
    private int id;
    private volatile boolean full;
    private ChopStiks leftChopStick;
    private ChopStiks rightChopStick;
    private Random random;
    private int eatingCounter;

    public Philosopher(int id, ChopStiks leftChopStick,ChopStiks rightChopStick){
        this.id = id;
        this.leftChopStick = leftChopStick;
        this.rightChopStick = rightChopStick;
        this.random = new Random();
    }
    @Override
    public void run() {
        try {
            while (!full){
                think();

                if(leftChopStick.PickUp(this, State.LEFT)){
                    if(rightChopStick.PickUp(this,State.RIGHT)){
                        eat();
                        rightChopStick.PutDown(this,State.RIGHT);
                    }
                    leftChopStick.PutDown(this,State.LEFT);
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void think() throws InterruptedException {
        System.out.println(this.id + " is thinking...");
        Thread.sleep(random.nextInt(1000));
    }

    private void eat() throws InterruptedException {
        System.out.println(this.id + " is eating");
        eatingCounter++;
        Thread.sleep(random.nextInt(1000));
    }

    public void setFull(boolean full){
        this.full = full;
    }

    public boolean isFull(){
        return this.full;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Philosopher " + id;
    }

    public int getEatingCouter() {
        return this.eatingCounter;
    }
}
