package com.perfulandia.cl.clientes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.perfulandia.cl.clientes.service.ClienteService;
import com.perfulandia.cl.clientes.model.Cliente;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.http.ResponseEntity;
import java.util.Optional;





@RestController
@RequestMapping("/api/v1/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    //metodo que lista todos los clientes
    @GetMapping("/listar")
    public List<Cliente> listarClientes(){
        return clienteService.findAll();
    }

    //metodo que busca un cliente por id
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarCliente(@PathVariable int id){
        Optional<Cliente> clienteOptional = clienteService.findByIdCliente(id); // Obtiene el Optional

        if(clienteOptional.isPresent()){
            // Si el cliente está presente, devuélvelo con un 200 OK
            return new ResponseEntity<>(clienteOptional.get(), HttpStatus.OK);
        } else {
            // Si el cliente no se encuentra, devuelve un 404 Not Found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            // También podrías devolver: return ResponseEntity.notFound().build();
        }
    }


    //metodo que guarda un cliente
    @PostMapping("/guardar")
    public Cliente guardarCliente(@RequestBody Cliente cliente){
        return clienteService.saveCliente(cliente);
    }

    //metodo que actualiza un cliente
    @PutMapping("/actualizar/{id}")
    public Cliente actualizarCliente(@PathVariable int id, @RequestBody Cliente cliente){
        return clienteService.updateClientte(cliente);
    }
    //metodo que actualiza parcialmente un cliente
    @PatchMapping("/actualizar/{id}")
    public Cliente actualizarParcialmenteCliente(@PathVariable int id, @RequestBody Cliente cliente){
        return clienteService.updateClientte(cliente);
    }

    //metodo que elimina un cliente
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable int id){
        Optional<Cliente> clienteOptional = clienteService.findByIdCliente(id); // Obtener el Optional
    
        if(clienteOptional.isPresent()){ // Verificar si el Optional contiene un valor
            clienteService.deleteCliente(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404
        }
    }

    //metodo SQL nativo que lista clientes por orden alfabético
    @GetMapping("/listar/af")
    public List<Cliente> listarClientesOrdenados(){
        return clienteService.findAllOrderByNombreCliente();
    }

}
