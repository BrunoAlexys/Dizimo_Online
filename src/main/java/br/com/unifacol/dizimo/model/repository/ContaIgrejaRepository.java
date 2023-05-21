package br.com.unifacol.dizimo.model.repository;

import br.com.unifacol.dizimo.model.entities.ContaIgreja;
import br.com.unifacol.dizimo.model.interfaces.repository.IContaIgrejaRepository;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ContaIgrejaRepository implements IContaIgrejaRepository {
    private final EntityManager manager;
    private BuscarConta buscarConta = new BuscarConta();

    public ContaIgrejaRepository(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void cadastrar(ContaIgreja contaIgreja) throws SQLException {
        try {
            manager.getTransaction().begin();
            manager.persist(contaIgreja);
            if (manager.getTransaction().isActive()) {
                if (manager.getTransaction().getRollbackOnly()) {
                    manager.getTransaction().rollback();
                } else {
                    manager.getTransaction().commit();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao cadastrar a conta da igreja ", e);
        } finally {
            manager.close();
        }
    }

    @Override
    public void atualizar(Integer numeroDaContaAtual, Integer senhaAtual, ContaIgreja contaIgreja) throws SQLException {
        try {
            ContaIgreja contaIgrejaEncontrada = buscarConta.pesquisarContaIgreja(numeroDaContaAtual, senhaAtual);
            if (contaIgrejaEncontrada != null) {
                manager.getTransaction().begin();
                contaIgrejaEncontrada = manager.merge(contaIgrejaEncontrada);
                contaIgrejaEncontrada.setNumeroDaConta(contaIgreja.getNumeroDaConta());
                contaIgrejaEncontrada.setSenha(contaIgreja.getSenha());
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
            throw new RuntimeException("Erro ao alterar a conta da igreja", e);
        } finally {
            manager.close();
        }
    }

    @Override
    public void excluir(Integer numeroDaConta, Integer senha) throws SQLException {
        try {
            ContaIgreja contaIgrejaEncontrada = buscarConta.pesquisarContaIgreja(numeroDaConta, senha);
            if (contaIgrejaEncontrada.getSaldo().compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException("Não é possivel excluir uma conta com saldo!");
            } else {
                manager.getTransaction().begin();
                contaIgrejaEncontrada = manager.merge(contaIgrejaEncontrada);
                this.manager.remove(contaIgrejaEncontrada);
                if (manager.getTransaction().isActive()) {
                    if (manager.getTransaction().getRollbackOnly()) {
                        manager.getTransaction().rollback();
                    } else {
                        manager.getTransaction().commit();
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao excluir a conta da igreja", e);
        } finally {
            manager.close();
        }
    }

    @Override
    public List<ContaIgreja> listContaIgreja() throws SQLException {
        try {
            String jpql = "SELECT c FROM ContaIgreja c ";
            TypedQuery<ContaIgreja> query = manager.createQuery(jpql, ContaIgreja.class);
            List<ContaIgreja> listContaIgreja = query.getResultList();
            if (listContaIgreja.isEmpty()) {
                System.out.println("Não a contas cadastradas!");
            }
            return listContaIgreja;
        } catch (IllegalStateException | IllegalArgumentException e) {
            throw new SQLException("Erro ao listar as contas: " + e.getMessage(), e);
        } finally {
            manager.close();
        }
    }

    @Override
    public List<ContaIgreja> listContaIgrejaPorNumeroESenha(Integer numeroDaConta, Integer senha) throws SQLException {
        try {
            ContaIgreja contaIgrejaEncontrada = buscarConta.pesquisarContaIgreja(numeroDaConta, senha);
            List<ContaIgreja> listContaIgreja = new ArrayList<>();
            if (contaIgrejaEncontrada != null) {
                listContaIgreja.add(contaIgrejaEncontrada);
            }
            return listContaIgreja;
        } catch (NumberFormatException e) {
            System.out.println("Senha inválida");
        } catch (IllegalArgumentException e) {
            System.out.println("Conta não foi listada. Erro: " + e.getMessage());
        }
        return Collections.emptyList();
    }
}

