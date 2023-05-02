package br.com.unifacol.dizimo.model.test;

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

public class Teste {
    private static EntityManager manager = JPAUtil.getEntityManager();
    private static MemberRepository memberRepository = new MemberRepository(manager);
    private static MemberService memberService = new MemberService(memberRepository);
    private static AddressRepository addressRepository = new AddressRepository(manager);
    private static AddressService addressService = new AddressService(addressRepository);
    private static AccountFinder accountFinder = new AccountFinder();
    private static MemberAccountRepository memberAccountRepository = new MemberAccountRepository();
    private static MemberAccountService memberAccountService = new MemberAccountService(memberAccountRepository);

    public static void main(String[] args) throws SQLException {

    }

    public static void signUpMember() throws SQLException {
        String street = JOptionPane.showInputDialog("Rua: ");
        Integer number = Integer.parseInt(JOptionPane.showInputDialog("Numero: "));
        String district = JOptionPane.showInputDialog("Bairro: ");
        String city = JOptionPane.showInputDialog("Cidade: ");
        State state = State.valueOf(JOptionPane.showInputDialog("Estado: "));

        Address address = new Address(street, number, district, city, state);

        addressService.create(address);

        String name = JOptionPane.showInputDialog("Nome: ");
        String cpf = JOptionPane.showInputDialog("CPF: ");
        String date = JOptionPane.showInputDialog("Data de Nascimento (dd/MM/yyyy): ");
        LocalDate dateOfBirth = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate currentDate = LocalDate.now();
        Integer age = Period.between(dateOfBirth, currentDate).getYears();
        Sex sex = Sex.valueOf(JOptionPane.showInputDialog("Sexo: "));
        String email = JOptionPane.showInputDialog("Email: ");
        Integer password = Integer.parseInt(JOptionPane.showInputDialog("Senha: "));

        Member membro = new Member(name, cpf, age, dateOfBirth, sex, password, email);
        membro.setAddress(address);
        memberService.create(membro);
    }

    public static void registerMemberAccount() throws SQLException {
        String cpf = JOptionPane.showInputDialog("CPF: ");
        Integer password = Integer.parseInt(JOptionPane.showInputDialog("Password: "));
        Member memberFound = accountFinder.searchMemberByCpfAndPassword(cpf, password);
        if (memberFound != null) {
            Integer numberAccount = Integer.parseInt(JOptionPane.showInputDialog("Numero da conta: "));
            Integer passwordAccount = Integer.parseInt(JOptionPane.showInputDialog("Password: "));
            AccountMember accountMember = new AccountMember(numberAccount, passwordAccount, memberFound);
            memberAccountService.create(accountMember);
        }
    }

    public static void deposit() throws SQLException {
        Integer numberAccount = Integer.parseInt(JOptionPane.showInputDialog("Numero da conta: "));
        Integer passwordAccount = Integer.parseInt(JOptionPane.showInputDialog("Password: "));
        Double amount = Double.valueOf(JOptionPane.showInputDialog("Digite o valor do deposito: "));
        BigDecimal amountDeposit = new BigDecimal(amount);
        memberAccountService.deposit(numberAccount, passwordAccount, amountDeposit);
    }

    public static void withdraw() throws SQLException {
        Integer numberAccount = Integer.parseInt(JOptionPane.showInputDialog("Numero da conta: "));
        Integer passwordAccount = Integer.parseInt(JOptionPane.showInputDialog("Password: "));
        Double amount = Double.valueOf(JOptionPane.showInputDialog("Digite o valor do saque: "));
        BigDecimal amountWithdraw = new BigDecimal(amount);
        memberAccountService.withdraw(numberAccount, passwordAccount, amountWithdraw);

    }

    public static void transfer() throws SQLException {
        Integer sourceAccountNumber = Integer.parseInt(JOptionPane.showInputDialog("Digite o numero da sua conta: "));
        Integer password = Integer.parseInt(JOptionPane.showInputDialog("Digite sua senha: "));
        Integer destinationAccountNumber = Integer.parseInt(JOptionPane.showInputDialog("Digite o numero da conta do destinatario: "));
        Double transferAmount = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor da transferencia: "));
        BigDecimal transfer = new BigDecimal(transferAmount);
        memberAccountService.transfer(sourceAccountNumber,destinationAccountNumber,password,transfer);
    }
}
