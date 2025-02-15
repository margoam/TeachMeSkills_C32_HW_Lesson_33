package com.teachmeskills.hw.lesson_33;

import com.teachmeskills.hw.lesson_33.dao.AccountDAO;
import com.teachmeskills.hw.lesson_33.dao.TransactionDAO;
import com.teachmeskills.hw.lesson_33.exception.NotValidAccountNumberException;
import com.teachmeskills.hw.lesson_33.exception.NotValidSumTransactionException;
import com.teachmeskills.hw.lesson_33.model.Account;
import com.teachmeskills.hw.lesson_33.service.AccountService;
import com.teachmeskills.hw.lesson_33.service.TransactionService;
import com.teachmeskills.hw.lesson_33.utils.DbConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class AppRunner {

    public static void main(String[] args) {

        try (Connection connection = DbConnection.getConnection()) {
            AccountDAO accountDAO = new AccountDAO();
            TransactionDAO transactionDAO = new TransactionDAO(connection);
            AccountService accountService = new AccountService(accountDAO);
            TransactionService transactionService = new TransactionService(accountDAO, transactionDAO);

            accountService.createAccount(new Account(0, "user1", "ACC123", 500.00));
            accountService.createAccount(new Account(0, "user2", "ACC456", 300.00));

            System.out.println("Balance user1: " + accountService.getBalance("ACC123"));
            System.out.println("Balance user2: " + accountService.getBalance("ACC456"));

            transactionService.transferFunds("ACC123", "ACC456", 200.00, "user1");

            System.out.println("Balance after transaction user1: " + accountService.getBalance("ACC123"));
            System.out.println("Balance after transaction user2: " + accountService.getBalance("ACC456"));

        } catch (SQLException | NotValidAccountNumberException | NotValidSumTransactionException e) {
            System.err.println("Db connection is failed: " + e.getMessage());
        }
    }
}
