package org.example.autolocapi.repository;

import org.example.autolocapi.domain.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMaintenanceRepository extends JpaRepository<Maintenance, Long> {
}