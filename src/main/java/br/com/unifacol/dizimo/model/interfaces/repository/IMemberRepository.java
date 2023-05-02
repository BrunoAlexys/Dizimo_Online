package br.com.unifacol.dizimo.model.interfaces.repository;

import br.com.unifacol.dizimo.model.entities.Member;

import java.sql.SQLException;
import java.util.List;

public interface IMemberRepository {
    void create(Member member) throws SQLException;
    void update(Member member) throws SQLException;
    void delete(String cpf, Integer password)throws SQLException;
    List<Member> listMembers() throws SQLException;
    List<Member> listmembersCpfAndPassword(String cpf,Integer password) throws SQLException;
}
