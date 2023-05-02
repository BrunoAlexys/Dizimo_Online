package br.com.unifacol.dizimo.model.interfaces.service;

import java.math.BigDecimal;
import java.sql.SQLException;

public interface IBankTransition {
    void deposit(Integer accountNumber, Integer password, BigDecimal amount) throws SQLException;

    void withdraw(Integer accountNumber, Integer password, BigDecimal amount) throws SQLException;

    void transfer(Integer sourceAccountNumber, Integer destinationAccountNumber, Integer password, BigDecimal amount) throws SQLException;
}
