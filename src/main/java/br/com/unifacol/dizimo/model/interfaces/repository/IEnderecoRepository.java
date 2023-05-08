package br.com.unifacol.dizimo.model.interfaces.repository;

import br.com.unifacol.dizimo.model.entities.Endereco;

import java.sql.SQLException;
import java.util.List;

public interface IEnderecoRepository {
    void cadastrar(Endereco endereco) throws SQLException;
    void atualizar(Long id, Endereco endereco) throws SQLException;
    void excluir(Long id)throws SQLException;
    List<Endereco> listEndereco() throws SQLException;
    List<Endereco> listEnderecoPorID(Long id) throws SQLException;
}
