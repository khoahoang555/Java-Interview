package org.example;

import org.example.common.GrpcServer;
import org.example.sec06.BankService;

public class Main {

    public static void main(String[] args) {
        GrpcServer.create(new BankService())
            .start()
            .await();
    }
}
