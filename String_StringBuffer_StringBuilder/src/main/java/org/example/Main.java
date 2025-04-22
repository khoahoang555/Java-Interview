package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        int threadCount = 100;
        int numberThreads = 3;
//        exampleStringBuilderInThreads(threadCount, numberThreads);
        executeStringBufferInThreads(threadCount, numberThreads);
    }

    private static void executeStringBuilderInThreads(int threadCount, int numberThreads) {
        // StringBuilder is not thread-safe
        StringBuilder sb = new StringBuilder();

        executeThreads(buildRunnable(threadCount, () -> sb.append("a")), numberThreads);
        printResult(threadCount, numberThreads, sb.toString());
    }

    private static void executeStringBufferInThreads(int threadCount, int numberThreads) {
        // StringBuffer is thread-safe
        StringBuffer sb = new StringBuffer();

        executeThreads(buildRunnable(threadCount, () -> sb.append("a")), numberThreads);
        printResult(threadCount, numberThreads, sb.toString());
    }

    private static <T> Runnable buildRunnable(int threadCount, Supplier<T> supplier) {
        return () -> {
            for (int i = 0; i < threadCount; i++) {
                try {
                    Thread.sleep(1);
                    supplier.get();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }

    private static void executeThreads(Runnable runnable, int numberThreads) {
        List<Thread> threads = new ArrayList<>();
        for (int index = 0; index < numberThreads; index++) {
            Thread thread = new Thread(runnable);
            threads.add(thread);
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void printResult(int threadCount, int numberThreads, String result) {
        int expectedLength = threadCount * numberThreads;
        System.out.println("Expected length output: " + expectedLength);
        System.out.println("Actual length output: " + result.length());
    }
}
