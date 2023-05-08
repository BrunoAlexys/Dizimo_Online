package br.com.unifacol.dizimo.view;

import br.com.unifacol.dizimo.model.entities.Endereco;
import br.com.unifacol.dizimo.model.entities.Membro;
import br.com.unifacol.dizimo.model.enums.Genero;
import br.com.unifacol.dizimo.model.enums.Estado;
import br.com.unifacol.dizimo.model.repository.BuscarConta;
import br.com.unifacol.dizimo.model.repository.EnderecoRepository;
import br.com.unifacol.dizimo.model.repository.MembroRepository;
import br.com.unifacol.dizimo.model.service.EnderecoService;
import br.com.unifacol.dizimo.model.service.MembroService;
import br.com.unifacol.dizimo.model.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class MenuMembro {
    private EntityManager manager = JPAUtil.getEntityManager();
    private MembroRepository memberRepository = new MembroRepository(manager);
    private MembroService membroService = new MembroService(memberRepository);
    private EnderecoRepository addressRepository = new EnderecoRepository(manager);
    private EnderecoService enderecoService = new EnderecoService(addressRepository);
    private BuscarConta buscarConta = new BuscarConta();

    public void cadastrarMembro() throws SQLException {
        String rua = JOptionPane.showInputDialog("Digite o nome da rua: ");
        Integer numero = Integer.parseInt(JOptionPane.showInputDialog("Digite o número do seu endereço: "));
        String bairro = JOptionPane.showInputDialog("Digite o nome do bairro: ");
        String cidade = JOptionPane.showInputDialog("Digite o nome da cidade: ");
        Estado estado = Estado.valueOf(JOptionPane.showInputDialog("Digite a sigla do estado (ex: SP): "));

        Endereco endereco = new Endereco(rua, numero, bairro, cidade, estado);

        enderecoService.cadastrar(endereco);

        String nome = JOptionPane.showInputDialog("Digite o seu nome completo: ");
        String cpf = JOptionPane.showInputDialog("Digite o seu CPF: ");
        String data = JOptionPane.showInputDialog("Digite a sua data de nascimento no formato dd/MM/yyyy: ");
        LocalDate dataDeNascimento = LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate dataAtual = LocalDate.now();
        Integer idade = Period.between(dataDeNascimento, dataAtual).getYears();
        Genero genero = Genero.valueOf(JOptionPane.showInputDialog("Digite o seu sexo (M ou F): "));
        String email = JOptionPane.showInputDialog("Digite o seu endereço de email: ");
        Integer senha = Integer.parseInt(JOptionPane.showInputDialog("Digite uma senha numérica de 6 digitos: "));


        Membro membro = new Membro(nome, cpf, idade, dataDeNascimento, genero, senha, email);
        membro.setEndereco(endereco);
        membroService.cadastrar(membro);
    }

    public void atualizarMembro() throws SQLException {
        String cpfAtual = JOptionPane.showInputDialog("Digite seu CPF: ");
        Integer senhaAtual = Integer.parseInt(JOptionPane.showInputDialog("Digite sua senha: "));

        JOptionPane.showMessageDialog(null,"Faça a atualização dos dados ");

        String rua = JOptionPane.showInputDialog("Digite o nome da rua: ");
        Integer numero = Integer.parseInt(JOptionPane.showInputDialog("Digite o número do seu endereço: "));
        String bairro = JOptionPane.showInputDialog("Digite o nome do bairro: ");
        String cidade = JOptionPane.showInputDialog("Digite o nome da cidade: ");
        Estado estado = Estado.valueOf(JOptionPane.showInputDialog("Digite a sigla do estado (ex: SP): "));

        Endereco endereco = new Endereco(rua, numero, bairro, cidade, estado);

        enderecoService.atualizar(endereco.getId(), endereco);

        String nome = JOptionPane.showInputDialog("Digite o seu nome completo: ");
        String cpf = JOptionPane.showInputDialog("Digite o seu CPF: ");
        String data = JOptionPane.showInputDialog("Digite a sua data de nascimento no formato dd/MM/yyyy: ");
        LocalDate dataDeNascimento = LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate dataAtual = LocalDate.now();
        Integer idade = Period.between(dataDeNascimento, dataAtual).getYears();
        Genero genero = Genero.valueOf(JOptionPane.showInputDialog("Digite o seu sexo (M ou F): "));
        String email = JOptionPane.showInputDialog("Digite o seu endereço de email: ");
        Integer senha = Integer.parseInt(JOptionPane.showInputDialog("Digite uma senha numérica de 6 digitos: "));


        Membro membro = new Membro(nome, cpf, idade, dataDeNascimento, genero, senha, email);
        membro.setEndereco(endereco);
        membroService.atualizar(cpfAtual,senhaAtual, membro);
    }

    public void excluir() throws SQLException{
        String cpfAtual = JOptionPane.showInputDialog("Digite seu CPF: ");
        Integer senhaAtual = Integer.parseInt(JOptionPane.showInputDialog("Digite sua senha: "));

        membroService.excluir(cpfAtual,senhaAtual);
    }

    public void listMembros() throws SQLException{
        String cpfAtual = JOptionPane.showInputDialog("Digite seu CPF: ");
        Integer senhaAtual = Integer.parseInt(JOptionPane.showInputDialog("Digite sua senha: "));

        membroService.listarMembroPorCPFESenha(cpfAtual,senhaAtual);
    }
}
