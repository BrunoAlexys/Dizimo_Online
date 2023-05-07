package br.com.unifacol.dizimo.model.interfaces.service;


import br.com.unifacol.dizimo.model.entities.ChurchAccount;
import java.sql.SQLException;


public interface IChurchAccountService {
    void create(ChurchAccount churchAccount) throws SQLException;
    void update(Integer numberAccountActual,Integer passwordActual, ChurchAccount churchAccount) throws SQLException;
    void delete(Integer accountNumber,Integer password)throws SQLException;
    void listChurchAccounts() throws SQLException;
    void listChurchAccountNumberAndPassword(Integer accountNumber, Integer password) throws SQLException;
}
