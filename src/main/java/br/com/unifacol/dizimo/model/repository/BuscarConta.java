package br.com.unifacol.dizimo.model.repository;

import br.com.unifacol.dizimo.model.entities.*;
import br.com.unifacol.dizimo.model.interfaces.repository.IBuscarConta;
import br.com.unifacol.dizimo.model.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.sql.SQLException;

public class BuscarConta implements IBuscarConta {
    private EntityManager manager;

    public BuscarConta() {
        this.manager = JPAUtil.getEntityManager();
    }

    @Override
    public Membro pesquisarMembroPorCpfESenha(String cpf, Integer senha) throws SQLException{
        try {
            TypedQuery<Membro> query = manager
                    .createQuery("SELECT m FROM Membro m WHERE m.cpf = :cpf AND m.senha = :password", Membro.class);
            query.setParameter("cpf", cpf);
            query.setParameter("password", senha);
            return query.getSingleResult();
        } catch (NoResultException e) {
            throw new NoResultException("Não foi encontrada nenhuma conta com CPF " + cpf);
        }
    }

    @Override
    public Membro pesquisarMembroPorEmailESenha(String email, Integer senha) throws SQLException {
        try {
            TypedQuery<Membro> query = manager
                    .createQuery("SELECT m FROM Membro m WHERE m.email = :email AND m.senha = :password", Membro.class);
            query.setParameter("email", email);
            query.setParameter("password", senha);
            return query.getSingleResult();
        } catch (NoResultException e) {
            throw new NoResultException("Não foi encontrada nenhuma conta com o email: " + email);
        }
    }

    @Override
    public Igreja pesquisarPorCNPJESenha(String cnpj, Integer senha) throws SQLException {
        try {
            TypedQuery<Igreja> query = manager
                    .createQuery("SELECT c FROM Igreja c WHERE c.cnpj = :cnpj AND c.senha = :password", Igreja.class);
            query.setParameter("cnpj", cnpj);
            query.setParameter("password", senha);
            return query.getSingleResult();
        } catch (NoResultException e) {
            throw new NoResultException("Não foi encontrada nenhuma igreja com CNPJ: " + cnpj);
        }
    }

    @Override
    public Igreja pesquisarPorEmailESenha(String email, Integer senha) throws SQLException {
        try {
            TypedQuery<Igreja> query = manager
                    .createQuery("SELECT c FROM Igreja c WHERE c.email = :email AND c.senha = :password", Igreja.class);
            query.setParameter("email", email);
            query.setParameter("password", senha);
            return query.getSingleResult();
        } catch (NoResultException e) {
            throw new NoResultException("Não foi encontrada nenhuma conta com o email: " + email);
        }
    }

    @Override
    public Endereco pesquisarEndereco(Long id) throws SQLException {
        try {
            Endereco enderecoFound = manager.find(Endereco.class, id);
            if (enderecoFound != null) {
                return enderecoFound;
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Endereço não encontrado!");
        }

        return null;
    }

    @Override
    public ContaMembro pesquisarContaMembro(Integer numeroDaConta, Integer senha) throws SQLException {

        try {
            TypedQuery<ContaMembro> query = manager.createQuery(
                    "SELECT ma FROM ContaMembro ma WHERE ma.numeroDaConta = :accountNumber AND ma.senha = :password",
                    ContaMembro.class);
            query.setParameter("accountNumber", numeroDaConta);
            query.setParameter("password", senha);
            return query.getSingleResult();
        } catch (NoResultException e) {
            throw new NoResultException("Não foi encontrada nenhuma conta com o numero: " + numeroDaConta);
        }
    }

    @Override
    public ContaMembro pesquisarContaMembroPorNumero(Integer numeroDaConta) throws SQLException {
        try {
            TypedQuery<ContaMembro> query = manager.createQuery(
                    "SELECT c FROM ContaMembro c WHERE c.numeroDaConta = :accountNumber", ContaMembro.class);
            query.setParameter("accountNumber", numeroDaConta);
            return query.getSingleResult();
        } catch (NoResultException e) {
            throw new NoResultException("Não foi encontrada nenhuma conta com o numero: " + numeroDaConta);
        }
    }

    @Override
    public ContaIgreja pesquisarContaIgreja(Integer numeroDaConta, Integer senha) throws SQLException {
        try {
            TypedQuery<ContaIgreja> query = manager.createQuery(
                    "SELECT c FROM ContaIgreja c WHERE c.numeroDaConta = :accountNumber AND c.senha = :password", ContaIgreja.class);
            query.setParameter("accountNumber", numeroDaConta);
            query.setParameter("password", senha);
            return query.getSingleResult();
        } catch (NoResultException e) {
            throw new NoResultException("Não foi encontrada nenhuma conta com o numero: " + numeroDaConta);
        }
    }

    @Override
    public ContaIgreja pesquisarContaIgrejaPorNumero(Integer numeroDaConta) throws SQLException {
        try {
            TypedQuery<ContaIgreja> query = manager.createQuery(
                    "SELECT c FROM ContaIgreja c WHERE c.numeroDaConta = :accountNumber",
                    ContaIgreja.class);
            query.setParameter("accountNumber", numeroDaConta);
            return query.getSingleResult();
        } catch (NoResultException e) {
            throw new NoResultException("Não foi encontrada nenhuma conta com o numero: " + numeroDaConta);
        }
    }
}
