package com.perfulandia.cl.clientes.repository;

import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class ClienteRepository extends JpaRepository<Cliente, Integer>{

    //metodo qeu lista a los clientes por id

    List<Cliente> findById(int id){

    }
        
        
    }


}
