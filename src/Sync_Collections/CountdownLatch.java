package Sync_Collections;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Worker implements Runnable{
    private int id;
    private CountDownLatch latch;
    public Worker (int id,CountDownLatch latch){
        this.id=id;
        this.latch=latch;
    }
    public void run(){
        doWork();
        latch.countDown();
    }
    private void doWork(){
        try{
        System.out.println("Thread with ID"+this.id+"starts working.....");
        Thread.sleep(2000);
        }catch(InterruptedException e){
        throw new RuntimeException(e);
        }
    }
}
public class CountdownLatch{
    /*To Sync more than task by forcing them to wait for
        the completions of other task.
     -give initial count to CountDownLatch,any task that call await() on that object will block until count reaches0.
     -Other task call latch on the object to reduce count,when finished.
     -cannot be reset,one shot
     -task called upon count down has to wait(using await()) until latch count reaches zero
-
*/
        public static void main(String[]args){
            CountDownLatch latch = new CountDownLatch(5);
            ExecutorService service = Executors.newSingleThreadExecutor();

            for(int i=0;i<5;i++){
                service.execute(new Worker(i,latch));
            }
            try{
                latch.await();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("All task have been finished....");
            service.shutdown();
        }
}