package com.perfulandia.cl.clientes.controller;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.List;
import com.perfulandia.cl.clientes.service.ClienteService;
import com.perfulandia.cl.clientes.model.Cliente;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Optional;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.http.MediaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;









@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    @Autowired
    private ClienteController clienteController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testListarClientes(){

        when(clienteService.findAll()).thenReturn(List.of(new Cliente(1, 12345678, "K", "Juan", null, null, null, null, 12345678)));

        List<Cliente> clientes = clienteController.listarClientes();

        assertNotNull(clientes);
        assertEquals(clientes.size(),1);
    }

    @Test //metodo que busca un clietne por id
    public void testBuscarClientePorId() throws Exception {

        when(clienteService.findByIdCliente(1)).thenReturn(Optional.of(new Cliente(1, 12345678, "K", "Juan", null, null, null, null, 12345678)));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/cliente/1"))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.idCliente").value(1))
        .andExpect(MockMvcResultMatchers.jsonPath("$.nombreCliente").value("Juan"));
    }
    @Test //por si el cliente no existe 
    public void testBuscarClientePorIdNoExiste() throws Exception {

        when(clienteService.findByIdCliente(1)).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/cliente/1"))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
    
    @Test //metodo que guarda un cliente
    public void testGuardarCliente() throws Exception {

        Cliente cliente = new Cliente(1, 12345678, "K", "Juan", null, null, null, null, 12345678);

        when(clienteService.saveCliente(cliente)).thenReturn(cliente);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/cliente/guardar")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(cliente)))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk());
    }  
    
    @Test //metodo que actualiza un cliente por su id
    public void testActualizarCliente() throws Exception {

        Cliente cliente = new Cliente(1, 12345678, "K", "Juan", null, null, null, null, 12345678);

        when(clienteService.updateClientte(cliente)).thenReturn(cliente);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/cliente/actualizar/{id}", cliente.getIdCliente())
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(cliente)))
        .andDo(MockMvcResultHandlers.print());
    }

    @Test //metodo que actualiza parcialmente un cliente por su id
    public void testActualizarClienteParcial() throws Exception {

        Cliente cliente = new Cliente(1, 12345678, "K", "Juan", null, null, null, null, 12345678);

        when(clienteService.updateClientte(cliente)).thenReturn(cliente);

        mockMvc.perform(MockMvcRequestBuilders.patch("/api/v1/cliente/actualizar/{id}", cliente.getIdCliente())
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(cliente)))
        .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void testEliminarClienteExistente() throws Exception {
        when(clienteService.findByIdCliente(1)).thenReturn(Optional.of(new Cliente()));
        doNothing().when(clienteService).deleteCliente(1);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(clienteController).build();

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/cliente/eliminar/{id}", 1))
            .andExpect(MockMvcResultMatchers.status().isNoContent()); // ← 204, no 200

        verify(clienteService, times(1)).deleteCliente(1);
    }

    @Test //metodo que elimina un cliente pero no existe
    public void testEliminarClienteNoExistente() throws Exception {

        when(clienteService.findByIdCliente(1)).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/cliente/eliminar/{id}", 1))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
    


    @Test //metodo que lista clientes en orden alfabetico
    public void testListarClientesOrdenados() throws Exception {

        when(clienteService.findAllOrderByNombreCliente()).thenReturn(List.of(new Cliente(1, 12345678, "K", "Juan", null, null, null, null, 12345678)));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/cliente/listar/af"))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].nombreCliente").value("Juan"));

    }



    

}
