import java.util.Scanner;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by p on 2017-10-25.
 */
public class main {
    public static BlockingQueue<String> queue = new LinkedBlockingQueue<>();

    public static void main(String ... args){

        new Thread(()->{
            try{
                while(true){
                    queue.put("Hello");
                    System.out.println("PUT");
                }
            }catch (Exception e){

            }
        }).start();

        startPooling(10);

    }


    public static void startPooling(int poolSize){
        for(int e = 0; e < poolSize; e++){
            new Thread(()->{
                try{
                    while(true){
                        String s = queue.take();
                        System.err.println("TAKE[" + queue.size() + "] :: " + s);
                    }
                }catch (Exception ee){

                }
            }).start();
        }
    }


}
