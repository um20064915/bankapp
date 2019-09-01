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

public class PaymentDTO {

    private Long payerAccountId;
    private Long payeeAccountId;
    private double payerAmount;

    public Long getPayerAccountId() {
        return payerAccountId;
    }

    public void setPayerAccountId(final Long payerAccountId) {
        this.payerAccountId = payerAccountId;
    }

    public Long getPayeeAccountId() {
        return payeeAccountId;
    }

    public void setPayeeAccountId(final Long payeeAccountId) {
        this.payeeAccountId = payeeAccountId;
    }

    public double getPayerAmount() {
        return payerAmount;
    }

    public void setPayerAmount(final double payerAmount) {
        this.payerAmount = payerAmount;
    }
}
