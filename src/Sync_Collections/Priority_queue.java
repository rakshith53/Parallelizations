//package Sync_Collections;
//
//import java.util.concurrent.BlockingQueue;
//import java.util.concurrent.PriorityBlockingQueue;
//
//class PriorityQueueWorker1 implements Runnable {
//
//    private BlockingQueue<String> queue;
//    public PriorityQueueWorker1(BlockingQueue<String> queue){
//        this.queue = queue;
//    }
//    @Override
//    public void run() {
//        try {
//            queue.put("B");
//            queue.put("H");
//            queue.put("F");
//            Thread.sleep(2000);
//            queue.put("A");
//            Thread.sleep(400);
//            queue.put("Z");
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
//
//class PriorityQueueWorker2 implements Runnable {
//
//    private BlockingQueue<String> queue;
//    public PriorityQueueWorker2(BlockingQueue<String> queue){
//        this.queue = queue;
//    }
//    @Override
//    public void run() {
//        try {
//            Thread.sleep(5000);
//            System.out.println(queue.take());
//            Thread.sleep(1000);
//            System.out.println(queue.take());
//            Thread.sleep(2000);
//            System.out.println(queue.take());
//            System.out.println(queue.take());
//            System.out.println(queue.take());
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
//
//public class Priority_queue {
//    /*
//    * It implements blocking queue interface, similar delay queue but priority not based on delay time
//    * */
//
//    public static void main(String[] args) {
//        BlockingQueue<String> queue = new PriorityBlockingQueue<>();
//
//        PriorityQueueWorker1 first = new PriorityQueueWorker1(queue);
//        PriorityQueueWorker2 second = new PriorityQueueWorker2(queue);
//
//        new Thread(first).start();
//        new Thread(second).start();
//    }
//}

package Sync_Collections;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

class PriorityQueueWorker1 implements Runnable {

    private BlockingQueue<Person> queue;
    public PriorityQueueWorker1(BlockingQueue<Person> queue){
        this.queue = queue;
    }
    @Override
    public void run() {
        try {
            queue.put(new Person(12, "Adam"));
            queue.put(new Person(55, "Kevin"));
            queue.put(new Person(27,"Ana"));
            Thread.sleep(2000);
            queue.put(new Person(31, "Daniel"));
            Thread.sleep(400);
            queue.put(new Person(15,"Joe"));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class PriorityQueueWorker2 implements Runnable {

    private BlockingQueue<Person> queue;
    public PriorityQueueWorker2(BlockingQueue<Person> queue){
        this.queue = queue;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(5000);
            System.out.println(queue.take().getName());
            Thread.sleep(1000);
            System.out.println(queue.take().getName());
            Thread.sleep(2000);
            System.out.println(queue.take().getName());
            System.out.println(queue.take().getName());
            System.out.println(queue.take().getName());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class Person implements Comparable<Person> {
    private int age;
    private String name;

    public Person(int age,String name){
        this.age = age;
        this.name = name;
    }

    @Override
    public int compareTo(Person person) {
        return name.compareTo(person.getName());
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

public class Priority_queue {
    /*
     * It implements blocking queue interface, similar delay queue but priority not based on delay time
     * */

    public static void main(String[] args) {
        BlockingQueue<Person> queue = new PriorityBlockingQueue<>();

        PriorityQueueWorker1 first = new PriorityQueueWorker1(queue);
        PriorityQueueWorker2 second = new PriorityQueueWorker2(queue);

        new Thread(first).start();
        new Thread(second).start();
    }
}
