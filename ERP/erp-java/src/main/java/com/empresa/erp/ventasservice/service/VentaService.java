package com.empresa.erp.ventasservice.service;

import com.empresa.erp.inventarioservice.model.Producto;
import com.empresa.erp.inventarioservice.repository.ProductoRepository;
import com.empresa.erp.ventasservice.model.DetalleVenta;
import com.empresa.erp.ventasservice.model.Venta;
import com.empresa.erp.ventasservice.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class VentaService {
    @Autowired
    private VentaRepository ventaRepository;
    
    @Autowired
    private ProductoRepository productoRepository; // Agregar el repositorio para recuperar productos

    public List<Venta> obtenerTodas() {
        return ventaRepository.findAll();
    }

    public Venta guardarVenta(Venta venta) {
        if (venta.getCliente() == null || venta.getCliente().getId() == null) {
            throw new IllegalArgumentException("La venta debe tener un cliente válido.");
        }

        double total = 0;
        for (DetalleVenta detalle : venta.getDetalles()) {
            Producto producto = productoRepository.findById(detalle.getProducto().getId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + detalle.getProducto().getId()));

            if (producto.getStock() < detalle.getCantidad()) {
                throw new RuntimeException("Stock insuficiente para el producto: " + producto.getNombre());
            }

            producto.setStock(producto.getStock() - detalle.getCantidad());

            detalle.setProducto(producto);
            detalle.setVenta(venta);
            detalle.calcularSubtotal();

            total += detalle.getSubtotal();
        }

        venta.setTotal(total);
        Venta ventaGuardada = ventaRepository.save(venta);

        // Recuperar la venta con el cliente completamente cargado antes de devolverla
        return ventaRepository.findById(ventaGuardada.getId()).orElseThrow();
    }

    
    public Venta actualizarVenta(Long id, Venta ventaActualizada) {
        return ventaRepository.findById(id).map(venta -> {
            venta.setCliente(ventaActualizada.getCliente());
            venta.setFecha(ventaActualizada.getFecha());
            venta.setTotal(ventaActualizada.getTotal());
            return ventaRepository.save(venta);
        }).orElseThrow(() -> new RuntimeException("Venta no encontrada"));
    }
    
    public void eliminarVenta(Long id) {
        ventaRepository.deleteById(id);
    }
    
    public Double obtenerTotalVentasDelMes() {
        return ventaRepository.obtenerTotalVentasDelMes();
    }

    public Map<String, Integer> obtenerVentasPorMes() {
        List<Object[]> resultados = ventaRepository.obtenerVentasAgrupadasPorMes();
        Map<String, Integer> ventasPorMes = new LinkedHashMap<>();
        for (Object[] resultado : resultados) {
            ventasPorMes.put(resultado[0].toString(), ((Number) resultado[1]).intValue());
        }
        return ventasPorMes;
    }

}

