package br.com.unifacol.dizimo.model.interfaces.service;

import br.com.unifacol.dizimo.model.entities.Church;
import br.com.unifacol.dizimo.model.entities.ChurchAccount;

import java.sql.SQLException;
import java.util.List;

public interface IChurchAccountService {
    void create(ChurchAccount churchAccount) throws SQLException;
    void update(ChurchAccount churchAccount) throws SQLException;
    void delete(Integer churchAccount,Integer password)throws SQLException;
    List<ChurchAccount> listChurchAccounts() throws SQLException;
    List<ChurchAccount> listChurchAccountCnpjAndPassword(Long id) throws SQLException;
}
