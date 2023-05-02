package br.com.unifacol.dizimo.model.entities;

import br.com.unifacol.dizimo.model.enums.Sex;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "cpf", unique = true, nullable = false, length = 14)
    private String cpf;
    private String name;
    private Integer age;
    private LocalDate dateOfBirth;
    private Sex sex;
    private String email;
    private Integer password;
    private Boolean active;
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "member_account_id")
    private MemberAccount memberAccount;

    public Member(String name, String cpf, Integer age, LocalDate dateOfBirth, Sex sex, Integer password, String email) {
        this.name = name;
        this.cpf = cpf;
        this.age = age;
        this.dateOfBirth = dateOfBirth;
        this.sex = sex;
        this.password = password;
        this.email = email;
        this.active = true;
    }

    public Member() {
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public MemberAccount getMemberAccount() {
        return memberAccount;
    }

    public void setMemberAccount(MemberAccount memberAccount) {
        this.memberAccount = memberAccount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Member {\n");
        sb.append("  id: ").append(id).append("\n");
        sb.append("  cpf: '").append(cpf).append("'\n");
        sb.append("  name: '").append(name).append("'\n");
        sb.append("  age: ").append(age).append("\n");
        sb.append("  dateOfBirth: ").append(dateOfBirth).append("\n");
        sb.append("  sex: ").append(sex).append("\n");
        sb.append("  email: '").append(email).append("'\n");
        sb.append("  password: ").append(password).append("\n");
        sb.append("  active: ").append(active).append("\n");
        sb.append("  address: ").append(address).append("\n");
        sb.append("  memberAccount: ").append(memberAccount.toString()).append("\n");
        sb.append("}");
        return sb.toString();
    }

}