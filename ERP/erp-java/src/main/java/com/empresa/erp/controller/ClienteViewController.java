package com.empresa.erp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.empresa.erp.crmservice.service.ClienteService;
import com.empresa.erp.crmservice.model.Cliente;
import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClienteViewController {

    @Autowired
    private ClienteService servicio;

    @GetMapping
    public String mostrarClientes(Model model) {
        List<Cliente> clientes = servicio.obtenerClientes();
        model.addAttribute("clientes", clientes);
        return "clientes";
    }

}