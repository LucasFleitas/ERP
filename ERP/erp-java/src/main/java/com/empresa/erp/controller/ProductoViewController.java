package com.empresa.erp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.empresa.erp.inventarioservice.service.ProductoService;
import com.empresa.erp.inventarioservice.model.Producto;
import java.util.List;

@Controller
@RequestMapping("/productos")
public class ProductoViewController {

    @Autowired
    private ProductoService servicio;

    @GetMapping
    public String mostrarProductos(Model model) {
        List<Producto> productos = servicio.obtenerProductos();
        model.addAttribute("productos", productos);
        return "productos";
    }

}
