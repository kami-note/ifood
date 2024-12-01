package com.kryprforge.database.service;

import com.google.gson.Gson;
import java.io.IOException;

import com.kryprforge.database.repository.Address;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;

public class Api {

    private static final String VIACEP_URL = "https://viacep.com.br/ws/";

    public Address fetchAddress(String cep) throws IOException, ParseException {
        String url = VIACEP_URL + cep + "/json";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet(url);

        CloseableHttpResponse response = httpClient.execute(request);
        HttpEntity entity = response.getEntity();
        String json = EntityUtils.toString(entity);

        Gson gson = new Gson();
        ViaCEPResponse viaCEPResponse = gson.fromJson(json, ViaCEPResponse.class);

        return new Address(
                viaCEPResponse.getLogradouro(),
                "",
                viaCEPResponse.getBairro(),
                viaCEPResponse.getLocalidade(),
                viaCEPResponse.getUf(),
                cep,
                "Residential"
        );
    }

    private class ViaCEPResponse {
        private String logradouro;
        private String bairro;
        private String localidade;
        private String uf;

        public String getLogradouro() {
            return logradouro;
        }

        public void setLogradouro(String logradouro) {
            this.logradouro = logradouro;
        }

        public String getBairro() {
            return bairro;
        }

        public void setBairro(String bairro) {
            this.bairro = bairro;
        }

        public String getLocalidade() {
            return localidade;
        }

        public void setLocalidade(String localidade) {
            this.localidade = localidade;
        }

        public String getUf() {
            return uf;
        }

        public void setUf(String uf) {
            this.uf = uf;
        }
    }
}
