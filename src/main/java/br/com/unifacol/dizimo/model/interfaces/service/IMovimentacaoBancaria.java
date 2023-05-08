package br.com.unifacol.dizimo.model.interfaces.service;

import java.math.BigDecimal;
import java.sql.SQLException;

public interface IMovimentacaoBancaria {
    void depositar(Integer numeroDaConta, Integer senha, BigDecimal valor) throws SQLException;

    void sacar(Integer numeroDaConta, Integer senha, BigDecimal valor) throws SQLException;

    void transferir(Integer numeroDaContaOrigem, Integer numeroDaContaDestino, Integer senha, BigDecimal valor) throws SQLException;
}
