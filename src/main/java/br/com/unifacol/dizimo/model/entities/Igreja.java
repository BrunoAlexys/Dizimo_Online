package br.com.unifacol.dizimo.model.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "churchs")
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
    @OneToOne(cascade = CascadeType.ALL)
    private Endereco endereco;
    private Boolean ativo;
    @OneToOne(cascade = CascadeType.ALL)
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
        sb.append("Church {\n");
        sb.append("  id: ").append(id).append("\n");
        sb.append("  churchName: '").append(nomeDaIgreja).append("'\n");
        sb.append("  cnpj: '").append(cnpj).append("'\n");
        sb.append("  email: '").append(email).append("'\n");
        sb.append("  password: ").append(senha).append("\n");
        sb.append("  dateOfBirth: ").append(dataDeFundacao).append("\n");
        sb.append("  address: ").append(endereco).append("\n");
        sb.append("  active: ").append(ativo).append("\n");
        sb.append("  churchAccount: ").append(contaIgreja.toString()).append("\n");
        sb.append("}");
        return sb.toString();
    }

}
