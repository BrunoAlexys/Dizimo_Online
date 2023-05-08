package br.com.unifacol.dizimo.model.repository;

import br.com.unifacol.dizimo.model.entities.Membro;
import br.com.unifacol.dizimo.model.interfaces.repository.IMembroRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MembroRepository implements IMembroRepository {
    private EntityManager manager;
    private final BuscarConta buscarConta = new BuscarConta();

    public MembroRepository(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void cadastrar(Membro membro) throws SQLException {
        try {
            this.manager.getTransaction().begin();
            this.manager.persist(membro);
            if (manager.getTransaction().isActive()) {
                if (manager.getTransaction().getRollbackOnly()) {
                    manager.getTransaction().rollback();
                } else {
                    manager.getTransaction().commit();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao cadastrar membro", e);
        }
    }

    @Override
    public void atualizar(Membro membro) throws SQLException {
        try {
            Membro membroEncontrado = buscarConta.pesquisarMembroPorCpfESenha(membro.getCpf(), membro.getSenha());
            if (membroEncontrado != null) {
                manager.getTransaction().begin();
                membroEncontrado = manager.merge(membroEncontrado);
                membroEncontrado.setNome(membro.getNome());
                membroEncontrado.setIdade(membro.getIdade());
                membroEncontrado.setDataDeNascimento(membro.getDataDeNascimento());
                membroEncontrado.setCpf(membro.getCpf());
                membroEncontrado.setSexo(membro.getSexo());
                membroEncontrado.setAtivo(membro.getAtivo());
                membroEncontrado.setSenha(membro.getSenha());
                membroEncontrado.setEndereco(membro.getEndereco());
                membroEncontrado.setContaMembro(membro.getContaMembro());
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
            throw new RuntimeException("Erro ao alterar o membro", e);
        } finally {
            manager.close();
        }
    }

    @Override
    public void excluir(String cpf, Integer senha) throws SQLException {
        try {
            Membro membroEncontrado = buscarConta.pesquisarMembroPorCpfESenha(cpf, senha);
            if (membroEncontrado == null) {
                throw new NoResultException("Não foi encontrada nenhuma conta com CPF: " + cpf);
            }
            manager.getTransaction().begin();
            membroEncontrado = manager.merge(membroEncontrado);
            manager.remove(membroEncontrado);
            if (manager.getTransaction().isActive()) {
                if (manager.getTransaction().getRollbackOnly()) {
                    manager.getTransaction().rollback();
                } else {
                    manager.getTransaction().commit();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao excluir o membro", e);
        } finally {
            manager.close();
        }
    }

    @Override
    public List<Membro> listMembros() throws SQLException {
        try {
            String jpql = "SELECT m FROM Membro m ORDER BY m.nome";
            TypedQuery<Membro> query = manager.createQuery(jpql, Membro.class);
            List<Membro> listMembros = query.getResultList();
            if (listMembros.isEmpty()) {
                System.out.println("Não há membros cadastrados.");
            }
            return listMembros;
        } catch (IllegalStateException | IllegalArgumentException e) {
            throw new SQLException("Erro ao listar membros: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar os membros", e);
        } finally {
            manager.close();
        }
    }

    @Override
    public List<Membro> listmembrosPorCpfESenha(String cpf, Integer senha) throws SQLException {
        try {
            Membro membroEncontrado = buscarConta.pesquisarMembroPorCpfESenha(cpf, senha);
            List<Membro> listMembros = new ArrayList<>();
            if (membroEncontrado != null) {
                listMembros.add(membroEncontrado);
            }
            return listMembros;
        } catch (IllegalStateException | IllegalArgumentException e) {
            throw new SQLException("Erro ao listar o membro pelo CPF: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar o membro", e);
        } finally {
            manager.close();
        }
    }
}
