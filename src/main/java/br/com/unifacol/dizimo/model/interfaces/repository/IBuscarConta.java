package br.com.unifacol.dizimo.model.interfaces.repository;

import br.com.unifacol.dizimo.model.entities.*;

import java.sql.SQLException;

public interface IBuscarConta {
    Membro pesquisarMembroPorCpfESenha(String cpf, Integer senha) throws SQLException;
    Membro pesquisarMembroPorEmailESenha(String email, Integer senha) throws SQLException;
    Igreja pesquisarPorCNPJESenha(String cnpj, Integer senha) throws SQLException;
    Igreja pesquisarPorEmailESenha(String email, Integer senha) throws SQLException;
    Endereco pesquisarEndereco(Long id) throws SQLException;
    ContaMembro pesquisarContaMembro(Integer numeroDaConta, Integer senha) throws SQLException;
    ContaMembro pesquisarContaMembroPorNumero(Integer numeroDaConta) throws SQLException;
    ContaIgreja pesquisarContaIgreja(Integer numeroDaConta, Integer senha) throws SQLException;
    ContaIgreja pesquisarContaIgrejaPorNumero(Integer numeroDaConta) throws SQLException;
}
