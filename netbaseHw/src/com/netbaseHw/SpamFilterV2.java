package com.netbaseHw;

import java.io.File;
import java.io.FilenameFilter;
import java.util.*;
import java.util.concurrent.*;

public class SpamFilterV2 {
    static Map<String, Integer> results = new Hashtable<String, Integer>();

    synchronized public static void modifyResults(String AddedResult) {
        if (results.containsKey(AddedResult)) {
            results.put(AddedResult, results.get(AddedResult) + 1);
        } else {
            results.put(AddedResult, 1);
        }
        System.out.println(AddedResult + ", " + results.get(AddedResult));
    }

    public static void main(String dirPath) {
        // Load files in the given directory
        File dir = new File(dirPath);
        File[] matchingFiles = dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith("txt");
            }
        });

        // Execute files in the thread pool
        ExecutorService executor = Executors.newFixedThreadPool(12);
        for (File f : matchingFiles) {
            BiGramProcessingRunnable task = new BiGramProcessingRunnable(f);
            executor.execute(task);
        }

        // Add shut down hook to handle abrupt termination
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
            executor.shutdown();
            try {
                if (!executor.awaitTermination(500, TimeUnit.MILLISECONDS)) {
                    List<Runnable> droppedTasks = executor.shutdownNow();
                    System.out.println("Executor was abruptly shut down. " + droppedTasks.size() + " tasks never commenced execution.");
                }
            } catch (InterruptedException e) {
                executor.shutdownNow();
            }
            }
        });

        // Shut down thread pool when all tasks are done
        executor.shutdown();
    }
}
