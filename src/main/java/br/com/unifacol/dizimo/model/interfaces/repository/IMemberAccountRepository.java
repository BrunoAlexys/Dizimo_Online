package br.com.unifacol.dizimo.model.interfaces.repository;

import br.com.unifacol.dizimo.model.entities.AccountMember;

import java.sql.SQLException;
import java.util.List;

public interface IMemberAccountRepository {
    void create(AccountMember accountMember) throws SQLException;
    void update(Integer numberAccountActual,Integer passwordActual, AccountMember accountMember) throws SQLException;
    void delete(Integer numberAccount, Integer password)throws SQLException;
    List<AccountMember> listMembers() throws SQLException;
    List<AccountMember> listMembersCpfAndPassword(Integer numberAccount, Integer password) throws SQLException;
}
