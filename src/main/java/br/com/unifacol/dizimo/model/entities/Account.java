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
public abstract class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(unique = true)
    private Integer accountNumber;
    private Integer password;
    private BigDecimal balance;
    private LocalDate accountOpeningDate;

    public Account(Integer accountNumber, Integer password) {
        this.accountNumber = accountNumber;
        this.password = password;
        this.balance = BigDecimal.ZERO;
        this.accountOpeningDate = LocalDate.now();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Account {\n");
        sb.append("  id: ").append(id).append("\n");
        sb.append("  accountNumber: ").append(accountNumber).append("\n");
        sb.append("  password: ").append(password).append("\n");
        sb.append("  balance: ").append(balance).append("\n");
        sb.append("  accountOpeningDate: ").append(accountOpeningDate).append("\n");
        sb.append("}");
        return sb.toString();
    }

}