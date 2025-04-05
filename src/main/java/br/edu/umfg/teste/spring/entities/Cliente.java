package br.edu.umfg.teste.spring.entities;

import jakarta.persistence.*;

@Entity
public class Cliente extends Pessoa{

    @Column(name = "NOME", nullable = false)
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
