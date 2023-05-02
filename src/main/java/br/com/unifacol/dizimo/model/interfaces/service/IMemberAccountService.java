package br.com.unifacol.dizimo.model.interfaces.service;

import br.com.unifacol.dizimo.model.entities.AccountMember;
import java.sql.SQLException;

public interface IMemberAccountService {
    void create(AccountMember accountMember) throws SQLException;
    void update(Integer numberAccountActual,Integer passwordActual, AccountMember accountMember) throws SQLException;
    void delete(Integer numberAccount, Integer password)throws SQLException;
    void listMemberAccounts() throws SQLException;
    void listMemberAccountsCpfAndPassword(Integer numberAccount,Integer password) throws SQLException;
}
