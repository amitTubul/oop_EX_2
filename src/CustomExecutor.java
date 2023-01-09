import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomExecutor {
    private PriorityBlockingQueue<Runnable> queue;
    private ThreadPoolExecutor executor;
    private int numOfCores;
    private volatile int maxPriority;

    public CustomExecutor() {
        this.numOfCores = Runtime.getRuntime().availableProcessors();
        this.queue = new PriorityBlockingQueue<>();
        this.executor = new ThreadPoolExecutor(this.numOfCores/2, this.numOfCores-1,
                300, TimeUnit.MILLISECONDS, queue);
        this.maxPriority = 0;
    }

    public <T> Future<T> submit(Task<T> task) {
        maxPriority = Math.max(maxPriority, task.getPriority());
        RunnableFuture<T> ftask = new FutureTask(task);
        executor.execute(ftask);
        return ftask;
//        Future<T> result;
//        try {
//            result = executor.submit(task);
//        } catch (NullPointerException e) {
//            throw new RuntimeException(e);
//        }
//        return result;
    }

    public <T> Future<T> submit(Callable<T> operation, TaskType type) {
        return submit(Task.createTask(operation, type));
    }

    public <T> Future<T> submit(Callable<T> operation) {
        return submit(Task.createTask(operation));
    }

    public int getCurrentMax() {
        return maxPriority;
    }

    public void gracefullyTerminate() {
        this.executor.shutdown();
    }

}
