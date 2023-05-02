package br.com.unifacol.dizimo.model.interfaces.service;

import br.com.unifacol.dizimo.model.entities.Address;

import java.sql.SQLException;
import java.util.List;

public interface IAdressService {
    void create(Address address) throws SQLException;
    void update(Long id,Address address) throws SQLException;
    void delete(Long id)throws SQLException;
    List<Address> listAdress() throws SQLException;
    List<Address> listAddressID(Long id) throws SQLException;
}
