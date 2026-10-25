import java.util.concurrent.RecursiveTask;
public class ParallelRiskCalculator extends RecursiveTask<Double> {
    private static final int THRESHOLD = 1000;
    private final double[] values; private final int start, end;
    public ParallelRiskCalculator(double[] values, int start, int end) { this.values=values; this.start=start; this.end=end; }
    @Override protected Double compute() {
        if (end - start <= THRESHOLD) { double sum=0; for(int i=start;i<end;i++) sum+=values[i]; return sum; }
        int mid=(start+end)/2;
        ParallelRiskCalculator left=new ParallelRiskCalculator(values,start,mid);
        ParallelRiskCalculator right=new ParallelRiskCalculator(values,mid,end);
        left.fork(); double rightResult=right.compute(); return left.join()+rightResult;
    }
}
