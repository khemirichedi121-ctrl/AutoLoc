package org.example.autolocapi.repository;

import org.example.autolocapi.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IReservationRepository extends JpaRepository<Reservation, Long> {
}