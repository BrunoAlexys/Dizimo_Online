package br.com.unifacol.dizimo.model.interfaces.service;

import br.com.unifacol.dizimo.model.entities.Address;
import br.com.unifacol.dizimo.model.entities.Church;
import br.com.unifacol.dizimo.model.entities.ChurchAccount;

import java.sql.SQLException;
import java.util.List;

public interface IChurchService {
    void create(Church church) throws SQLException;
    void update(Church church) throws SQLException;
    void delete(String cpf,Integer password)throws SQLException;
    List<Church> listChurch() throws SQLException;
    List<Church> listChurchCnpjAndPassword(Long id) throws SQLException;
}
