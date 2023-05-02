package br.com.unifacol.dizimo.model.service;

import br.com.unifacol.dizimo.model.entities.AccountMember;
import br.com.unifacol.dizimo.model.entities.ChurchAccount;
import br.com.unifacol.dizimo.model.interfaces.service.IBankTransition;
import br.com.unifacol.dizimo.model.interfaces.service.IMemberAccountService;
import br.com.unifacol.dizimo.model.repository.AccountFinder;
import br.com.unifacol.dizimo.model.repository.MemberAccountRepository;
import br.com.unifacol.dizimo.model.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class MemberAccountService implements IMemberAccountService, IBankTransition {
    private final MemberAccountRepository memberAccountRepository;
    private AccountFinder accountFinder = new AccountFinder();
    private EntityManager manager = JPAUtil.getEntityManager();

    public MemberAccountService(MemberAccountRepository memberAccountRepository) {
        this.memberAccountRepository = memberAccountRepository;
    }

    @Override
    public void create(AccountMember accountMember) throws SQLException {
        while (accountMember.getPassword() < 100000 || accountMember.getPassword() > 999999) {
            System.out.println("A senha deve ter 6 dígitos!");
            System.out.print("Digite a senha (6 dígitos): ");
            accountMember.setPassword(Integer.parseInt(JOptionPane.showInputDialog("Senha: ")));
        }
        memberAccountRepository.create(accountMember);
    }

    @Override
    public void update(Integer numberAccountActual, Integer passwordActual, AccountMember accountMember) throws SQLException {
        while (accountMember.getPassword() < 100000 || accountMember.getPassword() > 999999) {
            System.out.println("A senha deve ter 6 dígitos!");
            System.out.print("Digite a senha (6 dígitos): ");
            accountMember.setPassword(Integer.parseInt(JOptionPane.showInputDialog("Senha: ")));
        }

        memberAccountRepository.update(numberAccountActual, passwordActual, accountMember);
    }

    @Override
    public void delete(Integer numberAccount, Integer password) throws SQLException {
        memberAccountRepository.delete(numberAccount, password);
    }

    @Override
    public void listMemberAccounts() throws SQLException {
        List<AccountMember> accountMemberList = memberAccountRepository.listMembers();
        accountMemberList.forEach(System.out::println);
    }

    @Override
    public void listMemberAccountsCpfAndPassword(Integer numberAccount, Integer password) throws SQLException {
        List<AccountMember> accountMemberList = memberAccountRepository.listMembersCpfAndPassword(numberAccount, password);
        accountMemberList.forEach(System.out::println);
    }

    @Override
    public void deposit(Integer accountNumber, Integer password, BigDecimal amount) throws SQLException {
        try {
            AccountMember foundMemberAccount = accountFinder.fetchMemberAccount(accountNumber, password);
            if (foundMemberAccount != null) {
                manager.getTransaction().begin();
                BigDecimal currentBalance = foundMemberAccount.getBalance();
                BigDecimal newBalance = currentBalance.add(amount);
                foundMemberAccount.setBalance(newBalance);
                manager.merge(foundMemberAccount);
                manager.flush();
                if (manager.getTransaction().isActive()) {
                    if (manager.getTransaction().getRollbackOnly()) {
                        manager.getTransaction().rollback();
                        System.out.println("Não foi possivel realizar o deposito!");
                    } else {
                        manager.getTransaction().commit();
                        System.out.println("Deposito realizado com sucesso!");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Não  foi possivel realizar o deposito!");
        }
    }

    @Override
    public void withdraw(Integer accountNumber, Integer password, BigDecimal amount) throws SQLException {
        AccountMember foundMemberAccount = accountFinder.fetchMemberAccount(accountNumber, password);
        if (foundMemberAccount != null) {
            BigDecimal currentBalance = foundMemberAccount.getBalance();
            if (amount.compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException("O valor do saque deve ser maior que zero");
            } else if (currentBalance.compareTo(amount) >= 0) {
                manager.getTransaction().begin();
                BigDecimal newBalance = currentBalance.subtract(amount);
                foundMemberAccount.setBalance(newBalance);
                manager.merge(foundMemberAccount);
                manager.flush();
                if (manager.getTransaction().isActive()) {
                    if (manager.getTransaction().getRollbackOnly()) {
                        manager.getTransaction().rollback();
                        System.out.println("Não foi possivel realizar o saque!");
                    } else {
                        manager.getTransaction().commit();
                        System.out.println("Saque realizado com sucesso!");
                    }
                }
            }
        }
    }

    @Override
    public void transfer(Integer sourceAccountNumber, Integer destinationAccountNumber, Integer password, BigDecimal amount) throws SQLException {
        AccountMember  foundAccountMember = accountFinder.fetchMemberAccount(sourceAccountNumber,password);
        if (foundAccountMember != null){
            manager.getTransaction().begin();
            BigDecimal currentBalance = foundAccountMember.getBalance();
            if (amount.compareTo(BigDecimal.ZERO) <= 0){
                throw new IllegalArgumentException("O valor da transferencia não pode ser 0");
            } else if (currentBalance.compareTo(amount) >= 0) {
                BigDecimal newBalance = currentBalance.subtract(amount);
                manager.merge(foundAccountMember);
                manager.getTransaction().commit();
                ChurchAccount foundChurchAccount = accountFinder.searchChurchAccountByNumber(destinationAccountNumber);
                if (foundChurchAccount != null){
                    foundChurchAccount.setBalance(newBalance);
                    manager.merge(foundChurchAccount);
                    manager.getTransaction().commit();
                }
            }
        }
    }
}
