/*
 * Copyright © 2025 GFIT Inc. All rights reserved.
 */
package org.example;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class Product {
    private String name;
    private double originalPrice;
    private String description;
    protected abstract Tax getTax();

    public double getActualPrice() {
        return originalPrice + (originalPrice * getTax().getRate());
    }
}
