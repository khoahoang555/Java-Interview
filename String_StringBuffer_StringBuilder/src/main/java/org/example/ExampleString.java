/*
 * Copyright © 2025 GFIT Inc. All rights reserved.
 */
package org.example;

public class ExampleString {
    // Should be use String when the value is not going to change
    private static final String URL = "https://example.com/api/data";

    public static void main(String[] args) {
        System.out.println(URL);

        // String is immutable, so it cannot be changed. Should use StringBuilder or StringBuffer to change the value!
        String hello = "Hello";
        hello.concat("World");
        System.out.println(hello); // Output: Hello
    }
}
