package Dining_Philosopher;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
    public static void main(String[] args) throws InterruptedException {
        new Constant();
        ExecutorService service = null;
        Philosopher[] philosophers = null;
        ChopStiks[] chopStiks = null;
        try {
            philosophers = new Philosopher[Constant.NUMBER_OF_PHILOSOPHERS];
            chopStiks = new ChopStiks[Constant.NUMBER_OF_CHOPSTIKS];

            for (int i=0;i<Constant.NUMBER_OF_CHOPSTIKS;++i){
                chopStiks[i] = new ChopStiks(i);
            }
            service = Executors.newFixedThreadPool(Constant.NUMBER_OF_PHILOSOPHERS);

            for (int i=0;i<Constant.NUMBER_OF_PHILOSOPHERS;++i) {
                philosophers[i] = new Philosopher(i, chopStiks[i], chopStiks[(i + 1) % (Constant.NUMBER_OF_CHOPSTIKS)]);
                service.execute(philosophers[i]);
            }
            Thread.sleep(Constant.SIMULATION_RUNNING_TIME);

            for (Philosopher philosopher: philosophers)
                philosopher.setFull(true);
        }
        catch (Exception e){
            e.printStackTrace();;
        }
        finally {
            service.shutdown();

            while (!service.isTerminated())
                Thread.sleep(1000);

            for (Philosopher philosopher : philosophers)
                System.out.println(philosopher + "eat #"+philosopher.getEatingCouter());
        }
    }
}
