package br.com.unifacol.dizimo.model.service;

import br.com.unifacol.dizimo.model.entities.Endereco;
import br.com.unifacol.dizimo.model.interfaces.service.IEnderecoService;
import br.com.unifacol.dizimo.model.repository.EnderecoRepository;

import java.sql.SQLException;
import java.util.List;

public class EnderecoService implements IEnderecoService {
    private EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository) {
        super();
        this.enderecoRepository = enderecoRepository;
    }

    @Override
    public void cadastrar(Endereco endereco) throws SQLException {
        enderecoRepository.cadastrar(endereco);
    }

    @Override
    public void atualizar(Long id, Endereco endereco) throws SQLException {
        enderecoRepository.atualizar(id, endereco);
    }

    @Override
    public void excluir(Long id) throws SQLException {
        enderecoRepository.excluir(id);
    }

    @Override
    public void listEndereco() throws SQLException {
        List<Endereco> enderecoList = enderecoRepository.listEndereco();
        enderecoList.forEach(System.out::println);
    }

    @Override
    public void listarEnderecoID(Long id) throws SQLException {
        List<Endereco> enderecoList = enderecoRepository.listEnderecoPorID(id);
        enderecoList.forEach(System.out::println);
    }
}
