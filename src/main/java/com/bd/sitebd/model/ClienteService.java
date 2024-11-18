package com.bd.sitebd.model;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ClienteService {

    @Autowired
    ClienteDAO cdao;

    public void inserir(Cliente cli){
        cdao.inserir(cli);
    }

    // Serviço do método, pode ser mesmo nome, se tiver return
    // o serviço tem que ter return também, depois criar template
    public List<Map<String, Object>> obterTodosClientes(){
        return cdao.obterTodosClientes();
    }

    public void atualizarCliente(int id, Cliente cli){
        cdao.atualizarCliente(id, cli);
    }

    public Cliente obterCliente(int id){
        return cdao.obterCliente(id);
    }

    public void deletarCliente(int id){
        cdao.deletarCliente(id);
    }


}
