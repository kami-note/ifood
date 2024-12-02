package com.kryprforge.ui.screens;

import com.kryprforge.context.GlobalInfos;
import com.kryprforge.models.Address;
import com.kryprforge.service.AddressService;
import com.kryprforge.dao.AddressDAO;
import com.kryprforge.ui.components.InputField;
import com.kryprforge.ui.components.Header;
import com.kryprforge.ui.utils.CLIUtils;
import com.kryprforge.ui.components.Table;
import org.apache.hc.core5.http.ParseException;
import org.fusesource.jansi.Ansi;

import java.io.IOException;
import java.util.List;

public class AddressScreen {

    private final AddressService addressService;
    private final AddressDAO addressDAO;
    private final GlobalInfos globalInfos;
    private boolean inAddressMenu = true;

    public AddressScreen(AddressService addressService, AddressDAO addressDAO, GlobalInfos globalInfos) {
        this.addressService = addressService;
        this.addressDAO = addressDAO;
        this.globalInfos = globalInfos;
    }

    public boolean render() {
        Header header = new Header("Gerenciamento de Endereços", 50);
        header.render();

        while (inAddressMenu) {
            System.out.println("[1] Adicionar Endereço");
            System.out.println("[2] Selecionar Endereço");
            System.out.println("[0] Voltar ao Menu Principal");

            InputField<Integer> menuField = new InputField<>(
                    "Escolha uma opção",
                    Integer::parseInt,
                    "Entrada inválida! Por favor, insira um número."
            );

            int choice = menuField.getUserInput();

            switch (choice) {
                case 1 -> addAddress();
                case 2 -> selectAddress();
                case 3 -> deleteAddress();
                case 0 -> {
                    return false;
                }
                default -> System.out.println(CLIUtils.color("Opção inválida! Tente novamente.", Ansi.Color.RED));
            }
        }

        return false;
    }

    private void listAndDisplayAddresses() {
        List<Address> addresses = addressDAO.findAll();

        if (addresses.isEmpty()) {
            System.out.println(CLIUtils.color("Nenhum endereço encontrado.", Ansi.Color.YELLOW));
        } else {
            System.out.println(CLIUtils.color("Endereços cadastrados:", Ansi.Color.CYAN));

            String[] headers = {"ID", "Rua", "Número", "Bairro", "Cidade", "Estado", "CEP", "Tipo"};

            List<String[]> rows = addresses.stream()
                    .map(address -> new String[]{
                            String.valueOf(address.getId()),
                            address.getStreet() != null ? address.getStreet() : "N/A",
                            address.getNumber() != null ? address.getNumber() : "N/A",
                            address.getNeighborhood() != null ? address.getNeighborhood() : "N/A",
                            address.getCity() != null ? address.getCity() : "N/A",
                            address.getState() != null ? address.getState() : "N/A",
                            address.getPostalCode() != null ? address.getPostalCode() : "N/A",
                            address.getAddressType() != null ? address.getAddressType() : "N/A"
                    })
                    .toList();

            Table table = new Table(headers, rows);
            table.render();
        }
    }

    public void addAddress() {
        InputField<String> cepField = new InputField<>("Digite o CEP:", s -> s, "CEP inválido!");
        String cep = cepField.getUserInput();

        try {
            Address address = addressService.fetchAddress(cep);

            System.out.println("Endereço encontrado:");
            System.out.printf("Rua: %s, Bairro: %s, Cidade: %s, Estado: %s, CEP: %s%n",
                    address.getStreet(), address.getNeighborhood(), address.getCity(),
                    address.getState(), address.getPostalCode());

            InputField<String> numberField = new InputField<>("Digite o número do endereço:", s -> s, "Número inválido!");
            String number = numberField.getUserInput();
            address.setNumber(number);

            InputField<String> streetField = new InputField<>("Digite o Nome da rua:", s -> s, "Nome inválido!");
            String nameStreet = streetField.getUserInput();
            address.setStreet(nameStreet);

            InputField<String> typeField = new InputField<>("Digite o tipo do endereço (Residencial, Comercial, etc.):", s -> s, "Tipo inválido!");
            String addressType = typeField.getUserInput();
            address.setAddressType(addressType);

            InputField<String> neighborhoodField = new InputField<>("Digite o nome do barrio:", s -> s, "Tipo inválido!");
            String addressNeighborhood = neighborhoodField.getUserInput();
            address.setNeighborhood(addressNeighborhood);

            globalInfos.setDeliveryAddress(address);
            System.out.println(CLIUtils.color("Endereço salvo temporariamente com sucesso!", Ansi.Color.GREEN));
            globalInfos.setIdAddressCurrent(addressDAO.save(globalInfos.getDeliveryAddress()));
        } catch (IOException | ParseException e) {
            System.out.println(CLIUtils.color("Erro ao buscar endereço: " + e.getMessage(), Ansi.Color.RED));
        }
    }

    private void deleteAddress() {
        listAndDisplayAddresses();

        InputField<Long> idField = new InputField<>("Digite o ID do endereço a ser removido:", Long::parseLong, "ID inválido!");
        Long id = idField.getUserInput();

        addressDAO.delete(id);
        System.out.println(CLIUtils.color("Endereço removido com sucesso!", Ansi.Color.GREEN));
    }

    private void selectAddress() {
        listAndDisplayAddresses();

        InputField<Long> idField = new InputField<>("Digite o ID do endereço a ser selecionado:", Long::parseLong, "ID inválido!");
        Long selectedId = idField.getUserInput();

        Address selectedAddress = addressDAO.findById(selectedId);
        if (selectedAddress != null) {
            globalInfos.setIdAddressCurrent(selectedAddress.getId());
            System.out.println(CLIUtils.color("Endereço selecionado com sucesso!", Ansi.Color.GREEN));
            inAddressMenu = false;
        } else {
            System.out.println(CLIUtils.color("Endereço não encontrado!", Ansi.Color.RED));
        }
    }

    private String addressDetails(Address address) {
        return String.format("ID: %d, Rua: %s, Número: %s, Bairro: %s, Cidade: %s, Estado: %s, CEP: %s, Tipo: %s",
                address.getId(),
                address.getStreet(),
                address.getNumber() != null ? address.getNumber() : "N/A",
                address.getNeighborhood(),
                address.getCity(),
                address.getState(),
                address.getPostalCode(),
                address.getAddressType() != null ? address.getAddressType() : "N/A");
    }
}

