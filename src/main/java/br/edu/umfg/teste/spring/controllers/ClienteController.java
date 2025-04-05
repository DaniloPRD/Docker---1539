package br.edu.umfg.teste.spring.controllers;

import br.edu.umfg.teste.spring.entities.Cliente;
import br.edu.umfg.teste.spring.repositories.EnderecoRepository;
import br.edu.umfg.teste.spring.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ClienteController {
    private ClienteRepository clienteRepository;
    private EnderecoRepository enderecoRepository;

    @Autowired
    public ClienteController(ClienteRepository clienteRepository, EnderecoRepository enderecoRepository) {
        this.clienteRepository = clienteRepository;
        this.enderecoRepository = enderecoRepository;
    }

    @GetMapping("/cadastroCliente")
    public String mostrarCadastro(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "cadastroCliente";
    }

    @GetMapping("/listaCliente")
    public String listarCliente(Model model) {
        model.addAttribute("cliente", clienteRepository.findAll());
        return "listaCliente";
    }

    @PostMapping("/clientes")
    public String cadastrarCliente(@ModelAttribute Cliente cliente) {
        enderecoRepository.save(cliente.getEndereco());
        clienteRepository.save(cliente);
        return "redirect:/listaCliente";
    }

    @GetMapping("/editarCliente/{id}")
    public String mostrarEditarCliente(@PathVariable Long id, Model model) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente inválido: " + id));
        model.addAttribute("cliente", cliente);
        return "editarCliente";
    }

    @PostMapping("/editarCliente/{id}")
    public String editarCliente(@PathVariable Long id, @ModelAttribute Cliente cliente) {
        cliente.setId(id);
        enderecoRepository.save(cliente.getEndereco());
        clienteRepository.save(cliente);
        return "redirect:/listaCliente";
    }

    @GetMapping("/excluirCliente/{id}")
    public String excluirCliente(@PathVariable Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente inválido: " + id));
        clienteRepository.delete(cliente);
        return "redirect:/listaCliente";
    }
}