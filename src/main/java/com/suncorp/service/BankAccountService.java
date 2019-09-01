////////////////////////////////////////////////////////////////////////////////
//
// Copyright (c) 2019, Suncorp Metway Limited. All rights reserved.
//
// This is unpublished proprietary source code of Suncorp Metway Limited.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
////////////////////////////////////////////////////////////////////////////////

package com.suncorp.service;

import java.util.List;
import com.suncorp.entity.BankAccount;
import com.suncorp.entity.Transaction;
import com.suncorp.model.BankAccountDTO;
import com.suncorp.model.MakePayment;
import com.suncorp.model.PaymentDTO;

public interface BankAccountService {

    public BankAccount saveNewBankAccount(BankAccountDTO bankAccountDto);
    public BankAccount deposit(BankAccountDTO bankAccountDto,String tranactionType);
    public BankAccount withdraw(BankAccountDTO bankAccountDto,String tranactionType);
    public List<Transaction> viewTransaction(Long accountId);
    public MakePayment makePayment(PaymentDTO payment);
    public String updateAccountType(Long accountId,String accountType);
}
