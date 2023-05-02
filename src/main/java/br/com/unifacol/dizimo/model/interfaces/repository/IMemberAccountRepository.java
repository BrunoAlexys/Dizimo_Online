package br.com.unifacol.dizimo.model.interfaces.repository;

import br.com.unifacol.dizimo.model.entities.Church;
import br.com.unifacol.dizimo.model.entities.MemberAccount;

import java.sql.SQLException;
import java.util.List;

public interface IMemberAccountRepository {
    void create(MemberAccount memberAccount) throws SQLException;
    void update(MemberAccount memberAccount) throws SQLException;
    void delete(String cnpj, Integer password)throws SQLException;
    List<MemberAccount> listMembers() throws SQLException;
    List<MemberAccount> listMembersCpfAndPassword(String cpf,Integer password) throws SQLException;
}
