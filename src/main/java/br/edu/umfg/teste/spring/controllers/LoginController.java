package br.edu.umfg.teste.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private static final String MASTER_USERNAME = "admin";
    private static final String MASTER_PASSWORD = "admin";

    @GetMapping("/")
    public String mostrarLogin() {
        return "login"; // Retorna a página index.html
    }

    @GetMapping("/index")
    public String mostrarIndex() {
        return "index"; // Retorna a página index.html
    }

    @GetMapping("/login")
    public String exibirLogin() {
        return "login"; // Nome do arquivo HTML para a página de login
    }

    @PostMapping("/login")
    public String validarLogin(
            @RequestParam String username,
            @RequestParam String password,
            Model model) {

        if (MASTER_USERNAME.equals(username) && MASTER_PASSWORD.equals(password)) {
            return "redirect:/index"; // Redireciona para o menu principal
        } else {
            model.addAttribute("error", "Usuário ou senha incorretos!");
            return "login"; // Retorna à página de login com erro
        }
    }
}

