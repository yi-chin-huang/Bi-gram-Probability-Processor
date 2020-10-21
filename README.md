## The Spam Filter

The text file **answer for problem 1.txt** contains the bi-gram probability and the value of k for problem 1.
The directory **netbaseHw** is a Java project root with the source code for the two problems.

To examine problem 1 (The Spam Filter), you can run `SpamFilter.main("src/com/netbaseHw/sampleDirectory/SampleData.txt");` in **Main.java**

To examine problem 2 (The Spam Filter v2), you can run `SpamFilterV2.main("src/com/netbaseHw/sampleDirectory");` in **Main.java**

If you want to test problem 2 with some files, you can put the test files end with **.txt** under the directory "**src/com/netbaseHw/sampleDirectory**", or put in another directory and call `SpamFilterV2.calculateBiGramProb(DIRECTORY_OF_TEST_FILES);`

---

Updates for the issues:
##### (1)
> By running SpamFilter directly, the program outputs 1.06558, which is different from answer in “answer for problem 1.txt”

I think it is due to the difference between the behavior of "==" and equals() when comparing two strings. In the usage here, equals() behaves what I intended, since it compares the value of two strings instead of the reference equality. However, in Java 15 (The version I installed), 
```
String s = " ".replaceAll("[^a-zA-Z0-9]", "");
System.out.println(s == "");
```
the output of the code above is true.
It is at odd with the result on online compiler(with previous version of Java). This might explain the differnece between the answer in the text file I provided and the output when you executed the program.
In the program, I replace `if (cleanWord != "")` with `if (!cleanWord.equals(""))` so that the behavior can be what I expected.

##### (2)
> This requirement is not implemented – “The program can handle interrupt in the middle of processing and shut down gracefully.”

I add a shut down hook in SpamFilterV2 to handle interruptions. In the shut down hook, the thread pool first stops accepting new tasks, and then wait a while for existing tasks to terminate. After that, it cancels currently executing tasks, and prints out messages to inform users that the executor is shut down and the number of tasks never started execution.
In this version of code, I use ExecutorService instead of self-implemented ThreadPool, since ExecutorService provided some useful functions for shutting down gracefully.
