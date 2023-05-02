package br.com.unifacol.dizimo.model.repository;

import br.com.unifacol.dizimo.model.entities.Member;
import br.com.unifacol.dizimo.model.interfaces.repository.IMemberRepository;

import java.sql.SQLException;
import java.util.List;

public class MemberRepository implements IMemberRepository {

    @Override
    public void create(Member member) throws SQLException {

    }

    @Override
    public void update(Member member) throws SQLException {

    }

    @Override
    public void delete(String cpf, Integer password) throws SQLException {

    }

    @Override
    public List<Member> listMembers() throws SQLException {
        return null;
    }

    @Override
    public List<Member> listmembersCpfAndPassword(String cpf, Integer password) throws SQLException {
        return null;
    }
}
