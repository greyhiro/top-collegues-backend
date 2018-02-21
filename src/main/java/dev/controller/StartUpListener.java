package dev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import dev.entity.Collegue;
import dev.entity.Vote;
import dev.entity.Vote.Action;
import dev.repository.CollegueRepository;
import dev.repository.VoteRepository;

@Component
public class StartUpListener {

	@Autowired
	CollegueRepository collegueRepo;

	@Autowired
	VoteRepository voteRepo;

	@EventListener(ContextRefreshedEvent.class)
	@Transactional
	public void contextRefreshedEvent() {
		Collegue collegue = new Collegue();
		collegue.setNom("Soquette");
		collegue.setScore(1000);
		collegue.setUrlImage(
				"https://vignette.wikia.nocookie.net/mana/images/5/5a/AoM_Chocobo.png/revision/latest?cb=20161007161048");

		Collegue collegue2 = new Collegue();
		collegue2.setNom("Leila");
		collegue2.setScore(800);
		collegue2.setUrlImage(
				"https://vignette.wikia.nocookie.net/vsbattles/images/f/fc/ChocoboTransperent.png/revision/latest?cb=20160113023846");
		collegueRepo.save(collegue);
		collegueRepo.save(collegue2);

		Collegue collegue3 = new Collegue();
		collegue3.setNom("Gaetan");
		collegue3.setScore(700);
		collegue3.setUrlImage(
				"https://static.fnac-static.com/multimedia/FR/Images_Produits/FR/MC/Visuel%20principal%20340x340/9/3/8//4988601404839/tsp20121030190242/FINAL-FANTASY-SERIES-Peluche-Chocobo.jpg");
		collegueRepo.save(collegue3);

		Collegue collegue4 = new Collegue();
		collegue4.setNom("Quentin");
		collegue4.setScore(700);
		collegue4.setUrlImage("https://static.zerochan.net/Chocobo.full.2071380.jpg");
		collegueRepo.save(collegue4);

		Collegue collegue5 = new Collegue();
		collegue5.setNom("Zackari");
		collegue5.setScore(700);
		collegue5.setUrlImage("https://slm-assets1.secondlife.com/assets/3775687/view_large/PCG%20ad.jpg?1308763042");
		collegueRepo.save(collegue5);

		Vote vote1 = new Vote();
		vote1.setCollegue(collegue5);
		vote1.setAction(Action.detester);
		voteRepo.save(vote1);

	}
}
