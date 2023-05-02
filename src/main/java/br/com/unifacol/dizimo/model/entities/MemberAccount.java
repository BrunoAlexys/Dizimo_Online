package br.com.unifacol.dizimo.model.entities;


import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "member_account")
public class MemberAccount extends Account {
    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public MemberAccount(Integer accountNumber, Integer password, Member member) {
        super(accountNumber, password);
        this.member = member;
    }

    public MemberAccount() {
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}