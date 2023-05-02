package br.com.unifacol.dizimo.model.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "churchs")
@Getter
@Setter
@NoArgsConstructor
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

    public Church(String churchName, String cnpj, Integer password, LocalDate dateOfBirth, String email) {
        this.churchName = churchName;
        this.cnpj = cnpj;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.active = true;
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
