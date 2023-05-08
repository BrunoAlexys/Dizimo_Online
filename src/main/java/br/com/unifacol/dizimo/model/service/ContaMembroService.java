package br.com.unifacol.dizimo.model.service;

import br.com.unifacol.dizimo.model.entities.ContaMembro;
import br.com.unifacol.dizimo.model.entities.ContaIgreja;
import br.com.unifacol.dizimo.model.interfaces.service.IMovimentacaoBancaria;
import br.com.unifacol.dizimo.model.interfaces.service.IContaMembroService;
import br.com.unifacol.dizimo.model.repository.BuscarConta;
import br.com.unifacol.dizimo.model.repository.ContaMembroRepository;
import br.com.unifacol.dizimo.model.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class ContaMembroService implements IContaMembroService, IMovimentacaoBancaria {
    private final ContaMembroRepository contaMembroRepository;
    private BuscarConta buscarConta = new BuscarConta();
    private EntityManager manager = JPAUtil.getEntityManager();
    public ContaMembroService(ContaMembroRepository contaMembroRepository) {
        this.contaMembroRepository = contaMembroRepository;
    }

    @Override
    public void cadastrar(ContaMembro contaMembro) throws SQLException {
        while (contaMembro.getSenha() < 100000 || contaMembro.getSenha() > 999999) {
            System.out.println("A senha deve ter 6 dígitos!");
            System.out.print("Digite a senha (6 dígitos): ");
            contaMembro.setSenha(Integer.parseInt(JOptionPane.showInputDialog("Senha: ")));
        }
        contaMembroRepository.cadastrar(contaMembro);
    }

    @Override
    public void atualizar(Integer numeroAtualDaConta, Integer senhaAtual, ContaMembro contaMembro) throws SQLException {
        while (contaMembro.getSenha() < 100000 || contaMembro.getSenha() > 999999) {
            System.out.println("A senha deve ter 6 dígitos!");
            System.out.print("Digite a senha (6 dígitos): ");
            contaMembro.setSenha(Integer.parseInt(JOptionPane.showInputDialog("Senha: ")));
        }

        contaMembroRepository.atualizar(numeroAtualDaConta, senhaAtual, contaMembro);
    }

    @Override
    public void excluir(Integer numeroDaConta, Integer senha) throws SQLException {
        contaMembroRepository.excluir(numeroDaConta, senha);
    }

    @Override
    public void listContaMembro() throws SQLException {
        List<ContaMembro> accountMemberList = contaMembroRepository.listMembros();
        accountMemberList.forEach(System.out::println);
    }

    @Override
    public void listMembrosPorNumeroESenha(Integer numberAccount, Integer password) throws SQLException {
        List<ContaMembro> accountMemberList = contaMembroRepository.listContaMembroPorNumeroESenha(numberAccount, password);
        accountMemberList.forEach(System.out::println);
    }

    @Override
    public void depositar(Integer numeroDaConta, Integer senha, BigDecimal valor) throws SQLException {
        try {
            ContaMembro contaMembroEncontrada = buscarConta.pesquisarContaMembro(numeroDaConta, senha);
            if (contaMembroEncontrada != null) {
                manager.getTransaction().begin();
                BigDecimal currentBalance = contaMembroEncontrada.getSaldo();
                BigDecimal newBalance = currentBalance.add(valor);
                contaMembroEncontrada.setSaldo(newBalance);
                manager.merge(contaMembroEncontrada);
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
    public void sacar(Integer numeroDaConta, Integer senha, BigDecimal valor) throws SQLException {
        ContaMembro contaMembroEncontrada = buscarConta.pesquisarContaMembro(numeroDaConta, senha);
        if (contaMembroEncontrada != null) {
            BigDecimal currentBalance = contaMembroEncontrada.getSaldo();
            if (valor.compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException("O valor do saque deve ser maior que zero");
            } else if (currentBalance.compareTo(valor) >= 0) {
                manager.getTransaction().begin();
                BigDecimal newBalance = currentBalance.subtract(valor);
                contaMembroEncontrada.setSaldo(newBalance);
                manager.merge(contaMembroEncontrada);
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
    public void transferir(Integer numeroDaContaOrigem, Integer numeroDaContaDestino, Integer senha, BigDecimal valor) throws SQLException {
        ContaMembro contaMembroEncontrada = buscarConta.pesquisarContaMembro(numeroDaContaOrigem, senha);
        if (contaMembroEncontrada != null) {
            manager.getTransaction().begin();
            BigDecimal currentBalance = contaMembroEncontrada.getSaldo();
            if (valor.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("O valor da transferencia não pode ser 0");
            } else if (currentBalance.compareTo(valor) >= 0) {
                BigDecimal newBalance = currentBalance.subtract(valor);
                contaMembroEncontrada.setSaldo(newBalance);
                manager.merge(contaMembroEncontrada);
                try {
                    ContaIgreja contaIgrejaEncontrada = buscarConta.pesquisarContaIgrejaPorNumero(numeroDaContaDestino);
                    if (contaIgrejaEncontrada != null) {
                        contaIgrejaEncontrada.setSaldo(contaIgrejaEncontrada.getSaldo().add(valor));
                        manager.merge(contaIgrejaEncontrada);
                    }
                } catch (Exception ex) {
                    manager.getTransaction().rollback();
                    throw new SQLException("Erro ao atualizar a conta da igreja", ex);
                }
                manager.getTransaction().commit();
            }
        }
    }
}
