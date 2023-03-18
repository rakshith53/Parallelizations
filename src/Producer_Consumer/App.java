package Producer_Consumer;

public class App {
    public static void main(String[] args) {
        Processor processor = new Processor();
        Thread T1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    processor.producer();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });

        Thread T2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    processor.consumer();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
        T1.start();
        T2.start();
        try {
            T1.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            T2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
