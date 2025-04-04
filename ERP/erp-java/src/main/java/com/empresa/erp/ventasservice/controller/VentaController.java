package com.empresa.erp.ventasservice.controller;

import com.empresa.erp.ventasservice.model.Venta;
import com.empresa.erp.ventasservice.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/venta")
public class VentaController {
    @Autowired
    private VentaService servicio;

    @GetMapping
    public List<Venta> listarVentas() {
        return servicio.obtenerTodas();
    }

    @GetMapping("/{id}")
    public Optional<Venta> obtenerVenta(@PathVariable Long id) {
        return servicio.obtenerTodas().stream().filter(v -> v.getId().equals(id)).findFirst();
    }

    @PostMapping
    public Venta crearVenta(@RequestBody Venta venta) {
        return servicio.guardarVenta(venta);
    }

    @PutMapping("/{id}")
    public Venta actualizarVenta(@PathVariable Long id, @RequestBody Venta venta) {
        return servicio.actualizarVenta(id, venta);
    }

    @DeleteMapping("/{id}")
    public void eliminarVenta(@PathVariable Long id) {
        servicio.eliminarVenta(id);
    }

    @GetMapping("/total-mes")
    public Double obtenerTotalVentasDelMes() {
        return servicio.obtenerTotalVentasDelMes();
    }

    @GetMapping("/ventas-mes")
    public Map<String, Integer> obtenerVentasPorMes() {
        return servicio.obtenerVentasPorMes();
    }
}

