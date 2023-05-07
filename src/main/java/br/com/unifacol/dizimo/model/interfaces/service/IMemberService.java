package br.com.unifacol.dizimo.model.interfaces.service;

import br.com.unifacol.dizimo.model.entities.Member;
import java.sql.SQLException;
import java.util.List;

public interface IMemberService {
    void create(Member member) throws SQLException;
    void update(String cpf, Integer password,Member member) throws SQLException;
    void delete(String cpf, Integer password)throws SQLException;
    void listMembers() throws SQLException;
    void listMembersCpfAndPassword(String cpf,Integer password) throws SQLException;
}
