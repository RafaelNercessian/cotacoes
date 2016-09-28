package br.com.cabolider.cotacoes.rest;

import br.com.cabolider.cotacoes.modelo.ProdutoRest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class ClienteRest {
    private ProdutoRest produtoRest = new ProdutoRest();
    private Client client = Client.create();
    private String entity;

    public void post(String codigo) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        this.produtoRest.setCodigo(codigo);
        String jsonEmString = mapper.writeValueAsString((Object)this.produtoRest);
        String postUrl = "http://localhost:8080/cabolider-estoque/rest/json/estoque/post";
        WebResource webResource = this.client.resource(postUrl);
        ClientResponse response = (ClientResponse)webResource.type("application/json").post((Class)ClientResponse.class, (Object)jsonEmString);
        this.entity = (String)response.getEntity((Class)String.class);
        if (response.getStatus() != 201) {
            throw new RuntimeException("N\u00e3o foi poss\u00edvel criar o objeto");
        }
    }

    public ProdutoRest[] getEstoque() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        WebResource webResource = this.client.resource("http://localhost:8080/cabolider-estoque/rest/json/estoque/get?codigo=" + this.entity);
        ClientResponse response = (ClientResponse)webResource.accept(new String[]{"application/json"}).get((Class)ClientResponse.class);
        String result = (String)response.getEntity((Class)String.class);
        ProdutoRest[] value = (ProdutoRest[])mapper.readValue(result, (Class)ProdutoRest[].class);
        return value;
    }
}