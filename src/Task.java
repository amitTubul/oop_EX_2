import java.util.concurrent.Callable;

public class Task<T> implements Comparable<Task<T>> {
    private Callable<T> operation;
    private TaskType type;
    private T result;

    private Task(Callable<T> operation, TaskType type) {
        this.operation = operation;
        this.type = type;
        this.result=null;
    }

    public static <T> Task<T> createTask(Callable<T> operation, TaskType type) {
        return new Task<>(operation, type);
    }

    public static <V> Task<V> createTask(Callable<V> operation) {
        return new Task<>(operation, TaskType.OTHER);
    }

    public int getPriority() {
        return type.getPriorityValue();
    }

    public void setPriority(int x){
        type.setPriority(x);
    }

    public Callable<T> getOperation() {
        return this.operation;
    }

    public TaskType getType() {
        return type;
    }

//    public T get() {
//        return this.result;
//    }
//
//    public void run() {
//        try {
//            this.result = operation.call();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

    public T call() throws Exception {
        return operation.call();
    }

    @Override
    public int compareTo(Task<T> other) {
        return Integer.compare(this.getPriority(), other.getPriority());
    }
}