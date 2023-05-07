package br.com.unifacol.dizimo.model.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ChurchAccount extends Account {
    @OneToOne(cascade = {CascadeType.ALL})
    private Church church;

    public ChurchAccount(Integer accountNumber, Integer password, Church church) {
        super(accountNumber, password);
        this.church = church;
    }

    public ChurchAccount (Integer accountNumber, Integer password){
        super(accountNumber,password);
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
