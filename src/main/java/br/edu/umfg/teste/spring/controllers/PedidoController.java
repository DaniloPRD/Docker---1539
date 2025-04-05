package br.edu.umfg.teste.spring.controllers;

import br.edu.umfg.teste.spring.entities.Fornecedor;
import br.edu.umfg.teste.spring.entities.ItemPedido;
import br.edu.umfg.teste.spring.entities.Pedido;
import br.edu.umfg.teste.spring.entities.Produto;
import br.edu.umfg.teste.spring.repositories.FornecedorRepository;
import br.edu.umfg.teste.spring.repositories.PedidoRepository;
import br.edu.umfg.teste.spring.repositories.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Controller
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    // Objeto para armazenar o estado do pedido no formulário
    private Pedido pedidoAtual = new Pedido();

    @GetMapping("/cadastroPedido")
    public String exibirFormulario(Model model) {
        model.addAttribute("pedido", pedidoAtual); // Pedido em andamento
        model.addAttribute("fornecedores", fornecedorRepository.findAll());
        model.addAttribute("produtos", produtoRepository.findAll());
        return "cadastroPedido"; // Nome da página HTML para o formulário
    }

    @PostMapping("/pedido/adicionarProduto")
    public String adicionarProduto(@RequestParam Long produtoId, @RequestParam int quantidade) {
        Produto produto = produtoRepository.findById(produtoId).orElseThrow();
        ItemPedido item = new ItemPedido();
        item.setProdutoId(produto);
        item.setPreco(BigDecimal.valueOf(produto.getPrecoVenda()));
        item.setQuantidade(quantidade);

        pedidoAtual.getItens().add(item); // Adiciona o item ao pedido em andamento
        return "redirect:/cadastroPedido";
    }

    @GetMapping("/listaPedido")
    public String listarPedido(Model model) {
        model.addAttribute("pedido", pedidoRepository.findAll());
        return "listaPedido"; // Retorna a página listaPedido.html
    }

    @PostMapping("/salvarPedido")
    public String salvarPedido(@ModelAttribute Pedido pedido) {
        // ...
        Fornecedor fornecedor = fornecedorRepository.findById(pedido.getFornecedorId()).orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));
        pedido.setFornecedor(fornecedor);

        // Configurações do pedido
        pedido.setDataCadastro(LocalDateTime.now());  // Define a data e hora local
        pedido.setValorTotal(
                pedidoAtual.getItens().stream()
                        .map(ItemPedido::getTotal)
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
        );
        pedido.setItens(pedidoAtual.getItens()); // Itens adicionados ao pedido

        // Salva o pedido no banco de dados
        pedidoRepository.save(pedido);

        // Atualiza o estoque do produto
        for (ItemPedido item : pedidoAtual.getItens()) {
            Produto produto = item.getProdutoId();
            produto.setQuantidade(produto.getQuantidade() + item.getQuantidade());
            produtoRepository.save(produto);
        }

        // Limpa o estado do pedido em andamento
        pedidoAtual = new Pedido();

        // Redireciona para a lista de pedidos ou outro destino adequado
        return "redirect:/listaPedido";
    }
}
