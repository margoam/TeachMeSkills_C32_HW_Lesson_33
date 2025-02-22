package com.teachmeskills.hw.lesson_33.service;

import com.teachmeskills.hw.lesson_33.dao.AccountDAO;
import com.teachmeskills.hw.lesson_33.exception.NotValidAccountNumberException;
import com.teachmeskills.hw.lesson_33.model.Account;
import com.teachmeskills.hw.lesson_33.utils.DbConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class AccountService {
    private final AccountDAO accountDAO;

    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public void createAccount(Account account) throws SQLException {
        accountDAO.createAccount(account);
    }

    public double getBalance(String accountNumber) throws SQLException, NotValidAccountNumberException {

        try (Connection connection = DbConnection.getConnection()) {
            Optional<Account> account = accountDAO.getAccount(accountNumber, connection);
            if (account.isPresent()) {
                return account.get().getBalance();
            } else {
                throw new NotValidAccountNumberException("Account not found.");
            }
        }catch (SQLException e) {
            throw new SQLException("Error during getting account number.", e);
        }

    }
}
