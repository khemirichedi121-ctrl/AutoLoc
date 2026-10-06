package org.example.autolocapi.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import java.util.List;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contrat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idContrat;

    private LocalDate dateSignature;
    private BigDecimal montantTotal;
    private Boolean valide;

    @OneToOne
    @JoinColumn(name = "id_reservation")
    private Reservation reservation;
    @OneToMany(
            mappedBy = "contrat",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Paiement> paiements;
}