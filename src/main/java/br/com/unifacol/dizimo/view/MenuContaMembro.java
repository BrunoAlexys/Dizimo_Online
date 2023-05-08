package br.com.unifacol.dizimo.view;

import br.com.unifacol.dizimo.model.entities.ContaMembro;
import br.com.unifacol.dizimo.model.entities.Membro;
import br.com.unifacol.dizimo.model.repository.BuscarConta;
import br.com.unifacol.dizimo.model.repository.ContaMembroRepository;
import br.com.unifacol.dizimo.model.service.ContaMembroService;

import javax.swing.*;
import java.math.BigDecimal;
import java.sql.SQLException;

public class MenuContaMembro {
    private BuscarConta buscarConta = new BuscarConta();
    private ContaMembroRepository contaMembroRepository = new ContaMembroRepository();
    private ContaMembroService contaMembroService = new ContaMembroService(contaMembroRepository);

    public void cadastrarConta() throws SQLException {
        String cpf = JOptionPane.showInputDialog("CPF: ");
        Integer senha = Integer.parseInt(JOptionPane.showInputDialog("Senha: "));
        Membro contaMembroEncontrada = buscarConta.pesquisarMembroPorCpfESenha(cpf, senha);
        if (contaMembroEncontrada != null) {
            Integer numberAccount = Integer.parseInt(JOptionPane.showInputDialog("Numero da conta: "));
            Integer passwordAccount = Integer.parseInt(JOptionPane.showInputDialog("Senha: "));
            ContaMembro accountMember = new ContaMembro(numberAccount, passwordAccount, contaMembroEncontrada);
            contaMembroService.cadastrar(accountMember);
        }
    }

    public void atualizarConta() throws SQLException {
        Integer numeroAtualDaConta = Integer.parseInt(JOptionPane.showInputDialog("Digite o numero da sua conta: "));
        Integer senhaAtualDaConta = Integer.parseInt(JOptionPane.showInputDialog("Digite sua senha: "));

        Integer novoNumeroDaConta = Integer.valueOf(JOptionPane.showInputDialog("Digite um novo numero da conta: "));
        Integer novaSenha = Integer.parseInt(JOptionPane.showInputDialog("Digite uma nova senha: "));

        ContaMembro contaMembro = new ContaMembro(novoNumeroDaConta, novaSenha);

        contaMembroService.atualizar(numeroAtualDaConta, senhaAtualDaConta, contaMembro);

    }

    public void excluirConta() throws SQLException {
        Integer numeroDaConta = Integer.parseInt(JOptionPane.showInputDialog("Digite o numero da sua conta: "));
        Integer senha = Integer.parseInt(JOptionPane.showInputDialog("Digite sua senha: "));

        contaMembroService.excluir(numeroDaConta, senha);
    }

    public void listarContaMembro() throws SQLException {
        Integer numeroDaConta = Integer.parseInt(JOptionPane.showInputDialog("Digite o numero da sua conta: "));
        Integer senha = Integer.parseInt(JOptionPane.showInputDialog("Digite sua senha: "));

        contaMembroService.listMembrosPorNumeroESenha(numeroDaConta,senha);
    }

    public void depositar() throws SQLException {
        Integer numeroDaConta = Integer.parseInt(JOptionPane.showInputDialog("Numero da conta: "));
        Integer senhaDaConta = Integer.parseInt(JOptionPane.showInputDialog("Password: "));
        Double valor = Double.valueOf(JOptionPane.showInputDialog("Digite o valor do deposito: "));
        BigDecimal valorDoDeposito = BigDecimal.valueOf(valor);
        contaMembroService.depositar(numeroDaConta, senhaDaConta, valorDoDeposito);
    }

    public void sacar() throws SQLException {
        Integer numeroDaConta = Integer.parseInt(JOptionPane.showInputDialog("Numero da conta: "));
        Integer senha = Integer.parseInt(JOptionPane.showInputDialog("Password: "));
        Double valor = Double.valueOf(JOptionPane.showInputDialog("Digite o valor do saque: "));
        BigDecimal valorDoSaque = BigDecimal.valueOf(valor);
        contaMembroService.sacar(numeroDaConta, senha, valorDoSaque);

    }

    public void transferir() throws SQLException {
        Integer numeroDaContaOrigem = Integer.parseInt(JOptionPane.showInputDialog("Digite o numero da sua conta: "));
        Integer senha = Integer.parseInt(JOptionPane.showInputDialog("Digite sua senha: "));
        Integer numeroDaContaDestino = Integer.parseInt(JOptionPane.showInputDialog("Digite o numero da conta do destinatario: "));
        Double valor = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor da transferencia: "));
        BigDecimal valorDaTransferencia = BigDecimal.valueOf(valor);
        contaMembroService.transferir(numeroDaContaOrigem, numeroDaContaDestino, senha, valorDaTransferencia);
    }
}
