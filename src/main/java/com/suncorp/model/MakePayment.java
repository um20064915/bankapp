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

public class MakePayment {
    private String payeeId;
    private String payerId;
    private String payerBalance;
    private String payeeBalance;

    public String getPayeeId() {
        return payeeId;
    }

    public void setPayeeId(final String payeeId) {
        this.payeeId = payeeId;
    }

    public String getPayerId() {
        return payerId;
    }

    public void setPayerId(final String payerId) {
        this.payerId = payerId;
    }

    public String getPayerBalance() {
        return payerBalance;
    }

    public void setPayerBalance(final String payerBalance) {
        this.payerBalance = payerBalance;
    }

    public String getPayeeBalance() {
        return payeeBalance;
    }

    public void setPayeeBalance(final String payeeBalance) {
        this.payeeBalance = payeeBalance;
    }
}
