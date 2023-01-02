import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class Ex2_1 {

    public static void main (String[] args) throws IOException, InterruptedException, ExecutionException {
        String [] str = createTextFiles(100,2,100);
        System.out.println("No threads:");
        long startTime = System.nanoTime();
        System.out.println("Number of lines:" + getNumOfLines(str));
        long endTime = System.nanoTime();
        long duration = (endTime - startTime)/1000000;  //divide by 1000000 to get milliseconds.
        System.out.println("Runtime: "+ duration +" ms");
        System.out.println("\n---------------------------------");

        System.out.println("Using threads:");
        startTime = System.nanoTime();
        System.out.println("Number of lines:" + getNumOfLinesThreads(str));
        endTime = System.nanoTime();
        duration = (endTime - startTime)/1000000;  //divide by 1000000 to get milliseconds.
        System.out.println("Runtimes: "+ duration+" ms");
        System.out.println("\n---------------------------------");

        System.out.println("Using threadPool:");
        startTime = System.nanoTime();
        System.out.println("Number of lines:" + getNumOfLinesThreadPool(str));
        endTime = System.nanoTime();
        duration = (endTime - startTime)/1000000;  //divide by 1000000 to get milliseconds.
        System.out.println("Runtime: "+ duration+" ms");
    }


    public static String[] createTextFiles(int n, int seed, int bound) throws IOException {
        Random rand = new Random(seed);
        String [] files_names =new String[n];
        for (int i = 0; i < n; i++) {
            int x = rand.nextInt(bound);
            FileOutputStream file = new FileOutputStream("file_" + (i + 1),false);
            files_names[i]="file_" + (i + 1);
            for (int j = 0; j < x-1 ; j++) {
                file.write("hello world\n".getBytes(StandardCharsets.UTF_8));
            }
            file.write("hello world".getBytes(StandardCharsets.UTF_8));
            file.close();
        }
        return files_names;
    }


    public static int getNumOfLines(String[] fileNames) throws IOException {
        int lines=0;
        for (String fileName : fileNames) {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            while (reader.readLine() != null) lines++;
        }
        return lines;
    }


    public static int getNumOfLinesThreads(String[] fileNames) throws InterruptedException {
        myThread[] threads=new myThread[fileNames.length];
        for(int i = 0; i < fileNames.length; i++) {
            threads [i]=new myThread(fileNames[i]);
            threads [i].start();
        }
        for(myThread thread:threads){
            thread.join();
        }
        int totalLineCount = 0;
        for (myThread thread:threads) {
            totalLineCount += thread.getLines();
        }
        return totalLineCount;
    }


    public static int getNumOfLinesThreadPool(String[] fileNames)throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(fileNames.length);

        List<Future<Integer>> list = new ArrayList<>();

        for (String fileName : fileNames) {
            Callable<Integer> task = new LineCounterCallable(fileName);
            Future<Integer> future = executor.submit(task);
            list.add(future);
        }

        int totalLineCount = 0;
        for (Future<Integer> future : list) {
            totalLineCount += future.get();
        }

        executor.shutdown();
        return totalLineCount;
    }


    public static class myThread extends Thread {
        private final String filename;
        private int lines;

        public myThread(String filename) {
            this.filename=filename;
            lines=0;
        }

        @Override
        public void run() {
            try {
                this.lines=getNumOfLines(new String[]{filename});
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        public int getLines() {
            return this.lines;
        }
    }


    public static class LineCounterCallable implements Callable<Integer> {

        private final String fileName;

        public LineCounterCallable(String fileName) {
            this.fileName = fileName;
        }

        public Integer call() throws IOException {
            return getNumOfLines(new String[]{fileName});
        }
    }
}