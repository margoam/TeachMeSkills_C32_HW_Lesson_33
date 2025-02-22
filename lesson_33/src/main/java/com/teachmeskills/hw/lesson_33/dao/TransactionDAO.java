package com.teachmeskills.hw.lesson_33.dao;

import com.teachmeskills.hw.lesson_33.model.Transaction;

import java.sql.Connection;
import java.sql.SQLException;

public interface TransactionDAO {
    void logTransaction(Transaction transaction, Connection connection) throws SQLException;
}
