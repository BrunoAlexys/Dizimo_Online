package br.com.unifacol.dizimo.model.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "igrejas")
@Getter
@Setter
@NoArgsConstructor
public class Igreja {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeDaIgreja;
    @Column(name = "cnpj", unique = true, nullable = false)
    private String cnpj;
    private String email;
    private Integer senha;
    private LocalDate dataDeFundacao;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Endereco endereco;
    private Boolean ativo;
    @OneToOne(fetch = FetchType.LAZY,mappedBy = "igreja")
    private ContaIgreja contaIgreja;

    public Igreja(String nomeDaIgreja, String cnpj, Integer senha, LocalDate dateOfBirth, String email) {
        this.nomeDaIgreja = nomeDaIgreja;
        this.cnpj = cnpj;
        this.email = email;
        this.senha = senha;
        this.dataDeFundacao = dateOfBirth;
        this.ativo = true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Igreja {\n");
        sb.append("  ID: ").append(id).append("\n");
        sb.append("  Nome da igreja: '").append(nomeDaIgreja).append("'\n");
        sb.append("  CNPJ: '").append(cnpj).append("'\n");
        sb.append("  email: '").append(email).append("'\n");
        sb.append("  Senha: ").append(senha).append("\n");
        sb.append("  Data de fundação: ").append(dataDeFundacao).append("\n");
        sb.append("  Endereço: ").append(endereco).append("\n");
        sb.append("}");
        return sb.toString();
    }

}
