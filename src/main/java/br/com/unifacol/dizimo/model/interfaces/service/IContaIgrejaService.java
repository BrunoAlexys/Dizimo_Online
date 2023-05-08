package br.com.unifacol.dizimo.model.interfaces.service;


import br.com.unifacol.dizimo.model.entities.ContaIgreja;
import java.sql.SQLException;


public interface IContaIgrejaService {
    void cadastrar(ContaIgreja contaIgreja) throws SQLException;
    void atualizar(Integer numeroAtualDaConta, Integer senhaAtual, ContaIgreja contaIgreja) throws SQLException;
    void excluir(Integer numeroDaConta, Integer senha)throws SQLException;
    void listContaIgreja() throws SQLException;
    void listarContaIgrejaPorNumeroESenha(Integer numeroDaConta, Integer senha) throws SQLException;
}
