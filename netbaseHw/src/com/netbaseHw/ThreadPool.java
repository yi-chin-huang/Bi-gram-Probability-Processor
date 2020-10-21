//package com.netbaseHw;
//
//import java.util.*;
//import java.util.concurrent.LinkedBlockingQueue;
//
//public class ThreadPool implements Runnable {
//    private static LinkedBlockingQueue<Runnable> queue;
//    private static List<Thread> threads;
//    private static boolean shutdown;
//
//    public ThreadPool(int numberOfThreads) {
//        queue = new LinkedBlockingQueue<>();
//        threads = new ArrayList<>();
//
//        for (int i=0; i<numberOfThreads; i++) {
//            Thread thread = new Thread(this);
//            thread.start();
//            threads.add(thread);
//        }
//    }
//
//    public void execute(Runnable task) throws InterruptedException {
//        queue.put(task);
//    }
//
//    private Runnable consume() throws InterruptedException {
//        return queue.take();
//    }
//
//    @Override
//    public void run()  {
//        try {
//            while (!shutdown) {
//                Runnable task = this.consume();
//                task.run();
//            }
//        } catch (InterruptedException e) {
//            System.out.println(Thread.currentThread().getName() + " is interrupted.");
//            e.printStackTrace();
//        }
//    }
//
//    public static void shutdown() {
//        shutdown = true;
//        threads.forEach((thread) -> {
//            thread.interrupt();
//        });
////        System.out.println("Shut down by " + Thread.currentThread().getName());
//    }
//
//    public static int getRemainingTasks() {
//        return queue.size();
//    }
//}
//
//
