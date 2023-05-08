package br.com.unifacol.dizimo.model.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ContaMembro extends Conta {
    @OneToOne
    private Membro membro;

    public ContaMembro(Integer accountNumber, Integer password, Membro membro) {
        super(accountNumber, password);
        this.membro = membro;
    }

    public ContaMembro(Integer accountNumber, Integer password) {
        super(accountNumber,password);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}