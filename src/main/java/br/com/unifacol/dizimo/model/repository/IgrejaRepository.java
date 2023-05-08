package br.com.unifacol.dizimo.model.repository;

import br.com.unifacol.dizimo.model.entities.Igreja;
import br.com.unifacol.dizimo.model.interfaces.repository.IIgrejaRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IgrejaRepository implements IIgrejaRepository {
    private final EntityManager manager;
    private final BuscarConta buscarConta = new BuscarConta();

    public IgrejaRepository(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void create(Igreja igreja) throws SQLException {
        try {
            this.manager.getTransaction().begin();
            this.manager.persist(igreja);
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
    public void update(String cnpj,Integer password, Igreja igreja) throws SQLException {
        try {
            Igreja igrejaEncontrada = buscarConta.pesquisarPorCNPJESenha(igreja.getCnpj(), igreja.getSenha());
            if (igrejaEncontrada != null) {
                manager.getTransaction().begin();
                igrejaEncontrada = manager.merge(igrejaEncontrada);
                igrejaEncontrada.setNomeDaIgreja(igreja.getNomeDaIgreja());
                igrejaEncontrada.setCnpj(igreja.getCnpj());
                igrejaEncontrada.setEmail(igreja.getEmail());
                igrejaEncontrada.setAtivo(igreja.getAtivo());
                igrejaEncontrada.setDataDeFundacao(igreja.getDataDeFundacao());
                igrejaEncontrada.setSenha(igreja.getSenha());
                igrejaEncontrada.setEndereco(igreja.getEndereco());
                igrejaEncontrada.setContaIgreja(igreja.getContaIgreja());
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
            Igreja igrejaEncontrada = buscarConta.pesquisarPorCNPJESenha(cnpj, password);
            if (igrejaEncontrada == null) {
                throw new NoResultException("Não foi encontrada nenhuma conta com o CNPJ: " + cnpj);
            }
            manager.getTransaction().begin();
            igrejaEncontrada = manager.merge(igrejaEncontrada);
            manager.remove(igrejaEncontrada);
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
    public List<Igreja> listChurchs() throws SQLException {
        try {
            String jpql = "SELECT c FROM Igreja c ORDER BY c.nomeDaIgreja";
            TypedQuery<Igreja> query = manager.createQuery(jpql, Igreja.class);
            List<Igreja> igrejaList = query.getResultList();
            if (igrejaList.isEmpty()) {
                System.out.println("Não há igrejas cadastradas.");
            }
            return igrejaList;
        } catch (IllegalStateException | IllegalArgumentException e) {
            throw new SQLException("Erro ao listar a igrejas: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar as igrejas ", e);
        } finally {
            manager.close();
        }
    }

    @Override
    public List<Igreja> listChurchsCpfAndPassword(String cnpj, Integer password) throws SQLException {
        try {
            Igreja igrejaEncontrada = buscarConta.pesquisarPorCNPJESenha(cnpj,password);
            List<Igreja> igrejaList = new ArrayList<>();
            if (igrejaEncontrada != null){
                igrejaList.add(igrejaEncontrada);
            }
            return igrejaList;
        } catch (IllegalStateException | IllegalArgumentException e) {
            throw new SQLException("Erro ao listar a igreja pelo CNPJ: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar a igreja ", e);
        } finally {
            manager.close();
        }
    }
}
