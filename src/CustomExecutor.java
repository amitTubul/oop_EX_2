import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;


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
        super(numOfCores/2,numOfCores-1,
                300, TimeUnit.MILLISECONDS, queue);
        this.currentMaxPriority = new AtomicInteger(Integer.MAX_VALUE);
        this.queuePriorities = new AtomicIntegerArray(10);//The priority can be between 1-10.
    }


    /**
     * The method submit a Task object to the CustomExecutor for executing it sometime in
     * the future.
     * @param task Task class object
     * @return a FutureTask Object which holds the result of task.
     * @param <T> generic type of Task object.
     */
    public <T> Future<T> submit(Task<T> task) {
        queuePriorities.getAndIncrement(task.getPriority());
        for (int i=0; i<queuePriorities.length(); i++) {
            if (queuePriorities.get(i) != 0) {
                currentMaxPriority.set(i);
                break;
            }
        }
        super.execute(task);
        return task;
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
     *The method get currentMaxPriority variable.
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
     * it to 0.
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
            } else if (i == queuePriorities.length()-1 && queuePriorities.get(0) == 0) {
                currentMaxPriority.set(0);
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

}
