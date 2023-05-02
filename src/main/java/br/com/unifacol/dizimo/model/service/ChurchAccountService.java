package br.com.unifacol.dizimo.model.service;

import br.com.unifacol.dizimo.model.entities.ChurchAccount;
import br.com.unifacol.dizimo.model.interfaces.service.IChurchAccountService;
import br.com.unifacol.dizimo.model.repository.ChurchAcountRepository;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class ChurchAccountService implements IChurchAccountService {
    private final ChurchAcountRepository churchAcountRepository;

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
    public void listChurchAccountCnpjAndPassword(Integer accountNumber, Integer password) throws SQLException {
        List<ChurchAccount> churchAccountList = churchAcountRepository.listChurchAccountsCpfAndPassword(accountNumber,password);
        churchAccountList.forEach(System.out::println);
    }
}
