package com.empresa.erp.ventasservice.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.empresa.erp.crmservice.model.Cliente;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "ventas")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id", nullable = false)
    @NotNull(message = "La venta debe estar asociada a un cliente")
    private Cliente cliente;

    @NotNull(message = "La fecha no puede ser nula")
    private LocalDate fecha;

    private Double total; 
    
    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
    private List<DetalleVenta> detalles;

    // Constructor vacío requerido por JPA
    public Venta() {
    	this.detalles = new ArrayList<DetalleVenta>();
    }

    public Venta(Cliente cliente, Double total, LocalDate fecha) {
        this.cliente = cliente;
        this.total = total;
        this.fecha = fecha;
    }

    // Getters y Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public List<DetalleVenta> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<DetalleVenta> detalles) {
	    this.detalles = detalles;
	    for (DetalleVenta detalle : detalles) {
	        detalle.setVenta(this);
	    }
	}

	
}


