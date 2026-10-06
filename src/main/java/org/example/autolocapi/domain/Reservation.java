package org.example.autolocapi.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.OneToOne;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReservation;

    private LocalDate dateDebut;
    private LocalDate dateFin;
    private StatutReservation statut;

    @ManyToOne
    @JoinColumn(name = "id_vehicule")
    private Vehicule vehicule;
    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;
    @OneToOne(mappedBy = "reservation")
    private Contrat contrat;
}