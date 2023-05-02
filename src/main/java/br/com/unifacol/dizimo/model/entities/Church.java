package br.com.unifacol.dizimo.model.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "churchs")
public class Church {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String churchName;
    @Column(name = "cnpj", unique = true, nullable = false)
    private String cnpj;
    private String email;
    private Integer password;
    private LocalDate dateOfBirth;
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
    private Boolean active;
    @OneToOne(cascade = CascadeType.ALL)
    private ChurchAccount churchAccount;

    public Church(String churchName, String cnpj, Integer password, LocalDate dateOfBirth, Address address, String email) {
        this.churchName = churchName;
        this.cnpj = cnpj;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.active = true;
    }

    public Church() {
    }

    public String getChurchName() {
        return churchName;
    }

    public void setChurchName(String churchName) {
        this.churchName = churchName;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPassword() {
        return password;
    }

    public void setPassword(Integer password) {
        this.password = password;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public ChurchAccount getChurchAccount() {
        return churchAccount;
    }

    public void setChurchAccount(ChurchAccount churchAccount) {
        this.churchAccount = churchAccount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Church {\n");
        sb.append("  id: ").append(id).append("\n");
        sb.append("  churchName: '").append(churchName).append("'\n");
        sb.append("  cnpj: '").append(cnpj).append("'\n");
        sb.append("  email: '").append(email).append("'\n");
        sb.append("  password: ").append(password).append("\n");
        sb.append("  dateOfBirth: ").append(dateOfBirth).append("\n");
        sb.append("  address: ").append(address).append("\n");
        sb.append("  active: ").append(active).append("\n");
        sb.append("  churchAccount: ").append(churchAccount.toString()).append("\n");
        sb.append("}");
        return sb.toString();
    }

}
