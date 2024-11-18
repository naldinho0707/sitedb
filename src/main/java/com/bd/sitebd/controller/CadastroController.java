package com.bd.sitebd.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.bd.sitebd.model.Cliente;
import com.bd.sitebd.model.ClienteService;
import com.bd.sitebd.model.Tool;

// import jakarta.websocket.server.PathParam;

@Controller
public class CadastroController {

    @Autowired
    private ApplicationContext context;

    @GetMapping("/")
    public String principal(){
       // retorno página principal
        return "principal";
    }

    @GetMapping("/atualizar/{id}")
    // Variável de caminho @PasthVariable
    public String atualizar(Model model, @PathVariable int id){
        // Criar um cliente que não é vazio
        ClienteService cs = context.getBean(ClienteService.class);
        Cliente cli = cs.obterCliente(id);
        model.addAttribute("id", id);
        model.addAttribute("cliente", cli);
        // retorno página cadastro
        return "atualizar";
    }

    @PostMapping("/atualizar/{id}")
    public String atualizar(@PathVariable int id, @ModelAttribute Cliente cli){
        ClienteService cs = context.getBean(ClienteService.class);
        cs.atualizarCliente(id, cli);
        return "redirect:/listagem";
    }


    @GetMapping("/cadastro")
    public String cadastro(Model model){
        model.addAttribute("cliente", new Cliente("",""));
        // retorno página cadastro
        return "cadastro";
    }


    @PostMapping("/cadastro")
    public String cadastrar(Model model, @ModelAttribute Cliente cli){
        ClienteService cs = context.getBean(ClienteService.class);
        cs.inserir(cli);
        // retorno página sucesso
        return "sucesso";
    }


    @GetMapping("/listagem")
     public String listagem(Model model){
      ClienteService cs = context.getBean(ClienteService.class);
      // pegar uma lista no banco de dados usando a função obterTodosClientes()
      //usando a variável "cs" com a classe ClienteService"   
      List<Map<String, Object>> lista = cs.obterTodosClientes();

      //Criar uma lista de Cliente Java para receber a lista de objetos JSON convertidos 
      // no "for" abaixo para objetos java utilizando a função da classe tool.java que criamomos 
      // importar ArrayList
      //List é classe abstrata e Arraylist é classe concreta   
      List<Cliente> listaClientes = new ArrayList<Cliente>();

      // Transforar a lista de JSON em lista de Clientes usando a classe Tool que criamos
      for(Map<String, Object> registro : lista){
          // importar a classe tool.java  
          listaClientes.add(Tool.converterCliente(registro));
      }
       //Mandar a listaClientes para o model   
       model.addAttribute("clientes", listaClientes);
       return "listagem";
    }


    // Deletar com confirmação

    // @GetMapping("/deletar/{id}")
    // // Variável de caminho @PasthVariable
    // public String deletar(Model model, @PathVariable int id){
    //     // Criar um cliente que não é vazio
    //     ClienteService cs = context.getBean(ClienteService.class);
    //     Cliente cli = cs.obterCliente(id);
    //     model.addAttribute("id", id);
    //     model.addAttribute("cliente", cli);
    //     // retorno página cadastro
    //     return "deletar";
    // }

    @PostMapping("/deletar/{id}")
    public String deletar(@PathVariable int id){
        ClienteService cs = context.getBean(ClienteService.class);
        cs.deletarCliente(id);
        return "redirect:/listagem";
    }

}
