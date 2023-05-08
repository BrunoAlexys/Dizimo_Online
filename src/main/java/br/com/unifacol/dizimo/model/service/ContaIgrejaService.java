package br.com.unifacol.dizimo.model.service;

import br.com.unifacol.dizimo.model.entities.ContaMembro;
import br.com.unifacol.dizimo.model.entities.ContaIgreja;
import br.com.unifacol.dizimo.model.interfaces.service.IMovimentacaoBancaria;
import br.com.unifacol.dizimo.model.interfaces.service.IContaIgrejaService;
import br.com.unifacol.dizimo.model.repository.BuscarConta;
import br.com.unifacol.dizimo.model.repository.ContaIgrejaRepository;
import br.com.unifacol.dizimo.model.util.JPAUtil;
import javax.persistence.EntityManager;
import javax.swing.*;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class ContaIgrejaService implements IContaIgrejaService, IMovimentacaoBancaria {
    private final ContaIgrejaRepository contaIgrejaRepository;
    private BuscarConta buscarConta = new BuscarConta();
    private EntityManager manager = JPAUtil.getEntityManager();

    public ContaIgrejaService(ContaIgrejaRepository contaIgrejaRepository) {
        this.contaIgrejaRepository = contaIgrejaRepository;
    }

    @Override
    public void cadastrar(ContaIgreja contaIgreja) throws SQLException {
        while (contaIgreja.getSenha() < 100000 || contaIgreja.getSenha() > 999999) {
            System.out.println("A senha deve ter 6 dígitos!");
            System.out.print("Digite a senha (6 dígitos): ");
            contaIgreja.setSenha(Integer.parseInt(JOptionPane.showInputDialog("Password: ")));
        }

        contaIgrejaRepository.cadastrar(contaIgreja);
    }

    @Override
    public void atualizar(Integer numeroAtualDaConta, Integer senhaAtual, ContaIgreja contaIgreja) throws SQLException {
        while (contaIgreja.getSenha() < 100000 || contaIgreja.getSenha() > 999999) {
            System.out.println("A senha deve ter 6 dígitos!");
            System.out.print("Digite a senha (6 dígitos): ");
            contaIgreja.setSenha(Integer.parseInt(JOptionPane.showInputDialog("Password: ")));
        }

        contaIgrejaRepository.atualizar(numeroAtualDaConta, senhaAtual, contaIgreja);
    }

    @Override
    public void excluir(Integer numeroDaConta, Integer senha) throws SQLException {
        contaIgrejaRepository.excluir(numeroDaConta, senha);
    }

    @Override
    public void listContaIgreja() throws SQLException {
        List<ContaIgreja> contaIgrejaList = contaIgrejaRepository.listContaIgreja();
        contaIgrejaList.forEach(System.out::println);
    }

    @Override
    public void listarContaIgrejaPorNumeroESenha(Integer numeroDaConta, Integer senha) throws SQLException {
        List<ContaIgreja> contaIgrejaList = contaIgrejaRepository.listContaIgrejaPorNumeroESenha(numeroDaConta, senha);
        contaIgrejaList.forEach(System.out::println);
    }

    @Override
    public void depositar(Integer numeroDaConta, Integer senha, BigDecimal valor) throws SQLException {
        try {
            ContaIgreja contaIgrejaEncontrada = buscarConta.pesquisarContaIgreja(numeroDaConta, senha);
            if (contaIgrejaEncontrada != null){
                manager.getTransaction().begin();
                BigDecimal currentBalance = contaIgrejaEncontrada.getSaldo();
                BigDecimal newBalance = currentBalance.add(valor);
                contaIgrejaEncontrada.setSaldo(newBalance);
                manager.merge(contaIgrejaEncontrada);
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
    public void sacar(Integer numeroDaConta, Integer senha, BigDecimal valor) throws SQLException {
        ContaIgreja contaIgrejaEncontrada = buscarConta.pesquisarContaIgreja(numeroDaConta, senha);
        if (contaIgrejaEncontrada != null){
            BigDecimal currentBalance = contaIgrejaEncontrada.getSaldo();
            if (valor.compareTo(BigDecimal.ZERO) < 0){
                throw new IllegalArgumentException("O valor do saque deve ser maior que zero");
            } else if (currentBalance.compareTo(valor) >= 0) {
                manager.getTransaction().begin();
                BigDecimal newBalance = currentBalance.subtract(valor);
                contaIgrejaEncontrada.setSaldo(newBalance);
                manager.merge(contaIgrejaEncontrada);
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
        ContaIgreja contaIgrejaEncontrada = buscarConta.pesquisarContaIgreja(numeroDaContaOrigem, senha);
        if (contaIgrejaEncontrada != null){
            manager.getTransaction().begin();
            BigDecimal currentBalance = contaIgrejaEncontrada.getSaldo();
            if (valor.compareTo(BigDecimal.ZERO) <= 0){
                throw new IllegalArgumentException("O valor da transferencia não pode ser 0");
            } else if (currentBalance.compareTo(valor) >= 0) {
                BigDecimal newBalance = currentBalance.subtract(valor);
                contaIgrejaEncontrada.setSaldo(newBalance);
                manager.merge(contaIgrejaEncontrada);
                try {
                    ContaMembro contaMembroEncontrada = buscarConta.pesquisarContaMembroPorNumero(numeroDaContaDestino);
                    if (contaMembroEncontrada != null){
                        contaMembroEncontrada.setSaldo(contaMembroEncontrada.getSaldo().add(valor));
                        manager.merge(contaMembroEncontrada);
                    }
                } catch (Exception ex) {
                    manager.getTransaction().rollback();
                    throw new SQLException("Erro ao atualizar a conta do membro", ex);
                }
                manager.getTransaction().commit();
            }
        }
    }

}
