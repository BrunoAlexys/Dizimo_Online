package br.com.unifacol.dizimo.model.repository;

import br.com.unifacol.dizimo.model.entities.Endereco;
import br.com.unifacol.dizimo.model.interfaces.repository.IEnderecoRepository;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EnderecoRepository implements IEnderecoRepository {
    private EntityManager manager;
    private BuscarConta buscarConta = new BuscarConta();

    public EnderecoRepository(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void cadastrar(Endereco endereco) throws SQLException {
        try {
            manager.getTransaction().begin();
            manager.persist(endereco);

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
    public void atualizar(Long id, Endereco endereco) throws SQLException {
       try {
           Endereco enderecoEncontrado = buscarConta.pesquisarEndereco(id);
           if (enderecoEncontrado != null) {
               manager.getTransaction().begin();
               enderecoEncontrado = manager.merge(enderecoEncontrado);
               enderecoEncontrado.setRua(endereco.getRua());
               enderecoEncontrado.setNumero(endereco.getNumero());
               enderecoEncontrado.setBairro(endereco.getBairro());
               enderecoEncontrado.setCidade(endereco.getCidade());
               enderecoEncontrado.setEstado(endereco.getEstado());

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
    public void excluir(Long id) throws SQLException {
       try {
           Endereco enderecoEncontrado = buscarConta.pesquisarEndereco(id);
           if (enderecoEncontrado != null) {
               manager.getTransaction().begin();
               enderecoEncontrado = manager.merge(enderecoEncontrado);
               manager.remove(enderecoEncontrado);
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
    public List<Endereco> listEndereco() throws SQLException {
        try {
            String jpql = "SELECT a FROM Endereco a";
            TypedQuery<Endereco> query = manager.createQuery(jpql, Endereco.class);
            List<Endereco> enderecoList = query.getResultList();
            if (enderecoList.isEmpty()) {
                System.out.println("Não há endereços cadastrados!");
            }

            return enderecoList;
        } catch (IllegalStateException | IllegalArgumentException e) {
            throw new SQLException("Erro ao listar os endereços por ID: " + e.getMessage(), e);
        } finally {
            manager.close();
        }
    }

    @Override
    public List<Endereco> listEnderecoPorID(Long id) throws SQLException {
        try {
            Endereco enderecoEncontrado = buscarConta.pesquisarEndereco(id);
            List<Endereco> enderecoList = new ArrayList<>();
            if (enderecoEncontrado != null) {
                enderecoList.add(enderecoEncontrado);
            }
            return enderecoList;
        } catch (IllegalStateException | IllegalArgumentException e) {
            throw new SQLException("Erro ao listar os endereços por ID: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar o endereço", e);
        } finally {
            manager.close();
        }
    }
}
