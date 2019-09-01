////////////////////////////////////////////////////////////////////////////////
//
// Copyright (c) 2019, Suncorp Metway Limited. All rights reserved.
//
// This is unpublished proprietary source code of Suncorp Metway Limited.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
////////////////////////////////////////////////////////////////////////////////

package com.suncorp.controller;

import java.util.List;
import javax.validation.Valid;
import com.suncorp.entity.BankAccount;
import com.suncorp.entity.Transaction;
import com.suncorp.exception.CustomExceptionHandler;
import com.suncorp.model.BankAccountDTO;
import com.suncorp.model.MakePayment;
import com.suncorp.model.PaymentDTO;
import com.suncorp.service.BankAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
public class BankAccountController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BankAccountController.class);

    @Autowired
    BankAccountService bankAccountService;

    @PostMapping(value = "/newaccount", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String openAccount(@Valid @RequestBody BankAccountDTO bAccount, BindingResult bindingResult) {
        LOGGER.debug("Inside open Account method ");
        StringBuilder jsonString = new StringBuilder();
        if (bindingResult.hasErrors()) {
            jsonString.append("{ \"success\": \"false\"");
            for (ObjectError error : bindingResult.getAllErrors())
                jsonString.append(",\"").append(((FieldError) error).getField()).append("\":\"").append(error.getDefaultMessage()).append("\"");
        } else {
           BankAccount bankAccount = bankAccountService.saveNewBankAccount(bAccount);
           if(bankAccount.getAccountId()!=null) {
               String accountId = String.valueOf(bankAccount.getAccountId()).toString();
               jsonString.append("{\"success\":\"true\",");
               jsonString.append("\"AccountId\": ").append(accountId);
           }
           else{
               jsonString.append("{ \"success\": \"false\"");
           }
        }
        jsonString.append("}");
        return jsonString.toString();
    }

    @PutMapping(value = "deposit", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String depositAmount(
            @Valid @RequestBody BankAccountDTO bAccount, @RequestParam String transactionType, BindingResult bindingResult) {
        LOGGER.debug("Inside  depositAmount method ");
        StringBuilder jsonString = new StringBuilder();
        if (bindingResult.hasErrors()) {
            jsonString.append("{ \"success\": \"false\"");
            for (ObjectError error : bindingResult.getAllErrors())
                jsonString.append(",\"").append(((FieldError) error).getField()).append("\":\"").append(error.getDefaultMessage()).append("\"");
        } else {
            BankAccount bankAccount = bankAccountService.deposit(bAccount,transactionType);
            Double currentBalance = bankAccount.getAmount();
            String depositBalance = String.valueOf(currentBalance).toString();
            jsonString.append("{\"success\":\"true\",");
            jsonString.append("\"balance\": ").append(depositBalance);
        }
        jsonString.append("}");
        return jsonString.toString();
    }

    @PutMapping(value = "withdraw", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String withdrawAmount(
            @Valid @RequestBody BankAccountDTO bAccount, @RequestParam String transactionType, BindingResult bindingResult,
            RedirectAttributes rAttributes) {
        LOGGER.debug("Inside  withdraw method ");
        StringBuilder jsonString = new StringBuilder();
        if (bindingResult.hasErrors()) {
            jsonString.append("{ \"success\": \"false\"");
            for (ObjectError error : bindingResult.getAllErrors())
                jsonString.append(",\"").append(((FieldError) error).getField()).append("\":\"").append(error.getDefaultMessage()).append("\"");
        }
        else{
            BankAccount bankAccount = bankAccountService.withdraw(bAccount,transactionType);
            Double currentBalance = bankAccount.getAmount();
            String leftBalance = String.valueOf(currentBalance).toString();
            jsonString.append("{\"success\":\"true\",");
            jsonString.append("\"balance\": ").append(leftBalance);
        }
        jsonString.append("}");
        return jsonString.toString();
    }

    @ExceptionHandler(CustomExceptionHandler.class)
    @PutMapping(value="/makepayment",consumes=MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public String makePayment(@RequestBody PaymentDTO paymentObject){
        StringBuilder jsonString = new StringBuilder();
        MakePayment makePayment = bankAccountService.makePayment(paymentObject);
        jsonString.append("{\"success\":\"true\",");
        jsonString.append("\"payeeId\": ").append(makePayment.getPayeeId());
        jsonString.append(",\"payeeBalance\": ").append(makePayment.getPayeeBalance());
        jsonString.append(",\"payerId\": ").append(makePayment.getPayerId());
        jsonString.append(",\"payerBalance\": ").append(makePayment.getPayerBalance());
        jsonString.append("}");
        return jsonString.toString();

    }

    @GetMapping(value="/viewtransaction",produces = MediaType.APPLICATION_JSON_VALUE)
    List<Transaction> viewTransaction(@RequestParam Long accountId){
        List<Transaction> transactionList = bankAccountService.viewTransaction(accountId);
        return  transactionList;
    }


    @PutMapping(value="/accounttype",produces= MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
    String updateAccountType(@RequestParam Long accountId,@RequestParam String accountType){

        StringBuilder jsonString = new StringBuilder();
        String bankAccount = bankAccountService.updateAccountType(accountId,accountType);
        String[] bnkAcct = bankAccount.split("_");
        jsonString.append("{\"success\":\"true\",");
        jsonString.append("\"Previous Account Type\": ").append("\"").append(bnkAcct[0]).append("\"");
        jsonString.append(",\"Current Account Type\": ").append("\"").append(bnkAcct[1]).append("\"");
        jsonString.append("}");
        return jsonString.toString();
    }

    @GetMapping(value="/hello")
   public  String m(){
        return "hi this is BankApplication";
    }

}
