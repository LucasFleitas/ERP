package com.empresa.erp.ventasservice.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.empresa.erp.ventasservice.model.Venta;
@Repository
public interface VentaRepository extends JpaRepository<Venta, Long>{
	Page<Venta> findAll(Pageable pageable);
    @Query("SELECT SUM(v.total) FROM Venta v WHERE MONTH(v.fecha) = MONTH(CURRENT_DATE) AND YEAR(v.fecha) = YEAR(CURRENT_DATE)")
    Double obtenerTotalVentasDelMes();

    @Query("SELECT MONTH(v.fecha), COUNT(v) FROM Venta v GROUP BY MONTH(v.fecha) ORDER BY MONTH(v.fecha)")
    List<Object[]> obtenerVentasAgrupadasPorMes();
	}
