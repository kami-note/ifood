package com.kryprforge.database.service;

import com.kryprforge.database.repository.Address;
import com.kryprforge.database.dao.AddressDAO;
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

}
