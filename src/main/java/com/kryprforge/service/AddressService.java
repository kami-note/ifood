package com.kryprforge.service;

import com.kryprforge.models.Address;
import com.kryprforge.dao.AddressDAO;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;

public class AddressService {

    private final AddressDAO addressDAO;
    private final Api api;

    public AddressService(AddressDAO addressDAO) {
        this.addressDAO = addressDAO;
        this.api = new Api();
    }

    public Address saveAddress(String cep) throws IOException, ParseException {
        Address address = api.fetchAddress(cep);
        addressDAO.save(address);
        return address;
    }
    public Address fetchAddress(String cep) throws IOException, ParseException {
        return api.fetchAddress(cep); // Retorna apenas o endereço
    }

}
