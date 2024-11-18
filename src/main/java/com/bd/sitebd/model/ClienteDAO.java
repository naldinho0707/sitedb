package com.bd.sitebd.model;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;


@Repository
public class ClienteDAO {
// DAO = Data Acssess Object

    @Autowired
    DataSource dataSource;

    JdbcTemplate jdbc;

    @PostConstruct
    private void initialize(){
        jdbc = new JdbcTemplate(dataSource);
    }

    public void inserir(Cliente cli){
        String sql = "INSERT INTO cliente(nome,cpf) VALUES (?,?);";
        Object[] parametros = new Object[2];
        parametros[0] = cli.getNome();
        parametros[1] = cli.getCpf();
        jdbc.update(sql, parametros);
    }

    // Obs.: Cada método criado tem que fazer um serviço no ClienteService.java
    // Método para retornar uma lista de clientes /"Tipo uma lista de JSON"
    //retorno lista [Map]=> [ {id: 1, nome: teste1, cpf: 12345678912}
    //                   {id: 2, nome: teste2, cpf: 98765432198} ]
    public List<Map<String, Object>> obterTodosClientes(){
        String sql = "Select * from cliente ORDER BY id ASC;";
        return jdbc.queryForList(sql);
    }

    // Método para atualizar o cadastro
    public void atualizarCliente(int id, Cliente cli){
        String sql = "UPDATE cliente SET ";
        sql += "nome = ?, cpf = ? WHERE id = ?";
        jdbc.update(sql, cli.getNome(), cli.getCpf(), id);
    }

    // Método obter 01 cliente específico com a informação do sue id
    public Cliente obterCliente(int id){
        String sql = "Select * from cliente where id = ?";
        // converter JSON para cliente, passando o id
        // queryForMap pega 01 registro o queryForList pega todos
        return Tool.converterCliente(jdbc.queryForMap(sql, id));
    }

    // Método deletar cliente
    public void deletarCliente(int id){
       String sql = "DELETE FROM cliente WHERE id = ?";
       jdbc.update(sql, id);
    }
}
