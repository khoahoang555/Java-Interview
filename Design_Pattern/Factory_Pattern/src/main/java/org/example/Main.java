package org.example;

public class Main {

    public static void main(String[] args) {
        Product iphone = new ElectronicProduct();
        iphone.setName("iPhone 14");
        iphone.setOriginalPrice(1000);
        iphone.setDescription("Latest iPhone model with advanced features.");
        System.out.println("The actual iphone price is " + iphone.getActualPrice());

        Product bed = new HouseholdProduct();
        bed.setName("Bed");
        bed.setOriginalPrice(50);
        bed.setDescription("Comfortable bed with a modern design.");
        System.out.println("The actual bed price is " + bed.getActualPrice());
    }
}
