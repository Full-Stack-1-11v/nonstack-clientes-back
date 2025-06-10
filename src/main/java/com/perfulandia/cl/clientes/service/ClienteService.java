package com.perfulandia.cl.clientes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.perfulandia.cl.clientes.repository.ClienteRepository;
import java.util.List;
import com.perfulandia.cl.clientes.model.Cliente;
import java.util.Optional;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ClienteService {

    @Autowired
    public ClienteRepository clienteRepository;

    //metodo que lista a todos los clientes
    public List<Cliente> findAll(){
        List<Cliente> clientes = clienteRepository.findAll();
        if(clientes.isEmpty()){
            System.out.println("No hay clientes");
            return null;
        }
        return clientes;
    }

    //metodo que busca un cliente por id

    public Optional<Cliente> findByIdCliente(int id) {
        return clienteRepository.findById(id);
    }

    //metodo que guarda un cliente

    public Cliente saveCliente(Cliente cliente){
        
        return clienteRepository.save(cliente);
    }

    //metodo que elimina un cliente

    public void deleteCliente(int id){
        clienteRepository.deleteById(id);
    }

    //metodo que actualiza un cliente
    public Cliente updateClientte(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    

    //metodo que busca un cliente por rut
    public List<Cliente> findByRut(Cliente cliente){
        return clienteRepository.findByRutCliente(cliente.getRutCliente());
    }
    //metodo que filtra clientes por orden alfabético
    public List<Cliente> findAllOrderByNombreCliente() {
        return clienteRepository.findAllOrderByNombreCliente();
    }
   
}
