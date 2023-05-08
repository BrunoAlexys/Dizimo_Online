package br.com.unifacol.dizimo.model.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Setter
public abstract class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(unique = true)
    private Integer numeroDaConta;
    private Integer senha;
    private BigDecimal saldo;
    private LocalDate dataDeAberturaDaConta;

    protected Conta(Integer numeroDaConta, Integer senha) {
        this.numeroDaConta = numeroDaConta;
        this.senha = senha;
        this.saldo = BigDecimal.ZERO;
        this.dataDeAberturaDaConta = LocalDate.now();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Conta {\n");
        sb.append("  id: ").append(id).append("\n");
        sb.append("  accountNumber: ").append(numeroDaConta).append("\n");
        sb.append("  password: ").append(senha).append("\n");
        sb.append("  balance: ").append(saldo).append("\n");
        sb.append("  accountOpeningDate: ").append(dataDeAberturaDaConta).append("\n");
        sb.append("}");
        return sb.toString();
    }

}