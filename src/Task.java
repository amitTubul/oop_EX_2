class Task<T> implements Comparable<Task<T>> {
    private Callable<T> operation;
    private TaskType type;

    private Task(Callable<T> operation, TaskType type) {
        this.operation = operation;
        this.type = type;
    }

    public static <V> Task<V> of(Callable<V> operation, TaskType type) {
        return new Task<>(operation, type);
    }

    private Task(Callable<T> operation) {
        this.operation = operation;
    }

    public static <V> Task<V> of(Callable<V> operation) {
        return new Task<>(operation);
    }

    public int getPriority() {
        return type.getPriorityValue();
    }

    public TaskType getType() {
        return type;
    }

    public T call() throws Exception {
        return operation.call();
    }

    @Override
    public int compareTo(Task<T> other) {
        return Integer.compare(this.getPriority(), other.getPriority());
    }
}
