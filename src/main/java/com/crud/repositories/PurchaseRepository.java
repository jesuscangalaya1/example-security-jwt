package com.crud.repositories;

import com.crud.entities.PurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<PurchaseEntity, Long> {
    List<PurchaseEntity> findByUsuario_NombreUsuario(String nombreUsuario);

}
