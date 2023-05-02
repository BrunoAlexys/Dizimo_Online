package br.com.unifacol.dizimo.model.repository;

import br.com.unifacol.dizimo.model.entities.Address;
import br.com.unifacol.dizimo.model.interfaces.repository.IAddressRepository;
import br.com.unifacol.dizimo.model.util.JPAUtil;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressRepository implements IAddressRepository {
    private EntityManager manager;
    private AccountFinder accountFinder = new AccountFinder();

    public AddressRepository(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void create(Address address) throws SQLException {
        try {
            manager.getTransaction().begin();
           // manager.merge(address);
            manager.persist(address);

            if (manager.getTransaction().isActive()) {
                if (manager.getTransaction().getRollbackOnly()) {
                    manager.getTransaction().rollback();
                } else {
                    manager.getTransaction().commit();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao cadastrar endereço", e);
        }
    }

    @Override
    public void update(Long id, Address address) throws SQLException {
       try {
           Address addressFound = accountFinder.searchAddress(id);
           if (addressFound != null) {
               manager.getTransaction().begin();
               addressFound = manager.merge(addressFound);
               addressFound.setStreet(address.getStreet());
               addressFound.setNumber(address.getNumber());
               addressFound.setDistrict(address.getDistrict());
               addressFound.setCity(address.getCity());
               addressFound.setState(address.getState());

               if (manager.getTransaction().isActive()) {
                   if (manager.getTransaction().getRollbackOnly()) {
                       manager.getTransaction().rollback();
                   } else {
                       manager.getTransaction().commit();
                   }
               }
           }
       }catch (Exception e) {
           throw new RuntimeException("Erro ao alterar o endereço", e);
       } finally {
           manager.close();
       }
    }

    @Override
    public void delete(Long id) throws SQLException {
       try {
           Address addressFound = accountFinder.searchAddress(id);
           if (addressFound != null) {
               manager.getTransaction().begin();
               addressFound = manager.merge(addressFound);
               manager.remove(addressFound);
               if (manager.getTransaction().isActive()) {
                   if (manager.getTransaction().getRollbackOnly()) {
                       manager.getTransaction().rollback();
                   } else {
                       manager.getTransaction().commit();
                   }
               }
           }
       }catch (Exception e) {
           throw new RuntimeException("Erro ao excluir a igreja", e);
       } finally {
           manager.close();
       }
    }

    @Override
    public List<Address> listAdress() throws SQLException {
        try {
            String jpql = "SELECT a FROM Address a";
            TypedQuery<Address> query = manager.createQuery(jpql, Address.class);
            List<Address> addressList = query.getResultList();
            if (addressList.isEmpty()) {
                System.out.println("Não há endereços cadastrados!");
            }

            return addressList;
        } catch (IllegalStateException | IllegalArgumentException e) {
            throw new SQLException("Erro ao listar os endereços por ID: " + e.getMessage(), e);
        } finally {
            manager.close();
        }
    }

    @Override
    public List<Address> listAddressID(Long id) throws SQLException {
        try {
            Address addressFound = accountFinder.searchAddress(id);
            List<Address> addressList = new ArrayList<>();
            if (addressFound != null) {
                addressList.add(addressFound);
            }
            return addressList;
        } catch (IllegalStateException | IllegalArgumentException e) {
            throw new SQLException("Erro ao listar os endereços por ID: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar o endereço", e);
        } finally {
            manager.close();
        }
    }
}
