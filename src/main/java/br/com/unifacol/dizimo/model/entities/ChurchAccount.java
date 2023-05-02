package br.com.unifacol.dizimo.model.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "church_account")
public class ChurchAccount extends Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @OneToOne
    @JoinColumn(name = "church_id")
    private Church church;

    public ChurchAccount(Integer accountNumber, Integer password, Church church) {
        super(accountNumber, password);
        this.church = church;
    }

    public ChurchAccount() {
    }

    public Church getChurch() {
        return church;
    }

    public void setChurch(Church church) {
        this.church = church;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
