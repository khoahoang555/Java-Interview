/*
 * Copyright © 2025 GFIT Inc. All rights reserved.
 */
package org.example.sec06.requesthandlers;

import io.grpc.stub.StreamObserver;
import org.example.models.sec06.AccountBalance;
import org.example.models.sec06.TransferRequest;
import org.example.models.sec06.TransferResponse;
import org.example.models.sec06.TransferStatus;
import org.example.sec06.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransferRequestHandler implements StreamObserver<TransferRequest> {

    private final static Logger log = LoggerFactory.getLogger(TransferRequestHandler.class);

    private final StreamObserver<TransferResponse> responseObserver;

    public TransferRequestHandler(StreamObserver<TransferResponse> responseObserver) {
        this.responseObserver = responseObserver;
    }

    @Override
    public void onNext(TransferRequest transferRequest) {
        TransferStatus status = this.transfer(transferRequest);

        TransferResponse response = TransferResponse.newBuilder()
            .setFromAccount(this.toAccountBalance(transferRequest.getFromAccount()))
            .setToAccount(this.toAccountBalance(transferRequest.getToAccount()))
            .setStatus(status)
            .build();

        responseObserver.onNext(response);
    }

    @Override
    public void onError(Throwable throwable) {
        log.info("client error {}", throwable.getMessage());
    }

    @Override
    public void onCompleted() {
        log.info("transfer request stream completed");
        this.responseObserver.onCompleted();
    }

    private TransferStatus transfer(TransferRequest request) {
        int amount = request.getAmount();
        int fromAccount = request.getFromAccount();
        int toAccount = request.getToAccount();

        TransferStatus status = TransferStatus.REJECTED;

        if (AccountRepository.getBalance(fromAccount) >= amount && (fromAccount != toAccount)) {
            AccountRepository.deductAmount(fromAccount, amount);
            AccountRepository.addAmount(toAccount, amount);

            status = TransferStatus.COMPLETED;
        }

        return status;
    }

    private AccountBalance toAccountBalance(int accountNumber) {
        return AccountBalance.newBuilder()
            .setAccountNumber(accountNumber)
            .setBalance(AccountRepository.getBalance(accountNumber))
            .build();
    }
}
