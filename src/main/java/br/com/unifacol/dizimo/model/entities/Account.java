package br.com.unifacol.dizimo.model.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "accounts")
@Inheritance(strategy = InheritanceType.JOINED)
public class Account {
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

    public Account() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Integer getPassword() {
        return password;
    }

    public void setPassword(Integer password) {
        this.password = password;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public LocalDate getAccountOpeningDate() {
        return accountOpeningDate;
    }

    public void setAccountOpeningDate(LocalDate accountOpeningDate) {
        this.accountOpeningDate = accountOpeningDate;
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