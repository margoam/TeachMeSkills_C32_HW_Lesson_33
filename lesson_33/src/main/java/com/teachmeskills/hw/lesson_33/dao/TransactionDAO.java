package com.teachmeskills.hw.lesson_33.dao;

import com.teachmeskills.hw.lesson_33.model.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionDAO {

    public TransactionDAO(Connection connection) {
    }

    public void logTransaction(Transaction transaction, Connection connection) throws SQLException {
        String sql = "INSERT INTO transactions (from_account, to_account, amount, timestamp, username) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, transaction.getFromAccount());
            preparedStatement.setString(2, transaction.getToAccount());
            preparedStatement.setDouble(3, transaction.getAmount());
            preparedStatement.setDate(4, java.sql.Date.valueOf(transaction.getTransactionDate()));
            preparedStatement.setString(5, transaction.getUsername());
            preparedStatement.executeUpdate();
        }
    }
}
