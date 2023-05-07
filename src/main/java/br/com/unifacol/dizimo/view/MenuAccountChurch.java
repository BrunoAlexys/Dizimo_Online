package br.com.unifacol.dizimo.view;

import br.com.unifacol.dizimo.model.entities.Church;
import br.com.unifacol.dizimo.model.entities.ChurchAccount;
import br.com.unifacol.dizimo.model.repository.AccountFinder;
import br.com.unifacol.dizimo.model.repository.ChurchAcountRepository;
import br.com.unifacol.dizimo.model.service.ChurchAccountService;
import br.com.unifacol.dizimo.model.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.math.BigDecimal;
import java.sql.SQLException;

public class MenuAccountChurch {
    private AccountFinder accountFinder = new AccountFinder();
    private EntityManager manager = JPAUtil.getEntityManager();
    private ChurchAcountRepository churchAcountRepository = new ChurchAcountRepository(manager);
    private ChurchAccountService churchAccountService = new ChurchAccountService(churchAcountRepository);

    public void registerChurchAccount() throws SQLException {
        String cnpj = JOptionPane.showInputDialog("CNPJ: ");
        Integer password = Integer.parseInt(JOptionPane.showInputDialog("Senha: "));
        Church churchFound = accountFinder.fetchChurchByCnpjAndPassword(cnpj,password);
        if (churchFound != null){
            Integer numberAccount = Integer.parseInt(JOptionPane.showInputDialog("Numero da conta: "));
            Integer passwordAccount = Integer.parseInt(JOptionPane.showInputDialog("Senha: "));
            churchFound = manager.merge(churchFound);
            ChurchAccount churchAccount = new ChurchAccount(numberAccount,passwordAccount,churchFound);
            churchAccountService.create(churchAccount);
        }
    }

    public void updateChurchAccount() throws SQLException{
        Integer numberAccountCurrente = Integer.parseInt(JOptionPane.showInputDialog("Digite o numero da sua conta: "));
        Integer passwordCurrente = Integer.parseInt(JOptionPane.showInputDialog("Digite sua senha: "));

        Integer newNumberAccountChurch = Integer.valueOf(JOptionPane.showInputDialog("Digite um novo numero da conta: "));
        Integer newPassword = Integer.parseInt(JOptionPane.showInputDialog("Digite uma nova senha: "));

        ChurchAccount churchAccount = new ChurchAccount(newNumberAccountChurch,newPassword);
        churchAccountService.update(newNumberAccountChurch,passwordCurrente,churchAccount);
    }

    public void deleteChurchAccount() throws SQLException {
        Integer numberAccountCurrente = Integer.parseInt(JOptionPane.showInputDialog("Digite o numero da sua conta: "));
        Integer passwordCurrente = Integer.parseInt(JOptionPane.showInputDialog("Digite sua senha: "));

        churchAccountService.delete(numberAccountCurrente,passwordCurrente);
    }

    public void listMemberAccount() throws SQLException{
        Integer numberAccountCurrente = Integer.parseInt(JOptionPane.showInputDialog("Digite o numero da sua conta: "));
        Integer passwordCurrente = Integer.parseInt(JOptionPane.showInputDialog("Digite sua senha: "));

        churchAccountService.listChurchAccountNumberAndPassword(numberAccountCurrente,passwordCurrente);
    }

    public void deposit() throws SQLException {
        Integer numberAccount = Integer.parseInt(JOptionPane.showInputDialog("Numero da conta: "));
        Integer passwordAccount = Integer.parseInt(JOptionPane.showInputDialog("Password: "));
        Double amount = Double.valueOf(JOptionPane.showInputDialog("Digite o valor do deposito: "));
        BigDecimal amountDeposit = BigDecimal.valueOf(amount);
        churchAccountService.deposit(numberAccount, passwordAccount, amountDeposit);
    }

    public void withdraw() throws SQLException {
        Integer numberAccount = Integer.parseInt(JOptionPane.showInputDialog("Numero da conta: "));
        Integer passwordAccount = Integer.parseInt(JOptionPane.showInputDialog("Password: "));
        Double amount = Double.valueOf(JOptionPane.showInputDialog("Digite o valor do saque: "));
        BigDecimal amountWithdraw = BigDecimal.valueOf(amount);
        churchAccountService.withdraw(numberAccount, passwordAccount, amountWithdraw);

    }

    public void transfer() throws SQLException {
        Integer sourceAccountNumber = Integer.parseInt(JOptionPane.showInputDialog("Digite o numero da sua conta: "));
        Integer password = Integer.parseInt(JOptionPane.showInputDialog("Digite sua senha: "));
        Integer destinationAccountNumber = Integer.parseInt(JOptionPane.showInputDialog("Digite o numero da conta do destinatario: "));
        Double transferAmount = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor da transferencia: "));
        BigDecimal transfer = BigDecimal.valueOf(transferAmount);
        churchAccountService.transfer(sourceAccountNumber, destinationAccountNumber, password, transfer);
    }
}
