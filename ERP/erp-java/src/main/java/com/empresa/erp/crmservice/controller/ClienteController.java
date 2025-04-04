package com.empresa.erp.crmservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.erp.crmservice.model.Cliente;
import com.empresa.erp.crmservice.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService servicio;

    @GetMapping
    public List<Cliente> listarClientes() {
        return servicio.obtenerClientes();
    }

    @GetMapping("/{id}")
    public Optional<Cliente> obtenerCliente(@PathVariable Long id) {
        return servicio.obtenerClientePorId(id);
    }

    @PostMapping
    public Cliente crearCliente(@RequestBody Cliente cliente) {
        return servicio.guardarCliente(cliente);
    }

    @DeleteMapping
    public void eliminarCliente(@RequestParam Long id) {
    	servicio.eliminarCliente(id);
    }

    @PutMapping
    public Cliente actualizarCliente(@RequestParam Long id, @RequestBody Cliente cliente) {
        return servicio.actualizarCliente(id, cliente);
    }

    @GetMapping("/paginado")
    public Page<Cliente> listarClientesPaginados(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "5") int size) {
        return servicio.listarClientesPaginados(page, size);
    }
}

