package br.edu.umfg.teste.spring.controllers;

import br.edu.umfg.teste.spring.entities.Produto;
import br.edu.umfg.teste.spring.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProdutoController {
    private ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @GetMapping("/cadastroProduto")
    public String mostrarCadastro(Model model) {
        model.addAttribute("produto", new Produto());
        return "cadastroProduto";
    }

    @GetMapping("/listaProduto")
    public String listarProduto(Model model) {
        model.addAttribute("produto", produtoRepository.findAll());
        return "listaProduto";
    }

    @PostMapping("/produtos")
    public String cadastrarProdutos(@ModelAttribute Produto produto) {
        produtoRepository.save(produto);
        return "redirect:/listaProduto";
    }

    @GetMapping("/realizaVenda")
    public String mostrarVenda(Model model) {
        model.addAttribute("produtos", produtoRepository.findAll());
        return "realizaVenda";
    }

    @PostMapping("/realizaVenda")
    public String realizaVenda(@RequestParam Long produtoId, @RequestParam int quantidade, @RequestParam String nome) {
        Produto produto = produtoRepository.findById(produtoId).orElseThrow();
        produto.setQuantidade(produto.getQuantidade() - quantidade);
        produtoRepository.save(produto);
        return "redirect:/index";
    }

    @GetMapping("/editarProduto/{id}")
    public String editarProduto(@PathVariable Long id, Model model) {
        Produto produto = produtoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));
        model.addAttribute("produto", produto);
        return "editarProduto"; // Nome da página HTML
    }

    // Salvar alterações do produto editado
    @PostMapping("/editarProduto/{id}")
    public String atualizarProduto(@PathVariable Long id, @ModelAttribute Produto produto) {
        produto.setId(id); // Garantir que o ID do produto é mantido
        produtoRepository.save(produto);
        return "redirect:/listaProduto"; // Redireciona para a lista de produtos
    }

    @GetMapping("/excluirProduto/{id}")
    public String excluirProduto(@PathVariable Long id) {
        Produto produto = produtoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));
        produtoRepository.delete(produto);
        return "redirect:/listaProduto"; // Redireciona para a lista de produtos
    }
}