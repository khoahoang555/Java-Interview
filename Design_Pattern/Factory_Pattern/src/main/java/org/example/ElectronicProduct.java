/*
 * Copyright © 2025 GFIT Inc. All rights reserved.
 */
package org.example;

public class ElectronicProduct extends Product {

    @Override
    public Tax getTax() {
        return new ElectronicTax();
    }
}
