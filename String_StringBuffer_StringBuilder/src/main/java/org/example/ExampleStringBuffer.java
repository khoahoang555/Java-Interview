/*
 * Copyright © 2025 GFIT Inc. All rights reserved.
 */
package org.example;

import java.time.LocalDateTime;

public class ExampleStringBuffer {
    private static final LoggerBuffer LOGGER_BUFFER = new LoggerBuffer();

    public static void main(String[] args) {
        Thread infoLoggingThread = new Thread(new LoggerRunnable(
            Thread.currentThread().getName(),
            LocalDateTime.now(),
            "INFO",
            "This is a info log message!"
        ));

        Thread errorLoggingThread = new Thread(new LoggerRunnable(
            Thread.currentThread().getName(),
            LocalDateTime.now(),
            "ERROR",
            "This is a error log message!"
        ));

        infoLoggingThread.start();
        errorLoggingThread.start();

        try {
            infoLoggingThread.join();
            errorLoggingThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println(LOGGER_BUFFER.getLogs());
    }

    private static class LoggerBuffer {

        // Should use StringBuffer when the value is going to change and run multiple threads
        private final StringBuffer buffer = new StringBuffer();

        // 2025-04-22T16:26:37.678199100 [Thread-name] LEVEL: This is a log message
        public void log(LocalDateTime time, String currentThread, String level, String message) {
            buffer.append(time)
                    .append(" [")
                    .append(currentThread)
                    .append("] ")
                    .append(level)
                    .append(": ")
                    .append(message)
                    .append(System.lineSeparator());
        }

        public String getLogs() {
            return buffer.toString();
        }
    }

    private record LoggerRunnable(String currentThread, LocalDateTime time, String level, String message) implements
        Runnable {

        @Override
        public void run() {
            LOGGER_BUFFER.log(time, currentThread, level, message);
        }
    }
}
