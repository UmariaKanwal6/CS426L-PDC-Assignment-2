import java.util.*;import java.util.concurrent.*;import java.util.concurrent.atomic.*;
public class InventoryCatalogManager {
    private static final int THREADS=10, OPERATIONS=50000;
    private static final int PRODUCTS=1000;
    private static Map<String,Integer> createCatalog(){Map<String,Integer> m=new HashMap<>();for(int i=0;i<PRODUCTS;i++)m.put("P"+i,100);return m;}
    public static void benchmarkSynchronizedMap()throws InterruptedException{Map<String,Integer> map=Collections.synchronizedMap(createCatalog());runBenchmark("synchronizedMap",map,false);}
    public static void benchmarkConcurrentMap()throws InterruptedException{ConcurrentHashMap<String,Integer> map=new ConcurrentHashMap<>(createCatalog());runBenchmark("ConcurrentHashMap",map,true);}
    private static void runBenchmark(String name,Map<String,Integer> map,boolean atomic)throws InterruptedException{
        ExecutorService pool=Executors.newFixedThreadPool(THREADS);CountDownLatch ready=new CountDownLatch(THREADS),start=new CountDownLatch(1),done=new CountDownLatch(THREADS);AtomicLong ops=new AtomicLong();
        for(int t=0;t<THREADS;t++){final int id=t;pool.execute(()->{ready.countDown();try{start.await();for(int i=0;i<OPERATIONS;i++){String key="P"+((i+id)%PRODUCTS);if(atomic){((ConcurrentHashMap<String,Integer>)map).compute(key,(k,v)->v==null?1:v+1);}else synchronized(map){map.put(key,map.get(key)+1);}map.get(key);ops.incrementAndGet();}}catch(InterruptedException e){Thread.currentThread().interrupt();}finally{done.countDown();}});}
        ready.await();long begin=System.nanoTime();start.countDown();done.await();long elapsed=System.nanoTime()-begin;pool.shutdown();double seconds=elapsed/1e9;
        System.out.printf("%s | Threads: %d | Operations: %d | Time: %.3f ms | Throughput: %.0f ops/sec%n",name,THREADS,ops.get(),elapsed/1e6,ops.get()/seconds);
    }
    public static void main(String[] args)throws InterruptedException{benchmarkSynchronizedMap();benchmarkConcurrentMap();}
}
