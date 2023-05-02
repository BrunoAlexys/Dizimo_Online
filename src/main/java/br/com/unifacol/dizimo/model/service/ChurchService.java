package br.com.unifacol.dizimo.model.service;

import br.com.unifacol.dizimo.model.entities.Church;
import br.com.unifacol.dizimo.model.interfaces.service.IChurchService;
import br.com.unifacol.dizimo.model.repository.ChurchRepository;
import br.com.unifacol.dizimo.model.validator.ValidatorCNPJ;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class ChurchService implements IChurchService {
    private final ChurchRepository churchRepository;
    private ValidatorCNPJ validatorCNPJ = new ValidatorCNPJ();

    public ChurchService(ChurchRepository churchRepository) {
        this.churchRepository = churchRepository;
    }

    @Override
    public void create(Church church) throws SQLException {
        do {
            if (!validatorCNPJ.verificarCnpj(church.getCnpj())) {
                System.out.println("CPF inválido. Digite novamente.");
                church.setCnpj(JOptionPane.showInputDialog("CNPJ: "));
            }
        } while (!validatorCNPJ.verificarCnpj(church.getCnpj()));

        while (church.getPassword() < 100000 || church.getPassword() > 999999) {
            System.out.println("A senha deve ter 6 dígitos!");
            System.out.print("Digite a senha (6 dígitos): ");
            church.setPassword(Integer.parseInt(JOptionPane.showInputDialog("Password: ")));
        }

        churchRepository.create(church);
    }

    @Override
    public void update(Church church) throws SQLException {
        do {
            if (!validatorCNPJ.verificarCnpj(church.getCnpj())) {
                System.out.println("CPF inválido. Digite novamente.");
                church.setCnpj(JOptionPane.showInputDialog("CNPJ: "));
            }
        } while (!validatorCNPJ.verificarCnpj(church.getCnpj()));

        while (church.getPassword() < 100000 || church.getPassword() > 999999) {
            System.out.println("A senha deve ter 6 dígitos!");
            System.out.print("Digite a senha (6 dígitos): ");
            church.setPassword(Integer.parseInt(JOptionPane.showInputDialog("Password: ")));
        }

        churchRepository.update(church);
    }

    @Override
    public void delete(String cnpj, Integer password) throws SQLException {
        churchRepository.delete(cnpj,password);
    }

    @Override
    public void listChurch() throws SQLException {
        List<Church> churchList = churchRepository.listChurchs();
        churchList.forEach(System.out::println);
    }

    @Override
    public void listChurchCnpjAndPassword(String cnpj,Integer password) throws SQLException {
        List<Church> churchList = churchRepository.listChurchsCpfAndPassword(cnpj,password);
        churchList.forEach(System.out::println);
    }
}
