package com.perfulandia.cl.clientes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import java.util.List;
import com.perfulandia.cl.clientes.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{



    //metodo que lista todos los clientes
    List<Cliente> findAll();
    //metodo que lista cliente por id
    Optional<Cliente> findByIdCliente(int idCliente);

    //metodo que lista cliente por rut
    List<Cliente> findByRutCliente(int rutCliente);

    //metodo que lista clientes por nombre
    List<Cliente> findByNombreCliente(String nombreCliente);

    //metodo que revisa si un cliente existe por rut
    boolean existsByRutCliente(int rutCliente);

    //metodo que revisa si un cliente existe por id
    boolean existsByIdCliente(int idCliente);
    
    //metodo SQL nativo que lista clientes por orden alfabético
    @Query(value = "SELECT * FROM cliente ORDER BY nombre_cliente ASC", nativeQuery = true)
    List<Cliente> findAllOrderByNombreCliente();

    

    
}
