package com.perfulandia.cl.clientes.model;
import jakarta.persistence.Column;

public class Cliente {

    @Column(name = "id_cliente")
    private int idCliente;

    @Column(name = "rut_cliente")
    private int rutCliente;

    @Column(name = "dv_cliente")
    private String dvCliente;

    @Column(name = "nombre_cliente")
    private String nombreCliente;

    @Column(name = "snombre_cliente")
    private String snombreCliente;

    @Column(name = "appaterno_cliente")
    private String appaternoCliente;

    @Column(name = "apmaterno_cliente")
    private String apmaternoCliente;

    @Column(name = "domiclio_cliente")
    private String domicilioCliente;

    @Column(name = "id_ciudad")
    private int idCiudadiudad;

}
