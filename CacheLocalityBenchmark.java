public class CacheLocalityBenchmark {
    private static final int N=2000,REPEATS=10;private final double[][] matrix=new double[N][N];
    public CacheLocalityBenchmark(){for(int i=0;i<N;i++)for(int j=0;j<N;j++)matrix[i][j]=i+j+0.5;}
    public double traverseRowMajor(){double sum=0;for(int i=0;i<N;i++)for(int j=0;j<N;j++)sum+=matrix[i][j];return sum;}
    public double traverseColumnMajor(){double sum=0;for(int j=0;j<N;j++)for(int i=0;i<N;i++)sum+=matrix[i][j];return sum;}
    public static void main(String[]args){CacheLocalityBenchmark b=new CacheLocalityBenchmark();double rowSum=0,colSum=0;long rowStart=System.nanoTime();for(int r=0;r<REPEATS;r++)rowSum+=b.traverseRowMajor();long rowNs=System.nanoTime()-rowStart;long colStart=System.nanoTime();for(int r=0;r<REPEATS;r++)colSum+=b.traverseColumnMajor();long colNs=System.nanoTime()-colStart;
        double rowMs=rowNs/1e6,colMs=colNs/1e6;System.out.printf("Row-Major time (10 runs): %.3f ms | checksum: %.2f%n",rowMs,rowSum);System.out.printf("Column-Major time (10 runs): %.3f ms | checksum: %.2f%n",colMs,colSum);System.out.printf("Column/Row speed ratio: %.2fx%n",colMs/rowMs);}
}
