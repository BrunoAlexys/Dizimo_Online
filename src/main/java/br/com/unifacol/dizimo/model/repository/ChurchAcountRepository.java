package br.com.unifacol.dizimo.model.repository;

import br.com.unifacol.dizimo.model.entities.ChurchAccount;
import br.com.unifacol.dizimo.model.interfaces.repository.IChurchAccountRepository;
import br.com.unifacol.dizimo.model.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChurchAcountRepository implements IChurchAccountRepository {
    private final EntityManager manager;
    private AccountFinder accountFinder;

    public ChurchAcountRepository(EntityManager manager) {
        this.manager = JPAUtil.getEntityManager();
    }

    @Override
    public void create(ChurchAccount churchAccount) throws SQLException {
        try {
            manager.getTransaction().begin();
            manager.persist(churchAccount);
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
    public void update(Integer numberAccountActual, Integer passwordActual, ChurchAccount churchAccount) throws SQLException {
        try {
            ChurchAccount churchAccountFound = accountFinder.searchChurchAccount(numberAccountActual, passwordActual);
            if (churchAccountFound != null) {
                manager.getTransaction().begin();
                churchAccountFound = manager.merge(churchAccountFound);
                churchAccountFound.setAccountNumber(churchAccount.getAccountNumber());
                churchAccountFound.setAccountOpeningDate(churchAccount.getAccountOpeningDate());
                churchAccountFound.setBalance(churchAccount.getBalance());
                churchAccountFound.setPassword(churchAccount.getPassword());
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
    public void delete(Integer numberAccount, Integer password) throws SQLException {
        try {
            ChurchAccount churchAccountFound = accountFinder.searchChurchAccount(numberAccount, password);
            if (churchAccountFound.getBalance().compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException("Não é possivel excluir uma conta com saldo!");
            } else {
                churchAccountFound = manager.merge(churchAccountFound);
                this.manager.remove(churchAccountFound);
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
    public List<ChurchAccount> listChurchsAccount() throws SQLException {
        try {
            String jpql = "SELECT c FROM ChurchAccount c ";
            TypedQuery<ChurchAccount> query = manager.createQuery(jpql, ChurchAccount.class);
            List<ChurchAccount> churchAccountList = query.getResultList();
            if (churchAccountList.isEmpty()) {
                System.out.println("Não a contas cadastradas!");
            }
            return churchAccountList;
        } catch (IllegalStateException | IllegalArgumentException e) {
            throw new SQLException("Erro ao listar as contas: " + e.getMessage(), e);
        } finally {
            manager.close();
        }
    }

    @Override
    public List<ChurchAccount> listChurchAccountsCpfAndPassword(Integer numberAccount, Integer password) throws SQLException {
        try {
            ChurchAccount churchAccountFound = accountFinder.searchChurchAccount(numberAccount, password);
            List<ChurchAccount> churchAccountList = new ArrayList<>();
            if (churchAccountFound != null) {
                churchAccountList.add(churchAccountFound);
            }
            return churchAccountList;
        } catch (NumberFormatException e) {
            System.out.println("Senha inválida");
        } catch (IllegalArgumentException e) {
            System.out.println("Conta não foi listada. Erro: " + e.getMessage());
        }
        return Collections.emptyList();
    }
}

