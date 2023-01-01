import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class EX2_1 {

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

public static void main (String[] args) throws IOException{
        String [] str = createTextFiles(2,2,10);
        System.out.println(getNumOfLinesThreads(str));
}


      public static int getNumOfLines(String[] fileNames) throws IOException {
        int lines=0;
          for (String fileName : fileNames) {
              BufferedReader reader = new BufferedReader(new FileReader(fileName));
              while (reader.readLine() != null) lines++;
          }
          return lines;
      }

      public static class myThread extends Thread {
        private String [] filename=new String[1];
        private int lines;

        public myThread(String filename) {
            this.filename[0]=filename;
            lines=0;
        }

        @Override
        public void run() {
            try {
                this.lines=getNumOfLines(filename);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
          public int getLines() {
              return lines;
          }
      }
      public static int getNumOfLinesThreads(String[] fileNames){
        int lines=0;
          myThread[] threads=new myThread[fileNames.length];
          for (int i = 0; i < fileNames.length; i++) {
              threads [i]= new myThread(fileNames[i]);
          }
          for (int i = 0; i < fileNames.length; i++) {
              threads[i].start();
          }
          for (int i = 0; i < fileNames.length; i++){
              lines+=threads[i].getLines();
          }
          return lines;
    }

//    public int getNumOfLinesThreadPool(String[] fileNames){}
}