package br.com.unifacol.dizimo.model.repository;

import br.com.unifacol.dizimo.model.entities.Church;
import br.com.unifacol.dizimo.model.interfaces.repository.IChurchRepository;
import br.com.unifacol.dizimo.model.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChurchRepository implements IChurchRepository {
    private final EntityManager manager;
    private final AccountFinder accountFinder = new AccountFinder();

    public ChurchRepository(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void create(Church church) throws SQLException {
        try {
            this.manager.getTransaction().begin();
            this.manager.persist(church);
            if (manager.getTransaction().isActive()) {
                if (manager.getTransaction().getRollbackOnly()) {
                    manager.getTransaction().rollback();
                } else {
                    manager.getTransaction().commit();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao cadastrar a igreja", e);
        } finally {
           manager.close();
        }
    }

    @Override
    public void update(String cnpj,Integer password, Church church) throws SQLException {
        try {
            Church churchfound = accountFinder.fetchChurchByCnpjAndPassword(church.getCnpj(), church.getPassword());
            if (churchfound != null) {
                manager.getTransaction().begin();
                churchfound = manager.merge(churchfound);
                churchfound.setChurchName(church.getChurchName());
                churchfound.setCnpj(church.getCnpj());
                churchfound.setEmail(church.getEmail());
                churchfound.setActive(church.getActive());
                churchfound.setFoundationDate(church.getFoundationDate());
                churchfound.setPassword(church.getPassword());
                churchfound.setAddress(church.getAddress());
                churchfound.setChurchAccount(church.getChurchAccount());
                manager.flush();
                if (manager.getTransaction().isActive()) {
                    if (manager.getTransaction().getRollbackOnly()) {
                        manager.getTransaction().rollback();
                    } else {
                        manager.getTransaction().commit();
                    }
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao alterar a igreja", e);
        } finally {
            manager.close();
        }
    }

    @Override
    public void delete(String cnpj, Integer password) throws SQLException {
        try {
            Church churchfound = accountFinder.fetchChurchByCnpjAndPassword(cnpj, password);
            if (churchfound == null) {
                throw new NoResultException("Não foi encontrada nenhuma conta com o CNPJ: " + cnpj);
            }
            manager.getTransaction().begin();
            churchfound = manager.merge(churchfound);
            manager.remove(churchfound);
            if (manager.getTransaction().isActive()) {
                if (manager.getTransaction().getRollbackOnly()) {
                    manager.getTransaction().rollback();
                } else {
                    manager.getTransaction().commit();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao excluir a igreja ", e);
        } finally {
            manager.close();
        }
    }

    @Override
    public List<Church> listChurchs() throws SQLException {
        try {
            String jpql = "SELECT c FROM Church c ORDER BY c.churchName";
            TypedQuery<Church> query = manager.createQuery(jpql, Church.class);
            List<Church> churchList = query.getResultList();
            if (churchList.isEmpty()) {
                System.out.println("Não há igrejas cadastradas.");
            }
            return churchList;
        } catch (IllegalStateException | IllegalArgumentException e) {
            throw new SQLException("Erro ao listar a igrejas: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar as igrejas ", e);
        } finally {
            manager.close();
        }
    }

    @Override
    public List<Church> listChurchsCpfAndPassword(String cnpj, Integer password) throws SQLException {
        try {
            Church churchFound = accountFinder.fetchChurchByCnpjAndPassword(cnpj,password);
            List<Church> churchList = new ArrayList<>();
            if (churchFound != null){
                churchList.add(churchFound);
            }
            return churchList;
        } catch (IllegalStateException | IllegalArgumentException e) {
            throw new SQLException("Erro ao listar a igreja pelo CNPJ: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar a igreja ", e);
        } finally {
            manager.close();
        }
    }
}
