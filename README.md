The text file **answer for problem 1.txt** contains the bi-gram probability and the value of k for problem 1.
The directory **netbaseHw** is a Java project root with the source code for the two problems.

To examine problem 1 (The Spam Filter), you can run `SpamFilter.main();` in **Main.java**
To examine problem 2 (The Spam Filter v2), you can run `SpamFilterV2.main();` in **Main.java**
If you want to use some files to test problem 2, you should put the test files end with **.txt** under the directory "**src/com/netbaseHw/sampleDirectory**"

-

Updates for the issues:
For issue 1 (By running SpamFilter directly, the program outputs 1.06558, which is different from answer in “answer for problem 1.txt”), I think it is due to the difference between the usage of "==" and equals() when comparing strings. , the result of equals() would be logically 
