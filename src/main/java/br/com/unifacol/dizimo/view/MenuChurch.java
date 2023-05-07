package br.com.unifacol.dizimo.view;

import br.com.unifacol.dizimo.model.entities.Address;
import br.com.unifacol.dizimo.model.entities.Church;
import br.com.unifacol.dizimo.model.enums.State;
import br.com.unifacol.dizimo.model.repository.AddressRepository;
import br.com.unifacol.dizimo.model.repository.ChurchRepository;
import br.com.unifacol.dizimo.model.service.AddressService;
import br.com.unifacol.dizimo.model.service.ChurchService;
import br.com.unifacol.dizimo.model.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MenuChurch {
    private EntityManager manager = JPAUtil.getEntityManager();
    private ChurchRepository churchRepository = new ChurchRepository(manager);
    private ChurchService churchService = new ChurchService(churchRepository);
    private AddressRepository addressRepository = new AddressRepository(manager);
    private AddressService addressService = new AddressService(addressRepository);

    public void singUpChurch() throws SQLException {
        String street = JOptionPane.showInputDialog("Digite o nome da rua: ");
        Integer number = Integer.parseInt(JOptionPane.showInputDialog("Digite o número do seu endereço: "));
        String district = JOptionPane.showInputDialog("Digite o nome do bairro: ");
        String city = JOptionPane.showInputDialog("Digite o nome da cidade: ");
        State state = State.valueOf(JOptionPane.showInputDialog("Digite a sigla do estado (ex: SP): "));

        Address address = new Address(street, number, district, city, state);

        addressService.create(address);

        String churchName = JOptionPane.showInputDialog("Digite o nome da igreja: ");
        String cnpj = JOptionPane.showInputDialog("CNPJ: ");
        String date = JOptionPane.showInputDialog("Data de fundação: ") ;
        LocalDate dateOfBirth = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String email = JOptionPane.showInputDialog("Digite o email: ");
        Integer password = Integer.parseInt(JOptionPane.showInputDialog("Digite a senha: "));

        Church church = new Church(churchName,cnpj,password,dateOfBirth,email);
        church.setAddress(address);

        churchService.create(church);
    }

    public void updateChurch() throws SQLException{
        String cnpjActual =  JOptionPane.showInputDialog("Digite o CNPJ: ");
        Integer passwordActual = Integer.parseInt(JOptionPane.showInputDialog("Digite a senha atual: "));

        JOptionPane.showMessageDialog(null,"Faça a atualização dos dados ");

        String street = JOptionPane.showInputDialog("Digite o nome da rua: ");
        Integer number = Integer.parseInt(JOptionPane.showInputDialog("Digite o número do seu endereço: "));
        String district = JOptionPane.showInputDialog("Digite o nome do bairro: ");
        String city = JOptionPane.showInputDialog("Digite o nome da cidade: ");
        State state = State.valueOf(JOptionPane.showInputDialog("Digite a sigla do estado (ex: SP): "));

        Address address = new Address(street, number, district, city, state);

        addressService.update(address.getId(),address);

        String churchName = JOptionPane.showInputDialog("Digite o nome da igreja: ");
        String cnpj = JOptionPane.showInputDialog("CNPJ: ");
        String date = JOptionPane.showInputDialog("Data de fundação: ") ;
        LocalDate dateOfBirth = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String email = JOptionPane.showInputDialog("Digite o email: ");
        Integer password = Integer.parseInt(JOptionPane.showInputDialog("Digite a senha: "));

        Church church = new Church(churchName,cnpj,password,dateOfBirth,email);
        church.setAddress(address);

        churchService.update(cnpjActual,passwordActual,church);

    }

    public void deleteChurch () throws SQLException{
        String cnpjActual =  JOptionPane.showInputDialog("Digite o CNPJ: ");
        Integer passwordActual = Integer.parseInt(JOptionPane.showInputDialog("Digite a senha atual: "));

        churchService.delete(cnpjActual,passwordActual);
    }

    public void listChurch () throws SQLException {
        String cnpjActual =  JOptionPane.showInputDialog("Digite o CNPJ: ");
        Integer passwordActual = Integer.parseInt(JOptionPane.showInputDialog("Digite a senha atual: "));

        churchService.listChurchCnpjAndPassword(cnpjActual,passwordActual);
    }
}
