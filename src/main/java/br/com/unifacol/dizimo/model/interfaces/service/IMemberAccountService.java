package br.com.unifacol.dizimo.model.interfaces.service;

import br.com.unifacol.dizimo.model.entities.MemberAccount;
import java.sql.SQLException;
import java.util.List;

public interface IMemberAccountService {
    void create(MemberAccount memberAccount) throws SQLException;
    void update(MemberAccount memberAccount) throws SQLException;
    void delete(Integer numberAccount, Integer password)throws SQLException;
    List<MemberAccount> listMemberAccounts() throws SQLException;
    List<MemberAccount> listMemberAccountsCpfAndPassword(Integer numberAccount,Integer password) throws SQLException;
}
