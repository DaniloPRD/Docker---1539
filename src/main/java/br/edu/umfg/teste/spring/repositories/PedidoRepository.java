package br.edu.umfg.teste.spring.repositories;

import br.edu.umfg.teste.spring.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    Pedido findByid(Long id);
}

