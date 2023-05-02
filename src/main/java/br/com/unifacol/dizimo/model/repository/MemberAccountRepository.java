package br.com.unifacol.dizimo.model.repository;

import br.com.unifacol.dizimo.model.entities.AccountMember;
import br.com.unifacol.dizimo.model.interfaces.repository.IMemberAccountRepository;
import br.com.unifacol.dizimo.model.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MemberAccountRepository implements IMemberAccountRepository {
    private final EntityManager manager;
    private AccountFinder accountFinder;

    public MemberAccountRepository() {
        this.manager = JPAUtil.getEntityManager();
    }

    @Override
    public void create(AccountMember accountMember) throws SQLException {
        try {
            manager.getTransaction().begin();
            manager.persist(accountMember);
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
    public void update(Integer numberAccountActual, Integer passwordActual, AccountMember accountMember) throws SQLException {
        try {
            AccountMember accountMemberFound = accountFinder.fetchMemberAccount(numberAccountActual, passwordActual);
            if (accountMemberFound != null) {
                manager.getTransaction().begin();
                accountMemberFound = manager.merge(accountMemberFound);
                accountMemberFound.setAccountNumber(accountMember.getAccountNumber());
                accountMemberFound.setAccountOpeningDate(accountMember.getAccountOpeningDate());
                accountMemberFound.setBalance(accountMember.getBalance());
                accountMemberFound.setPassword(accountMember.getPassword());
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
    public void delete(Integer numberAccount, Integer password) throws SQLException {
        AccountMember accountMemberFound = accountFinder.fetchMemberAccount(numberAccount, password);
        if (accountMemberFound.getBalance().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Não é possivel excluir uma conta com saldo!");
        } else {
            accountMemberFound = manager.merge(accountMemberFound);
            this.manager.remove(accountMemberFound);
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
    public List<AccountMember> listMembers() throws SQLException {
        try {
            String jpql = "SELECT m FROM AccountMember m ";
            TypedQuery<AccountMember> query = manager.createQuery(jpql, AccountMember.class);
            List<AccountMember> accountMembers = query.getResultList();
            if (accountMembers.isEmpty()) {
                System.out.println("Não a contas cadastradas!");
            }
            return accountMembers;
        } catch (IllegalStateException | IllegalArgumentException e) {
            throw new SQLException("Erro ao listar as contas: " + e.getMessage(), e);
        } finally {
            manager.close();
        }
    }

    @Override
    public List<AccountMember> listMembersCpfAndPassword(Integer numberAccount, Integer password) throws SQLException {
        try {
            AccountMember accountMember = accountFinder.fetchMemberAccount(numberAccount, password);
            List<AccountMember> accountMemberList = new ArrayList<>();
            if (accountMember != null) {
                accountMemberList.add(accountMember);
            }
            return accountMemberList;
        } catch (NumberFormatException e) {
            System.out.println("Senha inválida");
        } catch (IllegalArgumentException e) {
            System.out.println("Conta não foi listada. Erro: " + e.getMessage());
        }
        return Collections.emptyList();
    }
}

