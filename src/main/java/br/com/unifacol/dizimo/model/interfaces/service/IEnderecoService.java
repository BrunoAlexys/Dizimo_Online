package br.com.unifacol.dizimo.model.interfaces.service;

import br.com.unifacol.dizimo.model.entities.Endereco;

import java.sql.SQLException;

public interface IEnderecoService {
    void cadastrar(Endereco endereco) throws SQLException;
    void atualizar(Long id, Endereco endereco) throws SQLException;
    void excluir(Long id)throws SQLException;
    void listEndereco() throws SQLException;
    void listarEnderecoID(Long id) throws SQLException;
}
