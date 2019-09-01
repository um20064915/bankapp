////////////////////////////////////////////////////////////////////////////////
//
// Copyright (c) 2019, Suncorp Metway Limited. All rights reserved.
//
// This is unpublished proprietary source code of Suncorp Metway Limited.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
////////////////////////////////////////////////////////////////////////////////

package com.suncorp.service.serviceimpl;

import java.util.List;
import java.util.Optional;
import com.suncorp.entity.BankAccount;
import com.suncorp.entity.Transaction;
import com.suncorp.model.AccountType;
import com.suncorp.model.BankAccountDTO;
import com.suncorp.model.MakePayment;
import com.suncorp.model.PaymentDTO;
import com.suncorp.model.TransactionType;
import com.suncorp.repository.BankAccountRepository;
import com.suncorp.repository.TransactionRepository;
import com.suncorp.service.BankAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BankAccountServiceImpl.class);
    public static long count = 1;
    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    public BankAccount saveNewBankAccount(BankAccountDTO bankAccountDto) {
        LOGGER.debug("Entering into saveNewBankAccount Method");
        BankAccount bankAccount = new BankAccount();
        if(bankAccountDto.getAccountId()!=null) {
            bankAccount.setAccountId(bankAccountDto.getAccountId());
            bankAccount.setAccountCreationDate(bankAccountDto.getAccountCreationDate());
            bankAccount.setAccountType(bankAccountDto.getAccountType());
            bankAccount.setAmount(bankAccountDto.getAmount());
            bankAccount.setDateOfBirth(bankAccountDto.getDateOfBirth());
            bankAccount.setFirstName(bankAccountDto.getFirstName());
            bankAccount.setLastName(bankAccountDto.getLastName());
            bankAccount = bankAccountRepository.save(bankAccount);
        }
        return bankAccount;
    }

    public BankAccount deposit(BankAccountDTO bAccount,String transactionType){
        LOGGER.debug("Entering into deposit Method");
        Long accountId=bAccount.getAccountId();
        Transaction transaction = new Transaction();
        transaction.setAccountId(bAccount.getAccountId());
        transaction.setTransactionId(count);
        transaction.setDepositedAmount(bAccount.getAmount());
        //transaction.setWithdrawlAmount(0);
        transaction.setTransactionType(TransactionType.valueOf(transactionType.toUpperCase()));
        Optional<BankAccount> bankAccountOptional = bankAccountRepository.findById(accountId);
        BankAccount bankAccount = bankAccountOptional.get();
        double previousBalance=  bankAccount.getAmount();
        double currentbalance = previousBalance+bAccount.getAmount();
        bankAccount.setAmount(currentbalance);
        bankAccount = bankAccountRepository.save(bankAccount);
        transactionRepository.save(transaction);
        count=count+1;
        return bankAccount;
    }

    public BankAccount withdraw(BankAccountDTO bAccount,String tranactionType){
        LOGGER.debug("Entering into withdraw Method");
        Transaction transaction = new Transaction();
        transaction.setAccountId(bAccount.getAccountId());
        transaction.setTransactionId(count);
        //transaction.setDepositedAmount(bAccount.getAmount());
        transaction.setWithdrawlAmount(bAccount.getAmount());
        transaction.setTransactionType(TransactionType.valueOf(tranactionType.toUpperCase()));

        Long accountId=bAccount.getAccountId();
        Optional<BankAccount> bankAccountOptional = bankAccountRepository.findById(accountId);
        BankAccount bankAccount = bankAccountOptional.get();
        double previousBalance=  bankAccount.getAmount();
        double currentbalance = previousBalance-bAccount.getAmount();
        bankAccount.setAmount(currentbalance);
        bankAccount = bankAccountRepository.save(bankAccount);
        transactionRepository.save(transaction);
        count=count+1;
        return bankAccount;
    }
    public List<Transaction> viewTransaction(Long accountId){
        LOGGER.debug("Entering into viewTransaction Method");
        List<Transaction> transactionList = transactionRepository.findByAllAccountId(accountId);
        return transactionList;
    }

    public MakePayment makePayment(PaymentDTO paymentDTO){
        LOGGER.debug("Entering into makePayment Method");
        Long accountNumberReceive = paymentDTO.getPayeeAccountId();
        Long accountNumberSender = paymentDTO.getPayerAccountId();
        double paymentAmount = paymentDTO.getPayerAmount();

        Optional<BankAccount> bankAccountSender = bankAccountRepository.findById(accountNumberSender);
        BankAccount bankAccountSen = bankAccountSender.get();
        double senderBalance = bankAccountSen.getAmount();
        if(senderBalance>paymentAmount){
            senderBalance = senderBalance-paymentAmount;
        }
        else{
            ///exception case
        }
        bankAccountSen.setAmount(senderBalance);
        bankAccountRepository.save(bankAccountSen);
        Transaction transactions = new Transaction();
        transactions.setAccountId(bankAccountSen.getAccountId());
        transactions.setTransactionId(count);
        //transaction.setDepositedAmount(bAccount.getAmount());
        transactions.setWithdrawlAmount(paymentAmount);
        transactions.setTransactionType(TransactionType.WITHDRAWL);
        transactionRepository.save(transactions);
        count = count+1;
        Optional<BankAccount> bankAccountReceiver = bankAccountRepository.findById(accountNumberReceive);
        BankAccount bankAccountRec = bankAccountReceiver.get();
        Double receiverAmount = bankAccountRec.getAmount();
        receiverAmount = receiverAmount+paymentAmount;
        bankAccountRec.setAmount(receiverAmount);
        bankAccountRepository.save(bankAccountRec);
        Transaction transactionr = new Transaction();
        transactionr.setAccountId(bankAccountRec.getAccountId());
        transactionr.setTransactionId(count);
        transactionr.setDepositedAmount(paymentAmount);
        //transactions.setWithdrawlAmount(paymentAmount);
        transactionr.setTransactionType(TransactionType.DEPOSIT);
        transactionRepository.save(transactionr);
        count = count+1;
        Optional<BankAccount> bankAccountR = bankAccountRepository.findById(accountNumberReceive);
        String receiverId = String.valueOf(bankAccountR.get().getAccountId());
        String rAmount  = String.valueOf(bankAccountR.get().getAmount());
        Optional<BankAccount> bankAccountS = bankAccountRepository.findById(accountNumberSender);
        String senderId = String.valueOf(bankAccountS.get().getAccountId());
        String sAmount  = String.valueOf(bankAccountS.get().getAmount());
        MakePayment makePayment = new MakePayment();
        makePayment.setPayeeBalance(rAmount);
        makePayment.setPayeeId(receiverId);
        makePayment.setPayerBalance(sAmount);
        makePayment.setPayerId(senderId);
        return makePayment;
    }

    public String updateAccountType(Long accountId,String accountType){

       Optional<BankAccount> bankAccount= bankAccountRepository.findById(accountId);
       BankAccount bankAcct = bankAccount.get();
       String previousAccountType = bankAcct.getAccountType().toString();
       bankAcct.setAccountType(AccountType.valueOf(accountType.toUpperCase()));
       bankAccountRepository.save(bankAcct);
        Optional<BankAccount> currAccount = bankAccountRepository.findById(accountId);
        BankAccount bAccount = currAccount.get();
        String currentAcctType = bAccount.getAccountType().toString();
        String acctTypes = previousAccountType+"_"+currentAcctType;
        return acctTypes;
    }


}

