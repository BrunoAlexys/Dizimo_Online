package br.com.unifacol.dizimo.model.repository;

import br.com.unifacol.dizimo.model.entities.*;
import br.com.unifacol.dizimo.model.interfaces.repository.IFetchAccounts;
import br.com.unifacol.dizimo.model.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.sql.SQLException;

public class AccountFinder implements IFetchAccounts {
    private EntityManager manager;

    public AccountFinder() {
        this.manager = JPAUtil.getEntityManager();
    }

    @Override
    public Member searchMemberByCpfAndPassword(String cpf, Integer password) throws SQLException{
        try {
            TypedQuery<Member> query = manager
                    .createQuery("SELECT m FROM Member m WHERE m.cpf = :cpf AND m.password = :password", Member.class);
            query.setParameter("cpf", cpf);
            query.setParameter("password", password);
            return query.getSingleResult();
        } catch (NoResultException e) {
            throw new NoResultException("Não foi encontrada nenhuma conta com CPF " + cpf);
        }
    }

    @Override
    public Member searchMemberByEmailAndPassword(String email, Integer password) throws SQLException {
        try {
            TypedQuery<Member> query = manager
                    .createQuery("SELECT m FROM Member m WHERE m.email = :email AND m.password = :password", Member.class);
            query.setParameter("email", email);
            query.setParameter("password", password);
            return query.getSingleResult();
        } catch (NoResultException e) {
            throw new NoResultException("Não foi encontrada nenhuma conta com o email: " + email);
        }
    }

    @Override
    public Church fetchChurchByCnpjAndPassword(String cnpj, Integer password) throws SQLException {
        try {
            TypedQuery<Church> query = manager
                    .createQuery("SELECT c FROM Church c WHERE c.cnpj = :cnpj AND c.password = :password", Church.class);
            query.setParameter("cnpj", cnpj);
            query.setParameter("password", password);
            return query.getSingleResult();
        } catch (NoResultException e) {
            throw new NoResultException("Não foi encontrada nenhuma igreja com CNPJ: " + cnpj);
        }
    }

    @Override
    public Church searchChurchByEmailAndPassword(String email, Integer password) throws SQLException {
        try {
            TypedQuery<Church> query = manager
                    .createQuery("SELECT c FROM Church c WHERE c.email = :email AND c.password = :password", Church.class);
            query.setParameter("email", email);
            query.setParameter("password", password);
            return query.getSingleResult();
        } catch (NoResultException e) {
            throw new NoResultException("Não foi encontrada nenhuma conta com o email: " + email);
        }
    }

    @Override
    public Address searchAddress(Long id) throws SQLException {
        try {
            Address addressFound = manager.find(Address.class, id);
            if (addressFound != null) {
                return addressFound;
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Endereço não encontrado!");
        }

        return null;
    }

    @Override
    public AccountMember fetchMemberAccount(Integer numberAccount, Integer password) throws SQLException {

        try {
            TypedQuery<AccountMember> query = manager.createQuery(
                    "SELECT ma FROM AccountMember ma WHERE ma.accountNumber = :accountNumber AND ma.password = :password",
                    AccountMember.class);
            query.setParameter("accountNumber", numberAccount);
            query.setParameter("password", password);
            return query.getSingleResult();
        } catch (NoResultException e) {
            throw new NoResultException("Não foi encontrada nenhuma conta com o numero: " + numberAccount);
        }
    }

    @Override
    public AccountMember fetchMemberAccountByNumber(Integer accountNumber) throws SQLException {
        try {
            TypedQuery<AccountMember> query = manager.createQuery(
                    "SELECT c FROM AccountMember c WHERE c.accountNumber = :accountNumber", AccountMember.class);
            query.setParameter("accountNumber", accountNumber);
            return query.getSingleResult();
        } catch (NoResultException e) {
            throw new NoResultException("Não foi encontrada nenhuma conta com o numero: " + accountNumber);
        }
    }

    @Override
    public ChurchAccount searchChurchAccount(Integer accountNumber, Integer password) throws SQLException {
        try {
            TypedQuery<ChurchAccount> query = manager.createQuery(
                    "SELECT c FROM ChurchAccount c WHERE c.accountNumber = :accountNumber AND c.password = :password", ChurchAccount.class);
            query.setParameter("accountNumber", accountNumber);
            query.setParameter("password", password);
            return query.getSingleResult();
        } catch (NoResultException e) {
            throw new NoResultException("Não foi encontrada nenhuma conta com o numero: " + accountNumber);
        }
    }

    @Override
    public ChurchAccount searchChurchAccountByNumber(Integer accountNumber) throws SQLException {
        try {
            TypedQuery<ChurchAccount> query = manager.createQuery(
                    "SELECT c FROM AccountMember c WHERE c.accountNumber = :accountNumber",
                    ChurchAccount.class);
            query.setParameter("accountNumber", accountNumber);
            return query.getSingleResult();
        } catch (NoResultException e) {
            throw new NoResultException("Não foi encontrada nenhuma conta com o numero: " + accountNumber);
        }
    }
}
