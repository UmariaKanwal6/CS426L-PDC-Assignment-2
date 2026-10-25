import java.util.concurrent.*;
public class SensorGridMonitorApp {
    public static void main(String[] args)throws Exception{
        double[] data=new double[50000];java.util.Random random=new java.util.Random(42);for(int i=0;i<data.length;i++)data[i]=15+random.nextDouble()*40;
        ExecutorService workers=Executors.newFixedThreadPool(4);long start=System.nanoTime();
        Future<Double> min=workers.submit(()->new ParallelMinSearch(data,0,data.length).processGrid());
        Future<Double> max=workers.submit(()->new ParallelMaxSearch(data,0,data.length).processGrid());
        System.out.printf("Minimum sensor temperature: %.2f%n",min.get());System.out.printf("Maximum vibration/reading: %.2f%n",max.get());
        System.out.printf("Execution time: %.3f ms%n",(System.nanoTime()-start)/1_000_000.0);workers.shutdown();
    }
}
