package dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.entity.Collegue;

public interface CollegueRepository extends JpaRepository<Collegue, Integer> {

	Collegue findBynom(String nom);

	Collegue findByurlImage(String urlImage);

}
