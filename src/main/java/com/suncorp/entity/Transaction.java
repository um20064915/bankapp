////////////////////////////////////////////////////////////////////////////////
//
// Copyright (c) 2019, Suncorp Metway Limited. All rights reserved.
//
// This is unpublished proprietary source code of Suncorp Metway Limited.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
////////////////////////////////////////////////////////////////////////////////

package com.suncorp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import com.suncorp.model.TransactionType;

@Entity
@Table(name="TRANSACTION")
public class Transaction {


    @Id
    @Column(name="TRANSACTIONID")
    private Long transactionId;
    @Column(name="ACCOUNTID")
    private Long accountId;
    @Column(name="TRANSACTIONTYPE")
    private TransactionType transactionType;
    @Column(name="DEPOSITAMOUNT")
    private double depositedAmount;
    @Column(name="WITHDRAWLAMOUNT")
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
