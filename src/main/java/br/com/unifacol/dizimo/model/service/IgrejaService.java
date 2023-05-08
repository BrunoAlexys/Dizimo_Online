package br.com.unifacol.dizimo.model.service;

import br.com.unifacol.dizimo.model.entities.Igreja;
import br.com.unifacol.dizimo.model.interfaces.service.IIgrejaService;
import br.com.unifacol.dizimo.model.repository.IgrejaRepository;
import br.com.unifacol.dizimo.model.validator.ValidadorCNPJ;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class IgrejaService implements IIgrejaService {
    private final IgrejaRepository churchRepository;
    private ValidadorCNPJ validadorCNPJ = new ValidadorCNPJ();

    public IgrejaService(IgrejaRepository churchRepository) {
        this.churchRepository = churchRepository;
    }

    @Override
    public void cadastrar(Igreja igreja) throws SQLException {
        do {
            if (!validadorCNPJ.verificarCnpj(igreja.getCnpj())) {
                System.out.println("CPF inválido. Digite novamente.");
                igreja.setCnpj(JOptionPane.showInputDialog("CNPJ: "));
            }
        } while (!validadorCNPJ.verificarCnpj(igreja.getCnpj()));

        while (igreja.getSenha() < 100000 || igreja.getSenha() > 999999) {
            System.out.println("A senha deve ter 6 dígitos!");
            System.out.print("Digite a senha (6 dígitos): ");
            igreja.setSenha(Integer.parseInt(JOptionPane.showInputDialog("Password: ")));
        }

        churchRepository.create(igreja);
    }

    @Override
    public void atualizar(String cnpj, Integer senha, Igreja igreja) throws SQLException {
        do {
            if (!validadorCNPJ.verificarCnpj(igreja.getCnpj())) {
                System.out.println("CPF inválido. Digite novamente.");
                igreja.setCnpj(JOptionPane.showInputDialog("CNPJ: "));
            }
        } while (!validadorCNPJ.verificarCnpj(igreja.getCnpj()));

        while (igreja.getSenha() < 100000 || igreja.getSenha() > 999999) {
            System.out.println("A senha deve ter 6 dígitos!");
            System.out.print("Digite a senha (6 dígitos): ");
            igreja.setSenha(Integer.parseInt(JOptionPane.showInputDialog("Password: ")));
        }

        churchRepository.update(cnpj, senha, igreja);
    }

    @Override
    public void excluir(String cnpj, Integer senha) throws SQLException {
        churchRepository.delete(cnpj, senha);
    }

    @Override
    public void listIgreja() throws SQLException {
        List<Igreja> igrejaList = churchRepository.listChurchs();
        igrejaList.forEach(System.out::println);
    }

    @Override
    public void listarIgrejaPorCNPJESenha(String cnpj, Integer senha) throws SQLException {
        List<Igreja> igrejaList = churchRepository.listChurchsCpfAndPassword(cnpj, senha);
        igrejaList.forEach(System.out::println);
    }
}
