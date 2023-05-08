package br.com.unifacol.dizimo.model.interfaces.repository;

import br.com.unifacol.dizimo.model.entities.ContaIgreja;

import java.sql.SQLException;
import java.util.List;

public interface IContaIgrejaRepository {
    void cadastrar(ContaIgreja contaIgreja) throws SQLException;
    void atualizar(Integer numeroDaContaAtual, Integer senhaAtual, ContaIgreja contaIgreja) throws SQLException;
    void excluir(Integer numeroDaConta, Integer senha)throws SQLException;
    List<ContaIgreja> listContaIgreja() throws SQLException;
    List<ContaIgreja> listContaIgrejaPorNumeroESenha(Integer numeroDaConta, Integer senha) throws SQLException;
}
