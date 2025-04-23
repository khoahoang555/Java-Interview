/*
 * Copyright © 2025 GFIT Inc. All rights reserved.
 */
package org.example;

public class HouseholdProduct extends Product {

    @Override
    public Tax getTax() {
        return new HouseholdTax();
    }
}
