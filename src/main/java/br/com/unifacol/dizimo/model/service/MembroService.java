package br.com.unifacol.dizimo.model.service;

import br.com.unifacol.dizimo.model.entities.Membro;
import br.com.unifacol.dizimo.model.interfaces.service.IMembroService;
import br.com.unifacol.dizimo.model.repository.BuscarConta;
import br.com.unifacol.dizimo.model.repository.MembroRepository;
import br.com.unifacol.dizimo.model.validator.ValidadorCPF;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class MembroService implements IMembroService {
    private ValidadorCPF validadorCPF = new ValidadorCPF();
    private MembroRepository membroRepository;
    private BuscarConta buscarConta = new BuscarConta();

    public MembroService(MembroRepository membroRepository) {
        this.membroRepository = membroRepository;
    }

    @Override
    public void cadastrar(Membro membro) throws SQLException {
        do {
            if (!validadorCPF.verificarCpf(membro.getCpf())) {
                System.out.println("CPF inválido. Digite novamente.");
                membro.setCpf(JOptionPane.showInputDialog("CPF: "));
            }
        } while (!validadorCPF.verificarCpf(membro.getCpf()));

        while (membro.getSenha() < 100000 || membro.getSenha() > 999999) {
            System.out.println("A senha deve ter 6 dígitos!");
            System.out.print("Digite a senha (6 dígitos): ");
            membro.setSenha(Integer.parseInt(JOptionPane.showInputDialog("Senha: ")));
        }
        membroRepository.cadastrar(membro);
    }

    @Override
    public void atualizar(String cpf, Integer password, Membro membro) throws SQLException {
       Membro membroEncontrado = buscarConta.pesquisarMembroPorCpfESenha(cpf,password);
       if (membroEncontrado != null){
           do {

               if (!validadorCPF.verificarCpf(membro.getCpf())) {
                   System.out.println("CPF inválido. Digite novamente.");
                   membro.setCpf(JOptionPane.showInputDialog("CPF: "));
               }
           } while (!validadorCPF.verificarCpf(membro.getCpf()));

           while (membro.getSenha() < 100000 || membro.getSenha() > 999999) {
               System.out.println("A senha deve ter 6 dígitos!");
               System.out.print("Digite a senha (6 dígitos): ");
               membro.setSenha(Integer.parseInt(JOptionPane.showInputDialog("Senha: ")));
           }
           membroRepository.atualizar(membro);
       }
    }

    @Override
    public void excluir(String cpf, Integer senha) throws SQLException {
        membroRepository.excluir(cpf, senha);
    }

    @Override
    public void listMembros() throws SQLException {
        List<Membro> membros = membroRepository.listMembros();
        membros.forEach(System.out::println);
    }

    @Override
    public void listarMembroPorCPFESenha(String cpf, Integer password) throws SQLException {
        List<Membro> membros = membroRepository.listmembrosPorCpfESenha(cpf,password);
        membros.forEach(System.out::println);
    }
}
