package dev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.entity.Collegue;
import dev.entity.Commentaire;
import dev.repository.CommentaireRepository;

@RestController
@RequestMapping("/commentaire")
@CrossOrigin
public class CommentaireController {

	@Autowired
	CommentaireRepository CommentaireRepo;

	@GetMapping
	public List<Commentaire> listerCommentaire() {
		return this.CommentaireRepo.findAll();
	}

	@PostMapping("/creerCommentaire")
	public ResponseEntity<?> ajouterCommentaire(@RequestBody Collegue collegue, String commentaire) {

		if ((commentaire != null) && (collegue != null)) {
			Commentaire com = new Commentaire();
			com.setCommentaire(com.getCommentaire());
			com.setCollegue(com.getCollegue());
			CommentaireRepo.save(com);
			return ResponseEntity.ok(this.CommentaireRepo.findAll());

		}
		return ResponseEntity.status(400).body("Vous devez ajouter un commentaire et un collegue valable");
	}
}