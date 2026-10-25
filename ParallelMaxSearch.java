import java.util.concurrent.*;
public class ParallelMaxSearch extends SensorSearchEngine {
    public ParallelMaxSearch(double[] data,int start,int end){super(data,start,end);}
    public double processGrid(){return ForkJoinPool.commonPool().invoke(new MaxTask(sensorData,startIdx,endIdx));}
    private static class MaxTask extends RecursiveTask<Double>{double[] a;int s,e;MaxTask(double[]a,int s,int e){this.a=a;this.s=s;this.e=e;}
        protected Double compute(){if(e-s<=1000){double m=Double.NEGATIVE_INFINITY;for(int i=s;i<e;i++)m=Math.max(m,a[i]);return m;}int mid=(s+e)/2;MaxTask l=new MaxTask(a,s,mid),r=new MaxTask(a,mid,e);l.fork();return Math.max(l.join(),r.compute());}}
}
