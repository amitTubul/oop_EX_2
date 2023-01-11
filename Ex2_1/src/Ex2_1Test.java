import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

class Ex2_1Test {

    @Test
    public void EX2_1Tests() throws IOException, InterruptedException, ExecutionException {

        String [] str = Ex2_1.createTextFiles(100,2,100);
        System.out.println("No threads:");
        long startTime = System.nanoTime();
        System.out.println("Number of lines:" + Ex2_1.getNumOfLines(str));
        long endTime = System.nanoTime();
        long duration = (endTime - startTime)/1000000;  //divide by 1000000 to get milliseconds.
        System.out.println("Runtime: "+ duration +" ms");
        System.out.println("\n---------------------------------");

        System.out.println("Using threads:");
        startTime = System.nanoTime();
        System.out.println("Number of lines:" + Ex2_1.getNumOfLinesThreads(str));
        endTime = System.nanoTime();
        duration = (endTime - startTime)/1000000;  //divide by 1000000 to get milliseconds.
        System.out.println("Runtimes: "+ duration+" ms");
        System.out.println("\n---------------------------------");

        System.out.println("Using threadPool:");
        startTime = System.nanoTime();
        System.out.println("Number of lines:" + Ex2_1.getNumOfLinesThreadPool(str));
        endTime = System.nanoTime();
        duration = (endTime - startTime)/1000000;  //divide by 1000000 to get milliseconds.
        System.out.println("Runtime: "+ duration+" ms");
    }
}