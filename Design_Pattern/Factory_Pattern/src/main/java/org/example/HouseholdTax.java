/*
 * Copyright © 2025 GFIT Inc. All rights reserved.
 */
package org.example;

public class HouseholdTax implements Tax {

    private static final double RATE = 10;

    @Override
    public double getRate() {
        return RATE / 100;
    }
}
