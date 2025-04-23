/*
 * Copyright © 2025 GFIT Inc. All rights reserved.
 */
package org.example;

public class ElectronicTax implements Tax {

    private static final double RATE = 20;

    @Override
    public double getRate() {
        return RATE / 100;
    }
}
