package br.com.unifacol.dizimo.model.entities;

import br.com.unifacol.dizimo.model.enums.State;
import javax.persistence.*;

@Entity
@Table(name = "adrress")
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

    public Address() {
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
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
