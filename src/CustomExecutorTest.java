import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import java.util.concurrent.*;
class Tests {


//    public static final Logger logger = LoggerFactory.getLogger(Tests.class);
//    @Test
//    public void partialTest() {
//        CustomExecutor customExecutor = new CustomExecutor();
//        Task<Integer> task = Task.createTask(() -> {
//            int sum = 0;
//            for (int i = 1; i <= 10; i++) {
//                sum += i;
//            }
//            return sum;
//        }, TaskType.COMPUTATIONAL);
//
//        Callable<String> callable2 = () -> {
//            StringBuilder sb = new StringBuilder("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
//            return sb.reverse().toString();
//        };
//
//        for(int i=0; i<100; i++){
//            customExecutor.submit(task);
//            customExecutor.submit(callable2, TaskType.IO);
//            customExecutor.submit(callable2, TaskType.OTHER);
//        }
//
//        System.out.println(customExecutor.getQueue().toString());


//    /**
//     * check if the queue add by priority,
//     * set core and max to be 1, because we need
//     * that task get in the workqueue.
//     *
//     * Print - print the priorities og the queue's tasks
//     * by the order in the queue
//     */
//    @Test
//    public void partialTest() {
//        CustomExecutor customExecutor = new CustomExecutor();
//        for (int i = 0; i < 8; i++) {
//            Callable<String> testIO = () -> {
//                StringBuilder sb = new StringBuilder("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
//                return sb.reverse().toString();
//            };
//
//            var reverseTask1 = customExecutor.submit(testIO, TaskType.IO);
//            var task = Task.createTask(()->{
//                int sum = 0;
//                for (int j = 1; j <= 10; j++) {
//
//                    sum += j;
//                }
//                return sum;
//            }, TaskType.COMPUTATIONAL);
//            var sumTask = customExecutor.submit(task);
//            logger.info(()-> "Current maximum priority = " +
//                    customExecutor.getCurrentMax());
//
//            var testMath = customExecutor.submit(() -> {
//
//                return 1000 * Math.pow(1.02, 5);
//            }, TaskType.OTHER);
//
//            Callable<String> testIO2 = () -> {
//                StringBuilder sb = new StringBuilder("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
//
//                return sb.reverse().toString();
//            };
//
//            var testt = customExecutor.submit(testIO2, TaskType.IO);
//            logger.info(()-> "Current maximum priority = " +
//                    customExecutor.getCurrentMax());
//
//            final String get1;
//            final double get2;
//            final int get3;
//            System.out.println(customExecutor.getQueue().toString());
//
//            try {
//                get1 = testt.get();
//                get2 = testMath.get();
//                get3 = (int) sumTask.get();
//            } catch (InterruptedException | ExecutionException e) {
//                throw new RuntimeException(e);
//            }
//            logger.info(()-> "Reversed String = " + get1);
//            logger.info(()->String.valueOf("Total Price = " + get2));
//            logger.info(()-> "Current maximum priority = " +
//                    customExecutor.getCurrentMax());
//
//        }
//        customExecutor.gracefullyTerminate();
//    }


