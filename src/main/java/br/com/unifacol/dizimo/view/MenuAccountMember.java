package br.com.unifacol.dizimo.view;

import br.com.unifacol.dizimo.model.entities.AccountMember;
import br.com.unifacol.dizimo.model.entities.Member;
import br.com.unifacol.dizimo.model.repository.AccountFinder;
import br.com.unifacol.dizimo.model.repository.MemberAccountRepository;
import br.com.unifacol.dizimo.model.service.MemberAccountService;

import javax.swing.*;
import java.math.BigDecimal;
import java.sql.SQLException;

public class MenuAccountMember {
    private AccountFinder accountFinder = new AccountFinder();
    private MemberAccountRepository memberAccountRepository = new MemberAccountRepository();
    private MemberAccountService memberAccountService = new MemberAccountService(memberAccountRepository);

    public void registerMemberAccount() throws SQLException {
        String cpf = JOptionPane.showInputDialog("CPF: ");
        Integer password = Integer.parseInt(JOptionPane.showInputDialog("Senha: "));
        Member memberFound = accountFinder.searchMemberByCpfAndPassword(cpf, password);
        if (memberFound != null) {
            Integer numberAccount = Integer.parseInt(JOptionPane.showInputDialog("Numero da conta: "));
            Integer passwordAccount = Integer.parseInt(JOptionPane.showInputDialog("Senha: "));
            AccountMember accountMember = new AccountMember(numberAccount, passwordAccount, memberFound);
            memberAccountService.create(accountMember);
        }
    }

    public void updateMemberAccount() throws SQLException {
        Integer numberAccountCurrente = Integer.parseInt(JOptionPane.showInputDialog("Digite o numero da sua conta: "));
        Integer passwordCurrente = Integer.parseInt(JOptionPane.showInputDialog("Digite sua senha: "));

        Integer newNumberAccountMember = Integer.valueOf(JOptionPane.showInputDialog("Digite um novo numero da conta: "));
        Integer newPassword = Integer.parseInt(JOptionPane.showInputDialog("Digite uma nova senha: "));

        AccountMember accountMember = new AccountMember(newNumberAccountMember, newPassword);

        memberAccountService.update(numberAccountCurrente, passwordCurrente, accountMember);

    }

    public void deleteMemberAccount() throws SQLException {
        Integer numberAccountCurrente = Integer.parseInt(JOptionPane.showInputDialog("Digite o numero da sua conta: "));
        Integer passwordCurrente = Integer.parseInt(JOptionPane.showInputDialog("Digite sua senha: "));

        memberAccountService.delete(numberAccountCurrente, passwordCurrente);
    }

    public void listMemberAccount() throws SQLException {
        Integer numberAccountCurrente = Integer.parseInt(JOptionPane.showInputDialog("Digite o numero da sua conta: "));
        Integer passwordCurrente = Integer.parseInt(JOptionPane.showInputDialog("Digite sua senha: "));

        memberAccountService.listMemberAccountsCpfAndPassword(numberAccountCurrente,passwordCurrente);
    }

    public void deposit() throws SQLException {
        Integer numberAccount = Integer.parseInt(JOptionPane.showInputDialog("Numero da conta: "));
        Integer passwordAccount = Integer.parseInt(JOptionPane.showInputDialog("Password: "));
        Double amount = Double.valueOf(JOptionPane.showInputDialog("Digite o valor do deposito: "));
        BigDecimal amountDeposit = BigDecimal.valueOf(amount);
        memberAccountService.deposit(numberAccount, passwordAccount, amountDeposit);
    }

    public void withdraw() throws SQLException {
        Integer numberAccount = Integer.parseInt(JOptionPane.showInputDialog("Numero da conta: "));
        Integer passwordAccount = Integer.parseInt(JOptionPane.showInputDialog("Password: "));
        Double amount = Double.valueOf(JOptionPane.showInputDialog("Digite o valor do saque: "));
        BigDecimal amountWithdraw = BigDecimal.valueOf(amount);
        memberAccountService.withdraw(numberAccount, passwordAccount, amountWithdraw);

    }

    public void transfer() throws SQLException {
        Integer sourceAccountNumber = Integer.parseInt(JOptionPane.showInputDialog("Digite o numero da sua conta: "));
        Integer password = Integer.parseInt(JOptionPane.showInputDialog("Digite sua senha: "));
        Integer destinationAccountNumber = Integer.parseInt(JOptionPane.showInputDialog("Digite o numero da conta do destinatario: "));
        Double transferAmount = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor da transferencia: "));
        BigDecimal transfer = BigDecimal.valueOf(transferAmount);
        memberAccountService.transfer(sourceAccountNumber, destinationAccountNumber, password, transfer);
    }
}
