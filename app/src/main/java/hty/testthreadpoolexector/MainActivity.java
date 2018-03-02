package hty.testthreadpoolexector;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    int j = 0;
    int k = 0;
    int m = 0;
    int n = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();//线程池中一次最多可以同时运行的线程。
        //线程池中允许的最大线程数。如果当前阻塞队列满了，且继续提交任务，则创建新的线程执行任务，前提是当前线程数小于maximumPoolSize；
        int maximumPoolSize = NUMBER_OF_CORES * 2;
        Log.i(TAG, "onCreate: num=" + NUMBER_OF_CORES);
        //线程空闲时的存活时间，即当线程没有任务执行时，继续存活的时间；默认情况下，该参数只在线程数大于corePoolSize时才有用；
        int KEEP_ALIVE_TIME = 1;
        TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;//时间单位
        Log.i(TAG, "onCreate: "+KEEP_ALIVE_TIME_UNIT);
        BlockingQueue<Runnable> taskQueue = new LinkedBlockingDeque<Runnable>();//阻塞队列
        BlockingQueue<Runnable> taskQueue1 = new ArrayBlockingQueue<Runnable>(1);//数组阻塞队列
        BlockingQueue<Runnable> taskQueue2 = new LinkedBlockingQueue<>(1);//链表结构阻塞队列
        BlockingQueue<Runnable> taskQueue3 = new PriorityBlockingQueue<>(1);//优先级阻塞队列
        final ExecutorService executorService = new ThreadPoolExecutor(NUMBER_OF_CORES, maximumPoolSize,
                KEEP_ALIVE_TIME, KEEP_ALIVE_TIME_UNIT, taskQueue,new DefaultThreadFactory("test"));
        for (int i = 0; i < 10; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Log.i(TAG, "run: execute" + j++);
                        Thread.sleep(2000);
                        Log.i(TAG, "run: finish" + k++);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        Future future=executorService.submit(new Runnable() {
            @Override
            public void run() {

            }
        });
        Future future1=executorService.submit(new Callable() {
            @Override
            public Object call() throws Exception {
                Log.i(TAG, "call: execute1" + n++);
                Thread.sleep(2000);
                Log.i(TAG, "call: finish1" + m++);
                return null;
            }
        });
    }

}
