package org.example.autolocapi.service;

import lombok.RequiredArgsConstructor;
import org.example.autolocapi.domain.Reservation;
import org.example.autolocapi.exception.ResourceNotFoundException;
import org.example.autolocapi.repository.IReservationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements IReservationService {

    private final IReservationRepository reservationRepository;

    @Override
    public Reservation create(Reservation reservation) {
        // 1. Garde sur l'identifiant
        if (reservation.getIdReservation() != null) {
            throw new IllegalArgumentException("Une nouvelle réservation ne doit pas avoir d'identifiant");
        }
        // 2. Règle de gestion : date de fin >= date de début
        validerDates(reservation.getDateDebut(), reservation.getDateFin());

        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation findById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation", id));
    }

    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation update(Long id, Reservation reservation) {
        Reservation existant = findById(id);

        // Règle de gestion
        validerDates(reservation.getDateDebut(), reservation.getDateFin());

        // Recopie des attributs modifiables
        existant.setDateDebut(reservation.getDateDebut());
        existant.setDateFin(reservation.getDateFin());
        existant.setStatut(reservation.getStatut());

        // Mise à jour des relations si fournies (sans toucher au Contrat)
        if (reservation.getVehicule() != null) {
            existant.setVehicule(reservation.getVehicule());
        }
        if (reservation.getClient() != null) {
            existant.setClient(reservation.getClient());
        }

        return reservationRepository.save(existant);
    }

    @Override
    public void deleteById(Long id) {
        if (!reservationRepository.existsById(id)) {
            throw new ResourceNotFoundException("Reservation", id);
        }
        reservationRepository.deleteById(id);
    }

    // Méthode privée pour valider les dates
    private void validerDates(LocalDate debut, LocalDate fin) {
        if (debut != null && fin != null && fin.isBefore(debut)) {
            throw new IllegalArgumentException("La date de fin ne peut pas être antérieure à la date de début");
        }
    }
}