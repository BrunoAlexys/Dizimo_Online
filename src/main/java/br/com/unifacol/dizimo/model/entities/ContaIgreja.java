package br.com.unifacol.dizimo.model.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ContaIgreja extends Conta {

    @OneToOne
    private Igreja igreja;

    public ContaIgreja(Integer numeroDaConta, Integer senha, Igreja igreja) {
        super(numeroDaConta, senha);
        this.igreja = igreja;
    }

    public ContaIgreja(Integer numeroDaConta, Integer senha){
        super(numeroDaConta,senha);
    }

    @Override
    public String toString() {
        return "\nTitular: " + this.getIgreja().getNomeDaIgreja() + super.toString();
    }

}
