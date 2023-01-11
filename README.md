# object oriented programming excercise 2:
this excercise includes two main directories: Ex2_1 and Ex2_2
each directory is intended to answer part 1 and part 2 respectively
each part includes src folder contains the relevant files for the relevant part

## EX2_1:

contains the requested functions for part 1 of exercise.

createTextFiles(): creates n files with a random number of lines, every line conteins the string:"hello world", the function returns a string array includes
the names of the files.

getNumOfLines():reads the n files using the string array includes all the files names and sum the amount of all lines in the files iteratively. 

getNumOfLinesThreads():reads the n files using the string array includes all the files names and sum the amount of all lines in the files using threads
which use "myThread" class.

getNumOfLinesThreadPool():reads the n files using the string array includes all the files names and sum the amount of all lines in the files
using threadpool which using "LineCounterCallable" class.

### efficiency:
getNumOfLinesThreads is more efficient than getNumOfLines because it can process multiple files in parallel, whereas getNumOfLines processes them one at a time.
By using multiple threads, the program can take advantage of multiple CPU cores, potentially completing the task more quickly.
Also, when the number of files is quite big the sequential implementation can take a long time while parallel implementation will
divide the work among all the threads, thus completing the task much faster.

getNumOfLinesThreadPool is more efficient than getNumOfLinesThreads because it makes use of a thread pool, which is a pre-configured and reusable set of threads. Instead of creating a new thread for every file, the thread pool will reuse the existing threads. This can be more efficient than creating new threads because creating and destroying threads can be a costly operation.

#### test output of 100 files:

<img width="214" alt="image" src="https://user-images.githubusercontent.com/117980808/211850147-3a922f78-c9d6-4b81-aa10-835841ce159b.png">

#### classes diagram of Ex2_1:

![image](https://user-images.githubusercontent.com/117980808/211852668-bf84ac63-a1f0-4f32-856f-6b20bc0a16f5.png)







## EX2_2:
contains the relevant java files for this ecercise.

#### tasktype.java:
represents the task's priority. 
contains the code we got from part 2 of exercise.


#### task.java:
represents a generic task that can be submitted to an execution by CustomExecutor.

The class extends FutureTask because the CustomExecutor can only execute Future objects.
It also implements the Comparable interface, so that tasks can be compared and sorted by priority.

The task class holds a Callable object, which represents the actual operation to be executed, and a TaskType object, which represents the task's priority.
The class provides factory methods for creating Task objects, one with default priority (TaskType.OTHER) and one with given priority, as well as methods for getting and setting the priority and getting the callable operation.

It also provides an override for toString() method, so that task can be presented as string.
The class also has compareTo (Task<T> other) method which allows the class to compare between two tasks by their priority, this is 
needed as the class implements Comparable interface.

#### CustomExecutor.java:
  
"CustomExecutor" is a custom-built executor service that uses a thread pool, extends ThreadPoolExecutor and designed to execute Task class objects according to their priority.

The CustomExecutor creates a thread pool with a number of threads equal to the number of cores on the host machine divided by 2 as a minimum size and number of cores - 1 as maximum.
It also creates a PriorityBlockingQueue which is used as a storage for waiting tasks.

It also holds an AtomicInteger variable currentMaxPriority which holds the priority of the task with the highest priority for execution and an AtomicIntegerArray queuePriorities which holds the number of tasks of each priority that are waiting in the queue.

The class provides 2 submit methods which creating Task objects, one with default priority(TaskType.OTHER) and one with a given priority.

It also provides an override for submit() method, which is used to submit task object to the executor service by adding it to the PriorityBlockingQueue and keep track of the current max priority in the queue.

  
#### classes diagram of Ex2_2:
(CustomExecutor extends ThreadPoolExecutor but, it was too big for the diagram so it is not there)
  
![Ex2_2](https://user-images.githubusercontent.com/117980808/211863482-d0e6433c-5e4c-4889-8d32-14053a037ed4.jpg)

  
