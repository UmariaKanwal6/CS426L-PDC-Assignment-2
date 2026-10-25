import java.util.concurrent.*;
public class ParallelMinSearch extends SensorSearchEngine {
    public ParallelMinSearch(double[] data,int start,int end){super(data,start,end);}
    public double processGrid(){return ForkJoinPool.commonPool().invoke(new MinTask(sensorData,startIdx,endIdx));}
    private static class MinTask extends RecursiveTask<Double>{double[] a;int s,e;MinTask(double[]a,int s,int e){this.a=a;this.s=s;this.e=e;}
        protected Double compute(){if(e-s<=1000){double m=Double.POSITIVE_INFINITY;for(int i=s;i<e;i++)m=Math.min(m,a[i]);return m;}int mid=(s+e)/2;MinTask l=new MinTask(a,s,mid),r=new MinTask(a,mid,e);l.fork();return Math.min(l.join(),r.compute());}}
}
