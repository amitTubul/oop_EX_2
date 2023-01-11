import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;


/**
 * Task class represent a generic task that can be submitted to CustomExecutor for execution.
 * The class extends FutureTask class because Custom Executor can execute only Future objects.
 * It also implements Comparable interface for comparing between 2 tasks by their priority.
 * @param <T> a generic type of task.
 * The Task holds those variables:
 * operation: a callable object.
 * type: Tasktype object which represent task priority.
 */
public class Task<T> extends FutureTask<T> implements Comparable<Task<T>> {
    private Callable<T> operation;
    private TaskType type;


    /**
     * Task constructor (Using also FutureTask constructor)
     * @param operation
     * @param type
     */
    private Task(Callable<T> operation, TaskType type) {
        super(operation);
        this.operation = operation; // not really needed, but allows later access to it by its getter.
        this.type = type;
    }

    /**
     * Factory method which create task object from Callable and TaskType objects by Task
     * private constructor.
     */
    public static <T> Task<T> createTask(Callable<T> operation, TaskType type) {
        return new Task<>(operation, type);
    }


    /**
     * Factory method which create task object from Callable object by Task
     * private constructor.
     */
    public static <V> Task<V> createTask(Callable<V> operation) {
        return new Task<>(operation, TaskType.OTHER);
    }


    /***
     * @return string which represent task object.
     */
    @Override
    public String toString() {
        return "Task{type=" + type +
                "priority=" + type.getPriorityValue() +
                "}\n";
    }


    /**
     * Task's object priority getter.
     * @return Task's object priority.
     */
    public int getPriority() {
        return type.getPriorityValue();
    }

    /**
     * Task's object priority setter.
     * @param x priority to set for task.
     */
    public void setPriority(int x){
        type.setPriority(x);
    }


    /**
     * Task's object Callable operation getter.
     * @return Task's object Callable operation.
     */
    public Callable<T> getOperation() {
        return this.operation;
    }


    /**
     * Task's object TaskType getter.
     * @return Task's object TaskType.
     */
    public TaskType getType() {
        return type;
    }


    /**
     * Not really needed because we extend FutureTask which call the callable object
     * and throw an exception if needed.
     *
     * We implement it anyway because it was one of the requirements of the Exercise.
     * @return call the callable operation.
     * @throws Exception
     */
    public T call() throws Exception {
        return operation.call();
    }


    /**
     * The method compare 2 tasks by their priority value.
     * @param other the Task object to be compared.
     * @return result of comparing.
     */
    @Override
    public int compareTo(Task<T> other) {
        return Integer.compare(this.getPriority(), other.getPriority());
    }
}
