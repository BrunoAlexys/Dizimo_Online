package br.com.unifacol.dizimo.model.entities;

import br.com.unifacol.dizimo.model.enums.Genero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "membros")
@Getter
@Setter
@NoArgsConstructor
public class Membro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "cpf", unique = true, nullable = false, length = 14)
    private String cpf;
    private String nome;
    private Integer idade;
    private LocalDate dataDeNascimento;
    private Genero sexo;
    private String email;
    private Integer senha;
    private Boolean ativo;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Endereco endereco;
    @OneToOne(fetch = FetchType.LAZY,mappedBy = "membro")
    private ContaMembro contaMembro;

    public Membro(String nome, String cpf, Integer idade, LocalDate dataDeNascimento, Genero sexo, Integer senha, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
        this.dataDeNascimento = dataDeNascimento;
        this.sexo = sexo;
        this.senha = senha;
        this.email = email;
        this.ativo = true;
    }

    public BigDecimal valorDaDoacao(){
        return this.getContaMembro().getSaldo();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Member {\n");
        sb.append("  ID: ").append(id).append("\n");
        sb.append("  CPF: '").append(cpf).append("'\n");
        sb.append("  nome: '").append(nome).append("'\n");
        sb.append("  Idade: ").append(idade).append("\n");
        sb.append("  data de nascimento: ").append(dataDeNascimento).append("\n");
        sb.append("  Sexo: ").append(sexo).append("\n");
        sb.append("  email: '").append(email).append("'\n");
        sb.append("  Senha: ").append(senha).append("\n");
        sb.append("  Endereço: ").append(endereco).append("\n");
        sb.append("}");
        return sb.toString();
    }

}