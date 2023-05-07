package br.com.unifacol.dizimo.view;

import br.com.unifacol.dizimo.model.entities.AccountMember;
import br.com.unifacol.dizimo.model.entities.Address;
import br.com.unifacol.dizimo.model.entities.Member;
import br.com.unifacol.dizimo.model.enums.Sex;
import br.com.unifacol.dizimo.model.enums.State;
import br.com.unifacol.dizimo.model.repository.AccountFinder;
import br.com.unifacol.dizimo.model.repository.AddressRepository;
import br.com.unifacol.dizimo.model.repository.MemberAccountRepository;
import br.com.unifacol.dizimo.model.repository.MemberRepository;
import br.com.unifacol.dizimo.model.service.AddressService;
import br.com.unifacol.dizimo.model.service.MemberAccountService;
import br.com.unifacol.dizimo.model.service.MemberService;
import br.com.unifacol.dizimo.model.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class MenuMember {
    private EntityManager manager = JPAUtil.getEntityManager();
    private MemberRepository memberRepository = new MemberRepository(manager);
    private MemberService memberService = new MemberService(memberRepository);
    private AddressRepository addressRepository = new AddressRepository(manager);
    private AddressService addressService = new AddressService(addressRepository);
    private AccountFinder accountFinder = new AccountFinder();

    public void signUpMember() throws SQLException {
        String street = JOptionPane.showInputDialog("Digite o nome da rua: ");
        Integer number = Integer.parseInt(JOptionPane.showInputDialog("Digite o número do seu endereço: "));
        String district = JOptionPane.showInputDialog("Digite o nome do bairro: ");
        String city = JOptionPane.showInputDialog("Digite o nome da cidade: ");
        State state = State.valueOf(JOptionPane.showInputDialog("Digite a sigla do estado (ex: SP): "));

        Address address = new Address(street, number, district, city, state);

        addressService.create(address);

        String name = JOptionPane.showInputDialog("Digite o seu nome completo: ");
        String cpf = JOptionPane.showInputDialog("Digite o seu CPF: ");
        String date = JOptionPane.showInputDialog("Digite a sua data de nascimento no formato dd/MM/yyyy: ");
        LocalDate dateOfBirth = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate currentDate = LocalDate.now();
        Integer age = Period.between(dateOfBirth, currentDate).getYears();
        Sex sex = Sex.valueOf(JOptionPane.showInputDialog("Digite o seu sexo (M ou F): "));
        String email = JOptionPane.showInputDialog("Digite o seu endereço de email: ");
        Integer password = Integer.parseInt(JOptionPane.showInputDialog("Digite uma senha numérica de 6 digitos: "));


        Member membro = new Member(name, cpf, age, dateOfBirth, sex, password, email);
        membro.setAddress(address);
        memberService.create(membro);
    }

    public void updateMember() throws SQLException {
        String cpfCurrent = JOptionPane.showInputDialog("Digite seu CPF: ");
        Integer passwordCurrent = Integer.parseInt(JOptionPane.showInputDialog("Digite sua senha: "));

        JOptionPane.showMessageDialog(null,"Faça a atualização dos dados ");

        String street = JOptionPane.showInputDialog("Digite o nome da rua: ");
        Integer number = Integer.parseInt(JOptionPane.showInputDialog("Digite o número do seu endereço: "));
        String district = JOptionPane.showInputDialog("Digite o nome do bairro: ");
        String city = JOptionPane.showInputDialog("Digite o nome da cidade: ");
        State state = State.valueOf(JOptionPane.showInputDialog("Digite a sigla do estado (ex: SP): "));

        Address address = new Address(street, number, district, city, state);

        addressService.update(address.getId(),address);

        String name = JOptionPane.showInputDialog("Digite o seu nome completo: ");
        String cpf = JOptionPane.showInputDialog("Digite o seu CPF: ");
        String date = JOptionPane.showInputDialog("Digite a sua data de nascimento no formato dd/MM/yyyy: ");
        LocalDate dateOfBirth = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate currentDate = LocalDate.now();
        Integer age = Period.between(dateOfBirth, currentDate).getYears();
        Sex sex = Sex.valueOf(JOptionPane.showInputDialog("Digite o seu sexo (M ou F): "));
        String email = JOptionPane.showInputDialog("Digite o seu endereço de email: ");
        Integer password = Integer.parseInt(JOptionPane.showInputDialog("Digite uma senha numérica de 6 digitos: "));

        Member member = new Member(name, cpf, age, dateOfBirth, sex, password, email);
        member.setAddress(address);
        memberService.update(cpfCurrent,passwordCurrent,member);
    }

    public void  delete() throws SQLException{
        String cpfCurrent = JOptionPane.showInputDialog("Digite seu CPF: ");
        Integer passwordCurrent = Integer.parseInt(JOptionPane.showInputDialog("Digite sua senha: "));

        memberService.delete(cpfCurrent,passwordCurrent);
    }

    public void listMembers() throws SQLException{
        String cpfCurrent = JOptionPane.showInputDialog("Digite seu CPF: ");
        Integer passwordCurrent = Integer.parseInt(JOptionPane.showInputDialog("Digite sua senha: "));

        memberService.listMembersCpfAndPassword(cpfCurrent,passwordCurrent);
    }
}
