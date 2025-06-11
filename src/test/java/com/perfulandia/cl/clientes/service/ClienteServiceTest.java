package com.perfulandia.cl.clientes.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.perfulandia.cl.clientes.model.Cliente;
import com.perfulandia.cl.clientes.repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.Optional;


import java.util.List;

@SpringBootTest
public class ClienteServiceTest {


    @MockBean
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteService clienteService;


    @Test // test para buscar a todos los clientes 
    void testFindAll(){

        when(clienteRepository.findAll()).thenReturn(List.of(new Cliente(1, 0, "Juan", null, null, null, null, null, 12345678)));

        List<Cliente> clientes = clienteService.findAll();

        assertNotNull(clientes);
        assertEquals(clientes.size(),1);
    }


    @Test // test para buscar un cliente por id 
    public void testFindByIdcliente() {
        //instancio un cliente 
           when(clienteRepository.findById(99)).thenReturn(Optional.empty());

        // Act
        Optional<Cliente> result = clienteService.findByIdCliente(99);

        // Assert
        assertFalse(result.isPresent());
    }


    @Test // metodo que guarda un cliente 
    public void testSaveCliente() {
        //intancio un cliente con datos prueba
        Cliente cliente = new Cliente(1, 12345678, "Juan", null, null, null, null, null, 12345678);
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente savedCliente = clienteService.saveCliente(cliente);

        assertNotNull(savedCliente);
        assertEquals(savedCliente.getRutCliente(), cliente.getRutCliente());

    
    }

    @Test  //metodo que elimina un cliente por id
    public void testDeleteCliente(){
 
        int id = 1; 
        doNothing().when(clienteRepository).deleteById(1);

        //llamo al metodo deleteCliente del servicio
        clienteService.deleteCliente(id);

        //verifico que se haya llamado al metodo deleteById del repositorio
        verify(clienteRepository,times(1)).deleteById(id);

        
    }
    @Test
    public void testUpdateCliente() {
        //instancio un cliente con datos de prueba
        Cliente cliente = new Cliente(1, 12345678, "Juan", null, null, null, null, null, 12345678);

        when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente updatedCliente = clienteService.updateClientte(cliente);


        assertNotNull(updatedCliente);
        assertEquals(updatedCliente.getRutCliente(), cliente.getRutCliente());

    }

    @Test
    public void testFindByRut(){

        Cliente cliente = new Cliente(1, 12345678, "K", "Juan", null, null, null, null, 12345678);

        when(clienteRepository.findByRutCliente(cliente.getRutCliente())).thenReturn(List.of(cliente));

        List<Cliente> clientes = clienteService.findByRut(cliente);

        assertNotNull(clientes);
        assertEquals(clientes.size(),1);
    }


    @Test
    public void testFindAllOrderByNombreCliente(){

        when(clienteRepository.findAllOrderByNombreCliente()).thenReturn(List.of(new Cliente(1, 12345678, "Juan", null, null, null, null, null, 12345678)));

        List<Cliente> clientes = clienteService.findAllOrderByNombreCliente();

        assertNotNull(clientes);
        assertEquals(clientes.size(),1);
    }

}
