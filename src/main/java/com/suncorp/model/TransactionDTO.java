////////////////////////////////////////////////////////////////////////////////
//
// Copyright (c) 2019, Suncorp Metway Limited. All rights reserved.
//
// This is unpublished proprietary source code of Suncorp Metway Limited.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
////////////////////////////////////////////////////////////////////////////////

package com.suncorp.model;

public class TransactionDTO {
    private Long transactionId;
    private Long accountId;
    private TransactionType transactionType;
    private double depositedAmount;
    private double withdrawlAmount;

    public double getDepositedAmount() {
        return depositedAmount;
    }

    public void setDepositedAmount(final double depositedAmount) {
        this.depositedAmount = depositedAmount;
    }

    public double getWithdrawlAmount() {
        return withdrawlAmount;
    }

    public void setWithdrawlAmount(final double withdrawlAmount) {
        this.withdrawlAmount = withdrawlAmount;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(final Long transactionId) {
        this.transactionId = transactionId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(final Long accountId) {
        this.accountId = accountId;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(final TransactionType transactionType) {
        this.transactionType = transactionType;
    }

}
