import java.util.concurrent.ForkJoinPool;
public class ForkJoinPortfolioApp {
    public static void main(String[] args){
        double[] assets=new double[10000]; for(int i=0;i<assets.length;i++)assets[i]=500+(i%1500);
        ForkJoinPool pool=new ForkJoinPool(); long start=System.nanoTime();
        double risk=pool.invoke(new ParallelRiskCalculator(assets,0,assets.length));
        pool.invoke(new PortfolioThresholdTask(assets,0,assets.length,1500));
        long elapsed=System.nanoTime()-start;
        System.out.printf("Initial cumulative risk: %.2f%n",risk);
        System.out.printf("Stress-tested first value: %.2f%n",assets[0]);
        System.out.printf("Processing time: %.3f ms%n",elapsed/1_000_000.0); pool.shutdown();
    }
}
