package dev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import dev.entity.Collegue;
import dev.repository.CollegueRepository;

@Component
public class StartUpListener {

	@Autowired
	CollegueRepository collegueRepo;

	@EventListener(ContextRefreshedEvent.class)
	@Transactional
	public void contextRefreshedEvent() {
		Collegue collegue = new Collegue();
		collegue.setNom("Rod");
		collegue.setScore(100);
		collegue.setUrlImage(
				"https://images.pexels.com/photos/265036/pexels-photo-265036.jpeg?w=1260&h=750&auto=compress&cs=tinysrgb");

		Collegue collegue2 = new Collegue();
		collegue2.setNom("Alice");
		collegue2.setScore(800);
		collegue2.setUrlImage(
				"https://images.pexels.com/photos/265036/pexels-photo-265036.jpeg?w=1260&h=750&auto=compress&cs=tinysrgb");

		collegueRepo.save(collegue);
		collegueRepo.save(collegue2);
	}
}
