package br.com.unifacol.dizimo.view;

import br.com.unifacol.dizimo.model.entities.Endereco;
import br.com.unifacol.dizimo.model.entities.Igreja;
import br.com.unifacol.dizimo.model.enums.Estado;
import br.com.unifacol.dizimo.model.repository.EnderecoRepository;
import br.com.unifacol.dizimo.model.repository.IgrejaRepository;
import br.com.unifacol.dizimo.model.service.EnderecoService;
import br.com.unifacol.dizimo.model.service.IgrejaService;
import br.com.unifacol.dizimo.model.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MenuIgreja {
    private EntityManager manager = JPAUtil.getEntityManager();
    private IgrejaRepository igrejaRepository = new IgrejaRepository(manager);
    private IgrejaService igrejaService = new IgrejaService(igrejaRepository);
    private EnderecoRepository enderecoRepository = new EnderecoRepository(manager);
    private EnderecoService enderecoService = new EnderecoService(enderecoRepository);

    public void cadastrarIgreja() throws SQLException {
        String rua = JOptionPane.showInputDialog("Digite o nome da rua: ");
        Integer numero = Integer.parseInt(JOptionPane.showInputDialog("Digite o número do seu endereço: "));
        String bairro = JOptionPane.showInputDialog("Digite o nome do bairro: ");
        String cidade = JOptionPane.showInputDialog("Digite o nome da cidade: ");
        Estado estado = Estado.valueOf(JOptionPane.showInputDialog("Digite a sigla do estado (ex: SP): "));

        Endereco endereco = new Endereco(rua, numero, bairro, cidade, estado);

        enderecoService.cadastrar(endereco);

        String nomeDaIgreja = JOptionPane.showInputDialog("Digite o nome da igreja: ");
        String cnpj = JOptionPane.showInputDialog("CNPJ: ");
        String data = JOptionPane.showInputDialog("Data de fundação: ") ;
        LocalDate dataDeFundacao = LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String email = JOptionPane.showInputDialog("Digite o email: ");
        Integer senha = Integer.parseInt(JOptionPane.showInputDialog("Digite a senha: "));

        Igreja igreja = new Igreja(nomeDaIgreja,cnpj,senha,dataDeFundacao,email);
        igreja.setEndereco(endereco);

        igrejaService.cadastrar(igreja);
    }

    public void updateChurch() throws SQLException{
        String cnpjAtual =  JOptionPane.showInputDialog("Digite o CNPJ: ");
        Integer senhaAtual = Integer.parseInt(JOptionPane.showInputDialog("Digite a senha atual: "));

        JOptionPane.showMessageDialog(null,"Faça a atualização dos dados ");

        String rua = JOptionPane.showInputDialog("Digite o nome da rua: ");
        Integer numero = Integer.parseInt(JOptionPane.showInputDialog("Digite o número do seu endereço: "));
        String bairro = JOptionPane.showInputDialog("Digite o nome do bairro: ");
        String cidade = JOptionPane.showInputDialog("Digite o nome da cidade: ");
        Estado estado = Estado.valueOf(JOptionPane.showInputDialog("Digite a sigla do estado (ex: SP): "));

        Endereco endereco = new Endereco(rua, numero, bairro, cidade, estado);

        enderecoService.atualizar(endereco.getId(), endereco);

        String nomeDaIgreja = JOptionPane.showInputDialog("Digite o nome da igreja: ");
        String cnpj = JOptionPane.showInputDialog("CNPJ: ");
        String data = JOptionPane.showInputDialog("Data de fundação: ") ;
        LocalDate dataDeFundacao = LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String email = JOptionPane.showInputDialog("Digite o email: ");
        Integer senha = Integer.parseInt(JOptionPane.showInputDialog("Digite a senha: "));

        Igreja igreja = new Igreja(nomeDaIgreja,cnpj,senha,dataDeFundacao,email);
        igreja.setEndereco(endereco);

        igrejaService.atualizar(cnpjAtual,senhaAtual, igreja);

    }

    public void deleteChurch () throws SQLException{
        String cnpjAtual =  JOptionPane.showInputDialog("Digite o CNPJ: ");
        Integer senhaAtual = Integer.parseInt(JOptionPane.showInputDialog("Digite a senha atual: "));

        igrejaService.excluir(cnpjAtual,senhaAtual);
    }

    public void listChurch () throws SQLException {
        String cnpjAtual =  JOptionPane.showInputDialog("Digite o CNPJ: ");
        Integer senhaAtual = Integer.parseInt(JOptionPane.showInputDialog("Digite a senha atual: "));

        igrejaService.listarIgrejaPorCNPJESenha(cnpjAtual,senhaAtual);
    }
}
