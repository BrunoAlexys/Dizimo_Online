package br.com.unifacol.dizimo.model.service;

import br.com.unifacol.dizimo.model.entities.Address;
import br.com.unifacol.dizimo.model.interfaces.service.IAdressService;
import br.com.unifacol.dizimo.model.repository.AddressRepository;

import java.sql.SQLException;
import java.util.List;

public class AddressService implements IAdressService {
    private AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        super();
        this.addressRepository = addressRepository;
    }

    @Override
    public void create(Address address) throws SQLException {
        addressRepository.create(address);
    }

    @Override
    public void update(Long id, Address address) throws SQLException {
        addressRepository.update(id,address);
    }

    @Override
    public void delete(Long id) throws SQLException {

    }

    @Override
    public List<Address> listAdress() throws SQLException {
        return null;
    }

    @Override
    public List<Address> listAddressID(Long id) throws SQLException {
        return null;
    }
}
