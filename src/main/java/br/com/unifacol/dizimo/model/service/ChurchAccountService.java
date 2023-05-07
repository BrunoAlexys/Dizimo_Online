package br.com.unifacol.dizimo.model.service;

import br.com.unifacol.dizimo.model.entities.AccountMember;
import br.com.unifacol.dizimo.model.entities.ChurchAccount;
import br.com.unifacol.dizimo.model.interfaces.service.IBankTransition;
import br.com.unifacol.dizimo.model.interfaces.service.IChurchAccountService;
import br.com.unifacol.dizimo.model.repository.AccountFinder;
import br.com.unifacol.dizimo.model.repository.ChurchAcountRepository;
import br.com.unifacol.dizimo.model.util.JPAUtil;
import javax.persistence.EntityManager;
import javax.swing.*;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class ChurchAccountService implements IChurchAccountService, IBankTransition {
    private final ChurchAcountRepository churchAcountRepository;
    private AccountFinder accountFinder = new AccountFinder();
    private EntityManager manager = JPAUtil.getEntityManager();

    public ChurchAccountService(ChurchAcountRepository churchAcountRepository) {
        this.churchAcountRepository = churchAcountRepository;
    }

    @Override
    public void create(ChurchAccount churchAccount) throws SQLException {
        while (churchAccount.getPassword() < 100000 || churchAccount.getPassword() > 999999) {
            System.out.println("A senha deve ter 6 dígitos!");
            System.out.print("Digite a senha (6 dígitos): ");
            churchAccount.setPassword(Integer.parseInt(JOptionPane.showInputDialog("Password: ")));
        }

        churchAcountRepository.create(churchAccount);
    }

    @Override
    public void update(Integer numberAccountActual, Integer passwordActual, ChurchAccount churchAccount) throws SQLException {
        while (churchAccount.getPassword() < 100000 || churchAccount.getPassword() > 999999) {
            System.out.println("A senha deve ter 6 dígitos!");
            System.out.print("Digite a senha (6 dígitos): ");
            churchAccount.setPassword(Integer.parseInt(JOptionPane.showInputDialog("Password: ")));
        }

        churchAcountRepository.update(numberAccountActual, passwordActual, churchAccount);
    }

    @Override
    public void delete(Integer accountNumber, Integer password) throws SQLException {
        churchAcountRepository.delete(accountNumber,password);
    }

    @Override
    public void listChurchAccounts() throws SQLException {
        List<ChurchAccount> churchAccountList = churchAcountRepository.listChurchsAccount();
        churchAccountList.forEach(System.out::println);
    }

    @Override
    public void listChurchAccountNumberAndPassword(Integer accountNumber, Integer password) throws SQLException {
        List<ChurchAccount> churchAccountList = churchAcountRepository.listChurchAccountsCpfAndPassword(accountNumber,password);
        churchAccountList.forEach(System.out::println);
    }

    @Override
    public void deposit(Integer accountNumber, Integer password, BigDecimal amount) throws SQLException {
        try {
            ChurchAccount foundChurchAccount = accountFinder.searchChurchAccount(accountNumber,password);
            if (foundChurchAccount != null){
                manager.getTransaction().begin();
                BigDecimal currentBalance = foundChurchAccount.getBalance();
                BigDecimal newBalance = currentBalance.add(amount);
                foundChurchAccount.setBalance(newBalance);
                manager.merge(foundChurchAccount);
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
        }catch (Exception e) {
            System.out.println("Não  foi possivel realizar o deposito!");
        }
    }

    @Override
    public void withdraw(Integer accountNumber, Integer password, BigDecimal amount) throws SQLException {
        ChurchAccount foundChurchAccount = accountFinder.searchChurchAccount(accountNumber,password);
        if (foundChurchAccount != null){
            BigDecimal currentBalance = foundChurchAccount.getBalance();
            if (amount.compareTo(BigDecimal.ZERO) < 0){
                throw new IllegalArgumentException("O valor do saque deve ser maior que zero");
            } else if (currentBalance.compareTo(amount) >= 0) {
                manager.getTransaction().begin();
                BigDecimal newBalance = currentBalance.subtract(amount);
                foundChurchAccount.setBalance(newBalance);
                manager.merge(foundChurchAccount);
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
        ChurchAccount foundChurchAccount = accountFinder.searchChurchAccount(sourceAccountNumber, password);
        if (foundChurchAccount != null){
            manager.getTransaction().begin();
            BigDecimal currentBalance = foundChurchAccount.getBalance();
            if (amount.compareTo(BigDecimal.ZERO) <= 0){
                throw new IllegalArgumentException("O valor da transferencia não pode ser 0");
            } else if (currentBalance.compareTo(amount) >= 0) {
                BigDecimal newBalance = currentBalance.subtract(amount);
                foundChurchAccount.setBalance(newBalance);
                manager.merge(foundChurchAccount);
                try {
                    AccountMember foundAccountMember = accountFinder.fetchMemberAccountByNumber(destinationAccountNumber);
                    if (foundAccountMember != null){
                        foundAccountMember.setBalance(foundAccountMember.getBalance().add(amount));
                        manager.merge(foundAccountMember);
                    }
                } catch (Exception ex) {
                    // Desfazer a transação em caso de exceção
                    manager.getTransaction().rollback();
                    throw new SQLException("Erro ao atualizar a conta do membro", ex);
                }
                manager.getTransaction().commit();
            }
        }
    }

}
