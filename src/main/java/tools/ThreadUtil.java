package tools;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author
 * @version 1.0.0
 * @ClassName ThreadUtil.java
 * @Description TODO
 * @createTime 2022年06月11日 12:05:00
 */
public class ThreadUtil {
    private static final int CORE_POOL_SIZE = 3;
    private static final int MAX_POOL_SIZE = 8;
    private static final int QUEUE_CAPACITY = 100;
    private static final Long KEEP_ALIVE_TIME = 1L;

    private static ThreadPoolExecutor executor;

    public static void init() {
        executor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(QUEUE_CAPACITY),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }

    public static void execute(Runnable runnable) {
        executor.execute(runnable);
    }

    public static void shutdown(){
        executor.shutdown();
    }
}
