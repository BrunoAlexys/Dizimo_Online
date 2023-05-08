package br.com.unifacol.dizimo.model.interfaces.repository;

import br.com.unifacol.dizimo.model.entities.Igreja;

import java.sql.SQLException;
import java.util.List;

public interface IIgrejaRepository {
    void create(Igreja igreja) throws SQLException;
    void update(String cnpj,Integer password, Igreja igreja) throws SQLException;
    void delete(String cnpj, Integer password)throws SQLException;
    List<Igreja> listChurchs() throws SQLException;
    List<Igreja> listChurchsCpfAndPassword(String cnpj, Integer password) throws SQLException;
}
