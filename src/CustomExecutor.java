import java.util.Comparator;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

<<<<<<< HEAD
public class CustomExecutor {

    private PriorityBlockingQueue<Runnable> queue;//task???
    private ThreadPoolExecutor executor;
    private int numOfCores;
    private volatile int maxPriority;
=======
>>>>>>> bd4d4019032b0c3386189f6bb63696aec0c219c9

/**CustomExecutor class extends ThreadPoolExecutor class and can execute Task class object tasks
 * by their priority.
 * The CustomExecutor holds those variables:
 * queue: holds all the waiting tasks.
 * numOfCores: holds the number of available processors for the program on this computer.
 * currentMaxPriority: holds the priority of the task with the highest priority for execution.
 * queuePriorities: holds the number of tasks of each priority that are waiting in queue.
 */
public class CustomExecutor extends ThreadPoolExecutor{
    private static PriorityBlockingQueue<Runnable> queue = new PriorityBlockingQueue<>();
    private static int numOfCores = Runtime.getRuntime().availableProcessors();
    private AtomicInteger currentMaxPriority;
    private AtomicIntegerArray queuePriorities;

    /**
     * CustomExecutor constructor (Using ThreadPoolExecutor constructor which get the
     * minimum of threads, maximum of threads, how much time to keep idle thread alive,
     * time unit for it and a queue for storing tasks.
     *
     * We also initialized currentMaxPriority to Integer.MaxValue and a new Array for
     * holding queued tasks priorities.
     */
    public CustomExecutor() {
<<<<<<< HEAD
        this.numOfCores = Runtime.getRuntime().availableProcessors();
        this.queue = new PriorityBlockingQueue<>();
        this.executor = new ThreadPoolExecutor(this.numOfCores/2, this.numOfCores-1,
=======
        super(numOfCores/2,numOfCores-1,
>>>>>>> bd4d4019032b0c3386189f6bb63696aec0c219c9
                300, TimeUnit.MILLISECONDS, queue);
        this.currentMaxPriority = new AtomicInteger(Integer.MAX_VALUE);
        this.queuePriorities = new AtomicIntegerArray(11);//The priority can be between 1-10.
    }

<<<<<<< HEAD
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
        queue.add(futureTask);
        executor.execute(futureTask);
        return futureTask;
=======

    /**
     * The method submit a Task object to the CustomExecutor for executing it sometime in
     * the future.
     * @param task Task class object
     * @return a FutureTask Object which holds the result of task.
     * @param <T> generic type of Task object.
     */
    public <T> Future<T> submit(Task<T> task) {
        queuePriorities.getAndIncrement(task.getPriority());
        for (int i=1; i<queuePriorities.length(); i++) {
            if (queuePriorities.get(i) != 0) {
                currentMaxPriority.set(i);
                break;
            }
        }
        super.execute(task);
        return task;
>>>>>>> bd4d4019032b0c3386189f6bb63696aec0c219c9
    }


    /**
     * The method creates Task object from Callable and Tasktype objects
     * and submit it to the CustomExecutor.
     * @param operation a callable operation.
     * @param type type of task (holds task's priority).
     * @return call the submit method with Task object)
     * @param <T> generic type of Task object.
     */
    public <T> Future<T> submit(Callable<T> operation, TaskType type) {
        return submit(Task.createTask(operation, type));
    }


    /**
     * The method creates Task object from Callable object
     * and submit it to the CustomExecutor.
     * @param operation a callable operation.
     * @return call the submit method with Task object)
     * @param <T> generic type of Task object.
     */
    public <T> Future<T> submit(Callable<T> operation) {
        return submit(Task.createTask(operation));
    }


    /**
     * Tasks queue getter
     * @return queue
     */
    public PriorityBlockingQueue<Runnable> getQueue() {
        return queue;
    }


    /**
     * currentMaxPriority variable getter.
     * @return the priority of the task with the highest priority for execution.
     */
    public AtomicInteger getCurrentMax() {
        return currentMaxPriority;
    }


    /**
     * Method invoked prior to executing the given Runnable in the given thread.
     * This method is invoked by thread t that will execute task r,
     * and may be used to re-initialize ThreadLocals, or to perform logging.
     * This implementation does nothing, but may be customized in subclasses.
     * Note: To properly nest multiple overridings, subclasses should generally
     * invoke super.beforeExecute at the end of this method
     *
     * We override this method to update currentMaxPriority a moment before a task
     * get out from the queue. if there are no more tasks in the queue- set
     * it to -1.
     *
     * @param t the thread that will run task {@code r}
     * @param r the task that will be executed
     */
    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        queuePriorities.getAndDecrement(((Task<?>)r).getPriority());
        for (int i = 0; i < queuePriorities.length(); i++) {
            if (queuePriorities.get(i) != 0) {
                currentMaxPriority.set(i);
                break;
            } else if (i == queuePriorities.length()-1) {
                currentMaxPriority.set(-1);
            }
        }
        super.beforeExecute(t, r);
    }


    /**
     * Initiates an orderly shutdown in which previously submitted tasks are executed,
     * but no new tasks will be accepted. Invocation has no additional effect if already shut down.
     */
    public void gracefullyTerminate() {
        super.shutdown();
    }

<<<<<<< HEAD
    public class TaskFuture<V> extends FutureTask<V> implements Comparable {

        @Override
        public String toString() {
            return "TaskFuture{" +
                    "type=" + type.getPriorityValue() +
                    '}';
        }

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
=======
}
>>>>>>> bd4d4019032b0c3386189f6bb63696aec0c219c9
