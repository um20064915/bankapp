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

import java.time.LocalDate;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class BankAccountDTO {

    private Long accountId;
    private double amount;
    @Size(min=2,max=30)
    private String firstName;
    @Size(min=2,max=30)
    private String lastName;
    private String dateOfBirth;
    @DateTimeFormat(pattern="yyyy-MM-dd", iso= ISO.DATE)
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private LocalDate accountCreationDate;
    private AccountType accountType;


    public double getAmount() {
        return amount;
    }

    public void setAmount(final double amount) {
        this.amount = amount;
    }



    public Long getAccountId(){
        return accountId;
    }
    public void setAccountId(final Long accountId){
        this.accountId=accountId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(final String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getAccountCreationDate() {
        return accountCreationDate;
    }

    public void setAccountCreationDate(final LocalDate accountCreationDate) {
        this.accountCreationDate = accountCreationDate;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(final AccountType accountType) {
        this.accountType = accountType;
    }
}
