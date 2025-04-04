package com.empresa.erp.inventarioservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.empresa.erp.inventarioservice.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
	Page<Producto> findAll(Pageable pageable);
}
