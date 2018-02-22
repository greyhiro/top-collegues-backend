package dev.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.entity.Collegue;
import dev.entity.Vote;
import dev.entity.Vote.Action;
import dev.repository.CollegueRepository;
import dev.repository.VoteRepository;

@RestController
@RequestMapping("/collegues")
@CrossOrigin
public class CollegueController {

	@Autowired
	CollegueRepository colleguesRepo;

	@Autowired
	VoteRepository voteRepo;

	@GetMapping
	public List<Collegue> listerCollegues() {
		return this.colleguesRepo.findAll();
	}

	@PostMapping("/creer")
	public ResponseEntity<?> ajouterCollegues(@RequestBody Collegue collegue) {
		Collegue col = new Collegue();

		Collegue nom = colleguesRepo.findBynom(collegue.getNom());
		if (nom != null) {

			return ResponseEntity.ok("deja existant en BDD");

		}

		if (collegue != null) {

			col.setNom(collegue.getNom());

			col.setUrlImage(collegue.getUrlImage());
			col.setScore(0);

			colleguesRepo.save(collegue);

			return ResponseEntity.ok(this.colleguesRepo.findAll());

		}

		return ResponseEntity.status(400).body("Vous devez ajouter un collegue valable");

	}

	@PatchMapping("/{nom}")
	public ResponseEntity<?> mettreAjourScore(@PathVariable String nom, @RequestBody Map<String, String> action)

	{

		Vote vote = new Vote();
		Collegue collegueChoisie = colleguesRepo.findBynom(nom);

		if (action.containsValue("aimer") == true) {

			vote.setAction(Action.aimer);
			vote.setCollegue(collegueChoisie);
			voteRepo.save(vote);

			collegueChoisie.setScore(collegueChoisie.getScore() + 10);
			return ResponseEntity.ok(colleguesRepo.save(collegueChoisie));
		}

		if (action.containsValue("deteste") == true) {
			vote.setAction(Action.detester);
			vote.setCollegue(collegueChoisie);
			voteRepo.save(vote);
			collegueChoisie.setScore(collegueChoisie.getScore() - 10);
			return ResponseEntity.ok(colleguesRepo.save(collegueChoisie));
		}

		return ResponseEntity.status(400).body("action non valable");

	}
}
