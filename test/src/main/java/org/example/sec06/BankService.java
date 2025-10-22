/*
 * Copyright © 2025 GFIT Inc. All rights reserved.
 */
package org.example.sec06;

import com.google.common.util.concurrent.Uninterruptibles;
import io.grpc.stub.StreamObserver;
import java.util.concurrent.TimeUnit;
import org.example.models.sec06.AccountBalance;
import org.example.models.sec06.BalanceCheckRequest;
import org.example.models.sec06.BankServiceGrpc;
import org.example.models.sec06.DepositRequest;
import org.example.models.sec06.Money;
import org.example.models.sec06.WithdrawRequest;
import org.example.sec06.repository.AccountRepository;
import org.example.sec06.requesthandlers.DepositRequestHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BankService extends BankServiceGrpc.BankServiceImplBase {

    private static final Logger log = LoggerFactory.getLogger(BankService.class);

    @Override
    public void getAccountBalance(BalanceCheckRequest request, StreamObserver<AccountBalance> responseObserver) {
        int accountNumber = request.getAccountNumber();
        int balance = AccountRepository.getBalance(accountNumber);
        AccountBalance accountBalance = AccountBalance
            .newBuilder()
            .setAccountNumber(accountNumber)
            .setBalance(balance)
            .build();

        responseObserver.onNext(accountBalance);
        responseObserver.onCompleted();
    }

    @Override
    public void withdraw(WithdrawRequest request, StreamObserver<Money> responseObserver) {
        int accountNumber = request.getAccountNumber();
        int balance = AccountRepository.getBalance(accountNumber);
        int requestAmount = request.getAmount();

        if (requestAmount > balance) {
            responseObserver.onCompleted();
            return;
        }

        for (int i = 0; i < (requestAmount / 10); i++) {
            Money money = Money.newBuilder().setAmount(10).build();
            responseObserver.onNext(money);
            log.info("money sent {}", money);
            AccountRepository.deductAmount(accountNumber, 10);
            Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
        }
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<DepositRequest> deposit(StreamObserver<AccountBalance> responseObserver) {
        return new DepositRequestHandler(responseObserver);
    }
}
