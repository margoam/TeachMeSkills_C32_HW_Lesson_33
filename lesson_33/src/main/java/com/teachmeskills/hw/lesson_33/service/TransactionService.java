package com.teachmeskills.hw.lesson_33.service;

import com.teachmeskills.hw.lesson_33.dao.AccountDAO;
import com.teachmeskills.hw.lesson_33.dao.TransactionDAO;
import com.teachmeskills.hw.lesson_33.exception.NotValidAccountNumberException;
import com.teachmeskills.hw.lesson_33.exception.NotValidSumTransactionException;
import com.teachmeskills.hw.lesson_33.model.Transaction;
import com.teachmeskills.hw.lesson_33.utils.DbConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public class TransactionService {

    private final AccountDAO accountDAO;
    private final TransactionDAO transactionDAO;

    public TransactionService(AccountDAO accountDAO, TransactionDAO transactionDAO) {
        this.accountDAO = accountDAO;
        this.transactionDAO = transactionDAO;
    }

    public void transferFunds(String fromAccount, String toAccount, double amount, String username)
            throws NotValidAccountNumberException, NotValidSumTransactionException, SQLException {

        if (amount <= 0) {
            throw new NotValidSumTransactionException("Transfer amount must be greater than zero");
        }

        try (Connection connection = DbConnection.getConnection()) {
            connection.setAutoCommit(false);

            double senderBalance = accountDAO.getBalanceByAccountNumber(fromAccount, connection);
            if (senderBalance < amount) {
                throw new NotValidSumTransactionException("Not enough balance!");
            }

            accountDAO.updateBalance(fromAccount, senderBalance - amount, connection);
            double receiverBalance = accountDAO.getBalanceByAccountNumber(toAccount, connection);
            accountDAO.updateBalance(toAccount, receiverBalance + amount, connection);

            Transaction transaction = new Transaction(0, fromAccount, toAccount, amount, LocalDate.now(), username);
            transactionDAO.logTransaction(transaction, connection);

            connection.commit();
            System.out.println("Transaction is succeeded!");
        } catch (SQLException e) {
            throw new SQLException("Error during transaction.", e);
        }
    }
}
