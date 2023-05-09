package br.com.unifacol.dizimo.model.entities;

import br.com.unifacol.dizimo.model.enums.Genero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
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
    @OneToOne(cascade = CascadeType.ALL)
    private Endereco endereco;
    @OneToOne(mappedBy = "membro", cascade = CascadeType.ALL)
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Member {\n");
        sb.append("  id: ").append(id).append("\n");
        sb.append("  cpf: '").append(cpf).append("'\n");
        sb.append("  name: '").append(nome).append("'\n");
        sb.append("  age: ").append(idade).append("\n");
        sb.append("  dateOfBirth: ").append(dataDeNascimento).append("\n");
        sb.append("  sex: ").append(sexo).append("\n");
        sb.append("  email: '").append(email).append("'\n");
        sb.append("  password: ").append(senha).append("\n");
        sb.append("  active: ").append(ativo).append("\n");
        sb.append("  address: ").append(endereco).append("\n");
        sb.append("  memberAccount: ").append(contaMembro.toString()).append("\n");
        sb.append("}");
        return sb.toString();
    }

}