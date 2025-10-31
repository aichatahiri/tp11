package com.example.demo.repositories;

import com.example.demo.entities.Compte;
import com.example.demo.entities.TypeCompte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource(path = "comptes")
public interface CompteRepository extends JpaRepository<Compte, Long> {

    // Méthode de recherche par type de compte
    @RestResource(path = "byType") // URL personnalisée : /api/comptes/search/byType
    List<Compte> findByType(@Param("t") TypeCompte type);
}
