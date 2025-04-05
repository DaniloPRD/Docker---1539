package br.edu.umfg.teste.spring.controllers;

import br.edu.umfg.teste.spring.entities.Endereco;
import br.edu.umfg.teste.spring.entities.Fornecedor;
import br.edu.umfg.teste.spring.repositories.EnderecoRepository;
import br.edu.umfg.teste.spring.repositories.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class FornecedorController {
    private FornecedorRepository fornecedorRepository;
    private EnderecoRepository enderecoRepository;

    @Autowired
    public FornecedorController(FornecedorRepository fornecedorRepository, EnderecoRepository enderecoRepository) {
        this.fornecedorRepository = fornecedorRepository;
        this.enderecoRepository = enderecoRepository;
    }

    @GetMapping("/cadastroFornecedor")
    public String mostrarCadastro(Model model) {
        model.addAttribute("fornecedor", new Fornecedor());
        return "cadastroFornecedor";
    }

    @GetMapping("/listaFornecedor")
    public String listarFornecedor(Model model) {
        model.addAttribute("fornecedor", fornecedorRepository.findAll());
        return "listaFornecedor";
    }

    @PostMapping("/fornecedores")
    public String cadastrarFornecedor(@ModelAttribute Fornecedor fornecedor) {
        enderecoRepository.save(fornecedor.getEndereco());
        fornecedorRepository.save(fornecedor);
        return "redirect:/listaFornecedor";
    }

    @GetMapping("/editarFornecedor/{id}")
    public String editarFornecedor(@PathVariable Long id, Model model) {
        Fornecedor fornecedor = fornecedorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Fornecedor não encontrado"));
        model.addAttribute("fornecedor", fornecedor);
        return "editarFornecedor"; // Nome da página de edição
    }

    @PostMapping("/editarFornecedor/{id}")
    public String atualizarFornecedor(@PathVariable Long id, @ModelAttribute Fornecedor fornecedor) {
        // Verifica se o endereço existe, se não, salva
        if (fornecedor.getEndereco() != null && fornecedor.getEndereco().getId() == null) {
            // Salva o endereço antes de salvar o fornecedor
            Endereco endereco = fornecedor.getEndereco();
            enderecoRepository.save(endereco);  // Salva o endereço primeiro
        }

        // Agora salva o fornecedor
        fornecedorRepository.save(fornecedor);

        return "redirect:/listaFornecedor";  // Redireciona após a atualização
    }
    @GetMapping("/excluirFornecedor/{id}")
    public String excluirFornecedor(@PathVariable Long id) {
        Fornecedor fornecedor = fornecedorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Fornecedor não encontrado"));
        fornecedorRepository.delete(fornecedor);
        return "redirect:/listaFornecedor"; // Redireciona para a lista de fornecedores
    }
}