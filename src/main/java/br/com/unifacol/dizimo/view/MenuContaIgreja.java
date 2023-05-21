package br.com.unifacol.dizimo.view;

import br.com.unifacol.dizimo.model.entities.Igreja;
import br.com.unifacol.dizimo.model.entities.ContaIgreja;
import br.com.unifacol.dizimo.model.repository.BuscarConta;
import br.com.unifacol.dizimo.model.repository.ContaIgrejaRepository;
import br.com.unifacol.dizimo.model.service.ContaIgrejaService;
import br.com.unifacol.dizimo.model.util.JPAUtil;
import javax.persistence.EntityManager;
import javax.swing.*;
import java.math.BigDecimal;
import java.sql.SQLException;

public class MenuContaIgreja {
    private BuscarConta buscarConta = new BuscarConta();
    private EntityManager manager = JPAUtil.getEntityManager();
    private ContaIgrejaRepository contaIgrejaRepository = new ContaIgrejaRepository(manager);
    private ContaIgrejaService contaIgrejaService = new ContaIgrejaService(contaIgrejaRepository);

    public void criarConta() throws SQLException {
        String cnpj = JOptionPane.showInputDialog("CNPJ: ");
        Integer senha = Integer.parseInt(JOptionPane.showInputDialog("Senha: "));
        Igreja igrejaEncontrado = buscarConta.pesquisarPorCNPJESenha(cnpj,senha);
        if (igrejaEncontrado != null){
            Integer numberAccount = Integer.parseInt(JOptionPane.showInputDialog("Numero da conta: "));
            Integer passwordAccount = Integer.parseInt(JOptionPane.showInputDialog("Senha: "));
            igrejaEncontrado = manager.merge(igrejaEncontrado);
            ContaIgreja contaIgreja = new ContaIgreja(numberAccount,passwordAccount, igrejaEncontrado);
            contaIgrejaService.cadastrar(contaIgreja);
        }
    }

    public void atualizarConta() throws SQLException{
        Integer numeroAtual = Integer.parseInt(JOptionPane.showInputDialog("Digite o numero da sua conta: "));
        Integer senhaAtual = Integer.parseInt(JOptionPane.showInputDialog("Digite sua senha: "));

        Integer novoNumeroDaConta = Integer.valueOf(JOptionPane.showInputDialog("Digite um novo numero da conta: "));
        Integer novaSenha = Integer.parseInt(JOptionPane.showInputDialog("Digite uma nova senha: "));

        ContaIgreja contaIgreja = new ContaIgreja(novoNumeroDaConta,novaSenha);
        contaIgrejaService.atualizar(numeroAtual,senhaAtual,contaIgreja);
    }

    public void excluirConta() throws SQLException {
        Integer numeroAtualDaConta = Integer.parseInt(JOptionPane.showInputDialog("Digite o numero da sua conta: "));
        Integer senhaAtual = Integer.parseInt(JOptionPane.showInputDialog("Digite sua senha: "));

        contaIgrejaService.excluir(numeroAtualDaConta,senhaAtual);
    }

    public void listarConta() throws SQLException{
        Integer numeroAtualDaConta = Integer.parseInt(JOptionPane.showInputDialog("Digite o numero da sua conta: "));
        Integer senhaAtualDaConta = Integer.parseInt(JOptionPane.showInputDialog("Digite sua senha: "));

        contaIgrejaService.listarContaIgrejaPorNumeroESenha(numeroAtualDaConta,senhaAtualDaConta);
    }

    public void depositar() throws SQLException {
        Integer numeroDaConta = Integer.parseInt(JOptionPane.showInputDialog("Numero da conta: "));
        Integer senhaDaConta = Integer.parseInt(JOptionPane.showInputDialog("Senha: "));
        Double valor = Double.valueOf(JOptionPane.showInputDialog("Digite o valor do deposito: "));
        BigDecimal valorDoDeposito = BigDecimal.valueOf(valor);
        contaIgrejaService.depositar(numeroDaConta, senhaDaConta, valorDoDeposito);
    }

    public void sacar() throws SQLException {
        Integer numeroDaConta = Integer.parseInt(JOptionPane.showInputDialog("Numero da conta: "));
        Integer senha = Integer.parseInt(JOptionPane.showInputDialog("Senha: "));
        Double valor = Double.valueOf(JOptionPane.showInputDialog("Digite o valor do saque: "));
        BigDecimal valorDoSaque = BigDecimal.valueOf(valor);
        contaIgrejaService.sacar(numeroDaConta, senha, valorDoSaque);

    }

    public void transferir() throws SQLException {
        Integer numeroDaContaOrigem = Integer.parseInt(JOptionPane.showInputDialog("Digite o numero da sua conta: "));
        Integer senha = Integer.parseInt(JOptionPane.showInputDialog("Digite sua senha: "));
        Integer numeroDaContaDestino = Integer.parseInt(JOptionPane.showInputDialog("Digite o numero da conta do destinatario: "));
        Double valor = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor da transferencia: "));
        BigDecimal valorDaTransferencia = BigDecimal.valueOf(valor);
        contaIgrejaService.transferir(numeroDaContaOrigem, numeroDaContaDestino, senha, valorDaTransferencia);
    }
}
