package br.edu.umfg.teste.spring.repositories;

import br.edu.umfg.teste.spring.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    Produto findByid(int id);
}