    public static final Logger logger = LoggerFactory.getLogger(Tests.class);

//    @Test
//    public void partialTest() {
//        CustomExecutor customExecutor = new CustomExecutor();
//        for (int i = 0; i < 20; i++) {
//            Callable<String> testIO = () -> {
//                StringBuilder sb = new StringBuilder("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
//                return sb.reverse().toString();
//            };
//
//            var reverseTask1 = customExecutor.submit(testIO, TaskType.IO);
//            var task = Task.createTask(() -> {
//                int sum = 0;
//                for (int j = 1; j <= 10; j++) {
//                    sum += j;
//                }
//                return sum;
//            }, TaskType.COMPUTATIONAL);
//            var sumTask = customExecutor.submit(task);
//            var reverseTask2 = customExecutor.submit(testIO, TaskType.IO);
//            var reverseTask3 = customExecutor.submit(testIO, TaskType.IO);
//            var reverseTask4 = customExecutor.submit(testIO, TaskType.IO);
//            var reverseTask5 = customExecutor.submit(testIO, TaskType.IO);
//            var sumTask1 = customExecutor.submit(task);
//            var sumTask2 = customExecutor.submit(task);
//            var sumTask3 = customExecutor.submit(task);
//            var sumTask4 = customExecutor.submit(task);
//            var reverseTask6 = customExecutor.submit(testIO, TaskType.IO);
//            var reverseTask7 = customExecutor.submit(testIO, TaskType.IO);
//            var reverseTask8 = customExecutor.submit(testIO, TaskType.IO);
//            var reverseTask9 = customExecutor.submit(testIO, TaskType.IO);
//            var sumTask6 = customExecutor.submit(task);
//            var sumTask7 = customExecutor.submit(task);
//            var sumTask8 = customExecutor.submit(task);
//            var sumTask9 = customExecutor.submit(task);
//            var reverseTask10 = customExecutor.submit(testIO, TaskType.IO);
//            var reverseTask11 = customExecutor.submit(testIO, TaskType.IO);
//            var reverseTask12 = customExecutor.submit(testIO, TaskType.IO);
//            var reverseTask13 = customExecutor.submit(testIO, TaskType.IO);
//            var sumTask10 = customExecutor.submit(task);
//            var sumTask11 = customExecutor.submit(task);
//            var sumTask12 = customExecutor.submit(task);
//            var sumTask13 = customExecutor.submit(task);
//            var reverseTask14 = customExecutor.submit(testIO, TaskType.IO);
//            var reverseTask15 = customExecutor.submit(testIO, TaskType.IO);
//            var reverseTask16 = customExecutor.submit(testIO, TaskType.IO);
//            var reverseTask17 = customExecutor.submit(testIO, TaskType.IO);
//            var sumTask14 = customExecutor.submit(task);
//            var sumTask15 = customExecutor.submit(task);
//            var sumTask16 = customExecutor.submit(task);
//            var sumTask17 = customExecutor.submit(task);




//            var reverseTask2 = customExecutor.submit(testIO, TaskType.IO);
//            var sumTask1 = customExecutor.submit(task);
//            var reverseTask3 = customExecutor.submit(testIO, TaskType.IO);
//            var sumTask2 = customExecutor.submit(task);
//            var reverseTask4 = customExecutor.submit(testIO, TaskType.IO);
//            var sumTask3 = customExecutor.submit(task);
//            var reverseTask5 = customExecutor.submit(testIO, TaskType.IO);
//            var sumTask4 = customExecutor.submit(task);


//            var testMath = customExecutor.submit(() -> {
//                return 1000 * Math.pow(1.02, 5);
//            }, TaskType.COMPUTATIONAL);
//
//            Callable<String> testIO2 = () -> {
//                StringBuilder sb = new StringBuilder("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
//                return sb.reverse().toString();
//            };
//            var testt = customExecutor.submit(testIO2, TaskType.IO);
//            System.out.println(customExecutor.getQueue().toString());
//            customExecutor.getCurrentMax();
//            final String get1;
//            final double get2;
//            final int get3;
//            try {
//                get1 = testt.get();
//                get2 = testMath.get();
//                get3 = (int) sumTask.get();
//            } catch (InterruptedException | ExecutionException e) {
//                throw new RuntimeException(e);
//            }
//            logger.info(() -> "Reversed String = " + get1);
//            logger.info(() -> String.valueOf("Total Price = " + get2));
//            logger.info(() -> "Current maximum priority = " +
//                    customExecutor.getCurrentMax());
//        }
//        customExecutor.gracefullyTerminate();

    @Test

    public void partialTest() {
        CustomExecutor customExecutor = new CustomExecutor();
        Task<Integer> task = Task.createTask(() -> {
            int sum = 0;
            for (int i = 1; i <= 10; i++) {
                sum += i;
            }
            return sum;
        }, TaskType.COMPUTATIONAL);
        Future<Integer> sumTask = customExecutor.submit(task);
        final int sum;
        try {
            sum = sumTask.get(1, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            throw new RuntimeException(e);
        }
        logger.info(() -> "Sum of 1 through 10 = " + sum);
        Callable<Double> callable1 = () -> {
            return 1000 * Math.pow(1.02, 5);
        };
        Callable<String> callable2 = () -> {
            StringBuilder sb = new StringBuilder("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
            return sb.reverse().toString();
        };
        // var is used to infer the declared type automatically
        Future<Double> priceTask = customExecutor.submit(() -> {
            return 1000 * Math.pow(1.02, 5);
        }, TaskType.COMPUTATIONAL);
        Future<String> reverseTask = customExecutor.submit(callable2, TaskType.IO);
        final Double totalPrice;
        final String reversed;
        try {
            totalPrice = priceTask.get();
            reversed = reverseTask.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        logger.info(() -> "Reversed String = " + reversed);
        logger.info(() -> String.valueOf("Total Price = " + totalPrice));
        logger.info(() -> "Current maximum priority = " +
                customExecutor.getCurrentMax());
        customExecutor.gracefullyTerminate();
    }
}
