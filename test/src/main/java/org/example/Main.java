package org.example;

import java.util.stream.IntStream;
import org.example.common.GrpcServer;
import org.example.sec06.BankService;
import org.example.sec06.TransferService;

public class Main {

    public static void main(String[] args) {
//        GrpcServer.create(new BankService(), new TransferService())
//            .start()
//            .await();
        IntStream.rangeClosed(1, 100)
            .limit(5)
            .forEach(i -> {
                System.out.println("emitting " + i);
            });
    }
}
