package org.example.autolocapi.repository;

import org.example.autolocapi.domain.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVehiculeRepository extends JpaRepository<Vehicule, Long> {
}