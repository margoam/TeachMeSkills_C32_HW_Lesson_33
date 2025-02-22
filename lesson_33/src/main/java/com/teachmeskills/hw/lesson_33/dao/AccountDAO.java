package com.teachmeskills.hw.lesson_33.dao;

import com.teachmeskills.hw.lesson_33.model.Account;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public interface AccountDAO {
    void createAccount(Account account) throws SQLException;
    double getBalanceByAccountNumber(String accountNumber, Connection connection) throws SQLException;
    void updateBalance(String accountNumber, double newBalance, Connection connection) throws SQLException;
    Optional<Account> getAccount(String accountNumber, Connection connection);
}
