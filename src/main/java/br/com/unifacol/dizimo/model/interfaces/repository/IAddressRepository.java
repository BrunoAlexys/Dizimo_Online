package br.com.unifacol.dizimo.model.interfaces.repository;

import br.com.unifacol.dizimo.model.entities.Address;
import br.com.unifacol.dizimo.model.entities.Member;

import java.sql.SQLException;
import java.util.List;

public interface IAddressRepository {
    void create(Address address) throws SQLException;
    void update(Address address) throws SQLException;
    void delete(Long id)throws SQLException;
    List<Address> listAdress() throws SQLException;
    List<Address> listAddressID(Long id) throws SQLException;
}
