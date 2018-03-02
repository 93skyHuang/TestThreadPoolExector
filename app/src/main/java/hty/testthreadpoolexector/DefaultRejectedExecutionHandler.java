package hty.testthreadpoolexector;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author: skyHuang @data: 2018/3/2.
 * @editor: null @data:null
 * @description: 线程池的饱和策略，当阻塞队列满了，且没有空闲的工作线程，如果继续提交任务，必须采取一种策略处理该任务，线程池提供了4种策略：
 * 1、AbortPolicy：直接抛出异常，默认策略；
 * 2、CallerRunsPolicy：用调用者所在的线程来执行任务；
 * 3、DiscardOldestPolicy：丢弃阻塞队列中靠最前的任务，并执行当前任务；
 * 4、DiscardPolicy：直接丢弃任务；
 * 当然也可以根据应用场景实现RejectedExecutionHandler接口，自定义饱和策略，如记录日志或持久化存储不能处理的任务。
 */

public class DefaultRejectedExecutionHandler implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
    }
}
