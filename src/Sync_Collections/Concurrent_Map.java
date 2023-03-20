package Sync_Collections;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

class MapFirstWorker implements Runnable {
    private ConcurrentMap<String, Integer> map;

    public MapFirstWorker(ConcurrentMap<String, Integer> map){
        this.map = map;
    }
    @Override
    public void run() {
        try {
            map.put("B",12);
            Thread.sleep(100);
            map.put("C",5);
            map.put("A",25);
            Thread.sleep(100);
            map.put("D",19);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class MapSecondWorker implements Runnable {
    private ConcurrentMap<String, Integer> map;

    public MapSecondWorker(ConcurrentMap<String, Integer> map){
        this.map = map;
    }
    @Override
    public void run() {
        try {
            System.out.println(map.get("B"));
            Thread.sleep(100);
            System.out.println(map.get("C"));
            Thread.sleep(100);
            System.out.println(map.get("A"));
            System.out.println(map.get("D"));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
public class Concurrent_Map {
    /*
    * Each map has segments and multiple thread can access map simultaneously, each thread for each segments
    * */
    public static void main(String[] args) {
        ConcurrentMap<String, Integer> map = new ConcurrentHashMap<String, Integer>();

        MapFirstWorker firstWorker = new MapFirstWorker(map);
        MapSecondWorker secondWorker = new MapSecondWorker(map);

        new Thread(firstWorker).start();
        new Thread(secondWorker).start();
    }
}
