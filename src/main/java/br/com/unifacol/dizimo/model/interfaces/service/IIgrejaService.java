package br.com.unifacol.dizimo.model.interfaces.service;


import br.com.unifacol.dizimo.model.entities.Igreja;
import java.sql.SQLException;


public interface IIgrejaService {
    void cadastrar(Igreja igreja) throws SQLException;
    void atualizar(String cnpj, Integer senha, Igreja igreja) throws SQLException;
    void excluir(String cnpj, Integer senha)throws SQLException;
    void listIgreja() throws SQLException;
    void listarIgrejaPorCNPJESenha(String cnpj, Integer senha) throws SQLException;
}
