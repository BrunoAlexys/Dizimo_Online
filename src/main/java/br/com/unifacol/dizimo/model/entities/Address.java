package br.com.unifacol.dizimo.model.entities;

import br.com.unifacol.dizimo.model.enums.State;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name = "adrress")
@Getter
@Setter
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private Integer number;
    private String district;
    private String city;
    private State state;

    public Address(String street, Integer number, String district, String city, State state) {
        this.street = street;
        this.number = number;
        this.district = district;
        this.city = city;
        this.state = state;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Address {\n");
        sb.append("  id: ").append(id).append("\n");
        sb.append("  street: '").append(street).append("'\n");
        sb.append("  number: ").append(number).append("\n");
        sb.append("  district: '").append(district).append("'\n");
        sb.append("  city: '").append(city).append("'\n");
        sb.append("  state: ").append(state).append("\n");
        sb.append("}");
        return sb.toString();
    }

}
