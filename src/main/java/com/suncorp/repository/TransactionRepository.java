////////////////////////////////////////////////////////////////////////////////
//
// Copyright (c) 2019, Suncorp Metway Limited. All rights reserved.
//
// This is unpublished proprietary source code of Suncorp Metway Limited.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
////////////////////////////////////////////////////////////////////////////////

package com.suncorp.repository;

import java.util.List;
import com.suncorp.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {

    @Query("SELECT u FROM Transaction u WHERE u.accountId = ?1")
    List<Transaction> findByAllAccountId(Long accountId);
}
