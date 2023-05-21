package br.com.unifacol.dizimo.model.repository;

import br.com.unifacol.dizimo.model.entities.ContaIgreja;
import br.com.unifacol.dizimo.model.entities.ContaMembro;
import br.com.unifacol.dizimo.model.interfaces.repository.IContaMembroRepository;
import br.com.unifacol.dizimo.model.util.JPAUtil;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ContaMembroRepository implements IContaMembroRepository {
    private final EntityManager manager;
    private BuscarConta buscarConta = new BuscarConta();

    public ContaMembroRepository(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void cadastrar(ContaMembro contaMembro) throws SQLException {
        try {
            manager.getTransaction().begin();
            manager.persist(contaMembro);
            if (manager.getTransaction().isActive()) {
                if (manager.getTransaction().getRollbackOnly()) {
                    manager.getTransaction().rollback();
                } else {
                    manager.getTransaction().commit();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao cadastrar a conta do membro", e);
        } finally {
            manager.close();
        }
    }

    @Override
    public void atualizar(Integer numeroDaContaAtual, Integer senhaAtual, ContaMembro contaMembro) throws SQLException {
        try {
            ContaMembro contaMembroEncontrada = buscarConta.pesquisarContaMembro(numeroDaContaAtual, senhaAtual);
            if (contaMembroEncontrada != null) {
                manager.getTransaction().begin();
                contaMembroEncontrada = manager.merge(contaMembroEncontrada);
                contaMembroEncontrada.setNumeroDaConta(contaMembro.getNumeroDaConta());
                contaMembroEncontrada.setSenha(contaMembro.getSenha());
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
            throw new RuntimeException("Erro ao alterar a conta membro", e);
        } finally {
            manager.close();
        }
    }

    @Override
    public void excluir(Integer numeroDaConta, Integer senha) throws SQLException {

        ContaMembro contaMembroEncontrada = buscarConta.pesquisarContaMembro(numeroDaConta, senha);
        if (contaMembroEncontrada.getSaldo().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Não é possivel excluir uma conta com saldo!");
        } else {
            manager.getTransaction().begin();
            contaMembroEncontrada = manager.merge(contaMembroEncontrada);
            System.out.println(contaMembroEncontrada);
            manager.remove(contaMembroEncontrada);
            manager.flush();
            if (manager.getTransaction().isActive()) {
                if (manager.getTransaction().getRollbackOnly()) {
                    manager.getTransaction().rollback();
                } else {
                    manager.getTransaction().commit();
                }
            }
        }
    }

    @Override
    public List<ContaMembro> listMembros() throws SQLException {
        try {
            String jpql = "SELECT m FROM ContaMembro m ";
            TypedQuery<ContaMembro> query = manager.createQuery(jpql, ContaMembro.class);
            List<ContaMembro> listContaMembros = query.getResultList();
            if (listContaMembros.isEmpty()) {
                System.out.println("Não a contas cadastradas!");
            }
            return listContaMembros;
        } catch (IllegalStateException | IllegalArgumentException e) {
            throw new SQLException("Erro ao listar as contas: " + e.getMessage(), e);
        } finally {
            manager.close();
        }
    }

    @Override
    public List<ContaMembro> listContaMembroPorNumeroESenha(Integer numeroDaconta, Integer senha) throws SQLException {
        try {
            ContaMembro contaMembro = buscarConta.pesquisarContaMembro(numeroDaconta, senha);
            List<ContaMembro> listContaMembro = new ArrayList<>();
            if (contaMembro != null) {
                listContaMembro.add(contaMembro);
            }
            return listContaMembro;
        } catch (NumberFormatException e) {
            System.out.println("Senha inválida");
        } catch (IllegalArgumentException e) {
            System.out.println("Conta não foi listada. Erro: " + e.getMessage());
        }
        return null;
    }

}

