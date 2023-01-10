import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomExecutor {
    private PriorityBlockingQueue<Runnable> queue;
    private ThreadPoolExecutor executor;
    private int numOfCores;
    private volatile int maxPriority;

    public PriorityBlockingQueue<Runnable> getQueue() {
        return queue;
    }

    public CustomExecutor() {
        this.numOfCores = Runtime.getRuntime().availableProcessors();
        this.queue = new PriorityBlockingQueue<>();
//        this.executor = new ThreadPoolExecutor(this.numOfCores/2, this.numOfCores-1,
//                300, TimeUnit.MILLISECONDS, queue);
        this.executor = new ThreadPoolExecutor(1,1,
                300, TimeUnit.MILLISECONDS, queue);
        this.maxPriority = 0;
    }

//    public <T> Future<T> submit(Task<T> task) {
//        maxPriority = Math.max(maxPriority, task.getPriority());
//        Future<T> result;
//        try {
//            result = (Future<T>) executor.submit(task);
//        } catch (NullPointerException e) {
//            throw new RuntimeException(e);
//        }
//        return result;
//    }
    public <T> Future<T> submit(Task<T> task) {
        maxPriority = Math.max(maxPriority, task.getPriority());
        TaskFuture<T> futureTask = new TaskFuture<>(task.getOperation(), task.getType());
//        queue.add(futureTask);
        executor.execute(futureTask);
        return futureTask;
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

    public class TaskFuture<V> extends FutureTask<V> implements Comparable {

        private TaskType type;

        public TaskFuture(Callable<V> operation, TaskType type) {
            super(operation);
            this.type = type;
        }

        public int getPriority() {
            return type.getPriorityValue();
        }

        public void setPriority(int x) {
            type.setPriority(x);
        }

        public TaskType getType() {
            return type;
        }

        @Override
        public int compareTo(Object other) {
            return Integer.compare(this.getPriority(), ((TaskFuture<?>)other).getPriority());
        }

    }
}
