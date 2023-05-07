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
public class AccountMember extends Account {
    @OneToOne
    private Member member;

    public AccountMember(Integer accountNumber, Integer password, Member member) {
        super(accountNumber, password);
        this.member = member;
    }

    public AccountMember(Integer accountNumber, Integer password) {
        super(accountNumber,password);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}