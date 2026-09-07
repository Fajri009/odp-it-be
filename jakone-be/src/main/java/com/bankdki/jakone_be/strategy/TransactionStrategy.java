package com.bankdki.jakone_be.strategy;

import java.math.BigDecimal;

import com.bankdki.jakone_be.entity.Account;

public interface TransactionStrategy {
    void execute(Account account, BigDecimal amount);
}