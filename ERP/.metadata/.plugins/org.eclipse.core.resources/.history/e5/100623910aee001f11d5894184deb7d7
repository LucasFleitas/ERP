package com.empresa.erp.inventarioservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.empresa.erp.inventarioservice.model.Producto;
import com.empresa.erp.inventarioservice.service.ProductoService;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService servicio;

    @GetMapping
    public List<Producto> listarProductos() {
        return servicio.obtenerProductos();
    }

    @GetMapping("/{id}")
    public Optional<Producto> obtenerProducto(@PathVariable Long id) {
        return servicio.obtenerProductoPorId(id);
    }

    @PostMapping
    public Producto crearProducto(@RequestBody Producto producto) {
        return servicio.guardarProducto(producto);
    }

    @DeleteMapping
    public void eliminarProducto(@RequestParam Long id) {
        servicio.eliminarProducto(id);
    }

    @PutMapping
    public Producto actualizarProducto(@RequestParam Long id, @RequestBody Producto producto) {
        return servicio.actualizarProducto(id, producto);
    }

    @GetMapping("/paginado")
    public Page<Producto> listarProductosPaginados(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "5") int size) {
        return servicio.listarProductosPaginados(page, size);
    }
}
