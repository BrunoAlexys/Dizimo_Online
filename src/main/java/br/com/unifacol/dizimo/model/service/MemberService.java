package br.com.unifacol.dizimo.model.service;

import br.com.unifacol.dizimo.model.entities.Member;
import br.com.unifacol.dizimo.model.interfaces.service.IMemberService;
import br.com.unifacol.dizimo.model.repository.AccountFinder;
import br.com.unifacol.dizimo.model.repository.MemberRepository;
import br.com.unifacol.dizimo.model.validator.ValidatorCPF;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class MemberService implements IMemberService {
    private ValidatorCPF validatorCPF = new ValidatorCPF();
    private MemberRepository memberRepository;
    private AccountFinder accountFinder = new AccountFinder();

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void create(Member member) throws SQLException {
        do {
            if (!validatorCPF.verificarCpf(member.getCpf())) {
                System.out.println("CPF inválido. Digite novamente.");
                member.setCpf(JOptionPane.showInputDialog("CPF: "));
            }
        } while (!validatorCPF.verificarCpf(member.getCpf()));

        while (member.getPassword() < 100000 || member.getPassword() > 999999) {
            System.out.println("A senha deve ter 6 dígitos!");
            System.out.print("Digite a senha (6 dígitos): ");
            member.setPassword(Integer.parseInt(JOptionPane.showInputDialog("Senha: ")));
        }
        memberRepository.create(member);
    }

    @Override
    public void update(String cpf, Integer password, Member member) throws SQLException {
       Member memberFound = accountFinder.searchMemberByCpfAndPassword(cpf,password);
       if (memberFound != null){
           do {

               if (!validatorCPF.verificarCpf(member.getCpf())) {
                   System.out.println("CPF inválido. Digite novamente.");
                   member.setCpf(JOptionPane.showInputDialog("CPF: "));
               }
           } while (!validatorCPF.verificarCpf(member.getCpf()));

           while (member.getPassword() < 100000 || member.getPassword() > 999999) {
               System.out.println("A senha deve ter 6 dígitos!");
               System.out.print("Digite a senha (6 dígitos): ");
               member.setPassword(Integer.parseInt(JOptionPane.showInputDialog("Senha: ")));
           }
           memberRepository.update(member);
       }
    }

    @Override
    public void delete(String cpf, Integer password) throws SQLException {
        memberRepository.delete(cpf,password);
    }

    @Override
    public void listMembers() throws SQLException {
        List<Member> members = memberRepository.listMembers();
        members.forEach(System.out::println);
    }

    @Override
    public void listMembersCpfAndPassword(String cpf, Integer password) throws SQLException {
        List<Member> members = memberRepository.listmembersCpfAndPassword(cpf,password);
        members.forEach(System.out::println);
    }
}
