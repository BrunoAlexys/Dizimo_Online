package br.com.unifacol.dizimo.model.interfaces.repository;

import br.com.unifacol.dizimo.model.entities.Church;
import br.com.unifacol.dizimo.model.entities.ChurchAccount;

import java.sql.SQLException;
import java.util.List;

public interface IChurchAccountRepository {
    void create(ChurchAccount churchAccount) throws SQLException;
    void update(Integer numberAccountActual,Integer passwordActual, ChurchAccount churchAccount) throws SQLException;
    void delete(Integer numberAccount, Integer password)throws SQLException;
    List<ChurchAccount> listChurchsAccount() throws SQLException;
    List<ChurchAccount> listChurchAccountsCpfAndPassword(Integer numberAccount,Integer password) throws SQLException;
}
