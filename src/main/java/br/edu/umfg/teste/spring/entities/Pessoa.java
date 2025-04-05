package br.edu.umfg.teste.spring.entities;

import jakarta.persistence.*;

@Entity
@Table (name = "pessoa")
@Inheritance(strategy = InheritanceType.JOINED)
public class Pessoa{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ENDERECO_ID", referencedColumnName = "ID")
    private Endereco endereco = new Endereco();

    @Column(name = "DOCUMENTO", nullable = false)
    private String documento;

    @Column(name = "FONE", nullable = true)
    private String telefone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumento() {
        return documento;
    }

    public String getTelefone() {
        return telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
       this.endereco = endereco;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
