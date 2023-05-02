package br.com.unifacol.dizimo.model.interfaces.repository;

import br.com.unifacol.dizimo.model.entities.Church;
import br.com.unifacol.dizimo.model.entities.Member;

import java.sql.SQLException;
import java.util.List;

public interface IChurchRepository {
    void create(Church church) throws SQLException;
    void update(Church church) throws SQLException;
    void delete(String cnpj, Integer password)throws SQLException;
    List<Church> listChurchs() throws SQLException;
    List<Church> listChurchsCpfAndPassword(String cnpj,Integer password) throws SQLException;
}
