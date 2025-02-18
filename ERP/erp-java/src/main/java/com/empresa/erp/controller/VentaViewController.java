package com.empresa.erp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.empresa.erp.ventasservice.service.VentaService;
import com.empresa.erp.ventasservice.model.Venta;
import java.util.List;

@Controller
@RequestMapping("/ventas")
public class VentaViewController {

    @Autowired
    private VentaService servicio;

    @GetMapping
    public String mostrarVentas(Model model) {
        List<Venta> ventas = servicio.obtenerTodas();
        model.addAttribute("ventas", ventas);
        return "ventas";
    }

}