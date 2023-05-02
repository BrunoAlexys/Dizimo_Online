package br.com.unifacol.dizimo.model.entities;

import br.com.unifacol.dizimo.model.enums.Sex;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "members")
@Getter
@Setter
@NoArgsConstructor
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
    private AccountMember accountMember;

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
        sb.append("  memberAccount: ").append(accountMember.toString()).append("\n");
        sb.append("}");
        return sb.toString();
    }

}