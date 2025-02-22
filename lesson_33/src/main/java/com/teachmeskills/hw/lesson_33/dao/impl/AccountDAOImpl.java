package com.teachmeskills.hw.lesson_33.dao.impl;

import com.teachmeskills.hw.lesson_33.dao.AccountDAO;
import com.teachmeskills.hw.lesson_33.model.Account;
import com.teachmeskills.hw.lesson_33.utils.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class AccountDAOImpl implements AccountDAO {

    @Override
    public void createAccount(Account account) throws SQLException {
        String sql = "INSERT INTO accounts (username, account_number, balance) VALUES (?, ?, ?)";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getAccountNumber());
            preparedStatement.setDouble(3, account.getBalance());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public double getBalanceByAccountNumber(String accountNumber, Connection connection) throws SQLException {
        String sql = "SELECT balance FROM accounts WHERE account_number = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, accountNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getDouble("balance");
            }
            throw new SQLException("Account with this account number " + accountNumber + " is not found.");
        }
    }

    @Override
    public void updateBalance(String accountNumber, double newBalance, Connection connection) throws SQLException {
        String sql = "UPDATE accounts SET balance = ? WHERE account_number = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setDouble(1, newBalance);
            preparedStatement.setString(2, accountNumber);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public Optional<Account> getAccount(String accountNumber, Connection connection) {
        String sql = "SELECT * FROM accounts WHERE account_number = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, accountNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                double accountBalance = resultSet.getDouble("balance");
                return Optional.of(new Account(id, username, accountNumber, accountBalance));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }
}
