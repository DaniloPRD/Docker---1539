package br.edu.umfg.teste.spring.entities;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Entity
public class Funcionario extends Pessoa{

    @Column(name = "NOME", nullable = false)
    private String nome;

    @Column(name = "SALARIO", nullable = false)
    private Double salario = 0.0;

    @Column(name = "DATA_NASC", nullable = true)
    @DateTimeFormat( pattern = "yyyy-MM-dd")
    private LocalDate data_nasc;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public LocalDate getData_nasc() {
        return data_nasc;
    }

    public void setData_nasc(LocalDate data_nasc) {
        this.data_nasc = data_nasc;
    }
}
