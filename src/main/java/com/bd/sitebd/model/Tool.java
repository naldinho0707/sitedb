package com.bd.sitebd.model;

import java.util.Map;


// converter o Map<String,Object>  em um objeto da classe Cliente
public class Tool {

    // recebe um Map e retornar um cliente (Cliente no formato objeto do java)
    //  por causa do thymeleaf.org  porque o MAP retorna "JSON" e não objeto java
    public static Cliente converterCliente(Map<String,Object> registro){
        // usar downcast para os objetos do Map (Interger = inteiro)
        // Como registro.get retorna um Object, devemos usar o polimorfismo
        // se subtipos ("String", "Interger") (downcast) para recuperar os tipos originais
        // os tipos devem ser os mesmos do banco de dados, exemplo varchar no banco e aqui inteiro
        // vai dar erro
        return new Cliente((Integer) registro.get("id")
                          ,(String) registro.get("nome")
                          ,(String) registro.get("cpf"));
    }
}
