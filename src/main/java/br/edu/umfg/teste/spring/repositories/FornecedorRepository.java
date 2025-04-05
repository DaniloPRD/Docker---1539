package br.edu.umfg.teste.spring.repositories;

import br.edu.umfg.teste.spring.entities.Fornecedor;
import br.edu.umfg.teste.spring.entities.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
    Fornecedor findByid(Long id);

}



