package hty.testthreadpoolexector;

import android.support.annotation.NonNull;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: skyHuang @data: 2018/3/2.
 * @editor: null @data:null
 * @description: 为每个线程取名字
 */

public class DefaultThreadFactory implements ThreadFactory {
    private final ThreadGroup threadGroup;
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String threadNamePrefix;

    public DefaultThreadFactory(String threadName) {
        SecurityManager securityManager = System.getSecurityManager();
        threadGroup = (securityManager != null) ? securityManager.getThreadGroup() : Thread.currentThread().getThreadGroup();
        threadNamePrefix = threadName + "-thread-";

    }

    @Override
    public Thread newThread(@NonNull Runnable r) {
        Thread thread = new Thread(threadGroup, r, threadNamePrefix + threadNumber.getAndIncrement(), 0);
        if (thread.isDaemon()) {
            thread.setDaemon(false);
        }
        if (thread.getPriority() != Thread.MAX_PRIORITY) {
            thread.setPriority(Thread.MAX_PRIORITY);
        }
        return thread;
    }
}
