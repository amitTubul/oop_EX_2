# object oriented programming excercise 2:
this excercise includes two main directories: Ex2_1 and Ex2_2
each directory is intended to answer part 1 and part 2 respectively
each part includes src folder contains the relevant files for the relevant part

#EX2_1:
contains the requested functions for this part of exercise.

createTextFiles(): creates n files with a random number of lines, every line conteins the string:"hello world", the function returns a string array includes
the names of the files.

getNumOfLines():reads the n files using the string array includes all the files names and sum the amount of all lines in the files iteratively. 

getNumOfLinesThreads():reads the n files using the string array includes all the files names and sum the amount of all lines in the files using threads
which use "myThread" class.

getNumOfLinesThreadPool():reads the n files using the string array includes all the files names and sum the amount of all lines in the files
using threadpool which using "LineCounterCallable" class.

#efficiency:
getNumOfLinesThreads is more efficient than getNumOfLines because it can process multiple files in parallel, whereas getNumOfLines processes them one at a time.
By using multiple threads, the program can take advantage of multiple CPU cores, potentially completing the task more quickly.
Also, when the number of files is quite big the sequential implementation can take a long time while parallel implementation will
divide the work among all the threads, thus completing the task much faster.

getNumOfLinesThreadPool is more efficient than getNumOfLinesThreads because it makes use of a thread pool, which is a pre-configured and reusable set of threads. Instead of creating a new thread for every file, the thread pool will reuse the existing threads. This can be more efficient than creating new threads because creating and destroying threads can be a costly operation.

test output of 100 files:

<img width="214" alt="image" src="https://user-images.githubusercontent.com/117980808/211850147-3a922f78-c9d6-4b81-aa10-835841ce159b.png">

classes diagram of Ex2_1:

![image](https://user-images.githubusercontent.com/117980808/211852668-bf84ac63-a1f0-4f32-856f-6b20bc0a16f5.png)




#EX2_2:


