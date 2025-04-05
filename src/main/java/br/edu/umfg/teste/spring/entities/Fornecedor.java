package br.edu.umfg.teste.spring.entities;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
public class Fornecedor extends Pessoa{

    @Column(name = "NOME_FANTASIA", nullable = false)
    private String nomeFantasia;

    @Column(name = "INSCRICAO_ESTADUAL", nullable = false)
    private String inscricaoEstadual;

    @Column(name = "RAZAO_SOCIAL", nullable = false)
    private String razaoSocial;

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

}
