package br.com.unifacol.dizimo.model.interfaces.service;


import br.com.unifacol.dizimo.model.entities.Church;
import java.sql.SQLException;


public interface IChurchService {
    void create(Church church) throws SQLException;
    void update(String cnpj,Integer password,Church church) throws SQLException;
    void delete(String cnpj,Integer password)throws SQLException;
    void listChurch() throws SQLException;
    void listChurchCnpjAndPassword(String cnpj,Integer password) throws SQLException;
}
