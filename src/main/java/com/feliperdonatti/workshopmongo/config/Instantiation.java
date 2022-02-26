package com.feliperdonatti.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.feliperdonatti.workshopmongo.domain.Post;
import com.feliperdonatti.workshopmongo.domain.User;
import com.feliperdonatti.workshopmongo.dto.AuthorDto;
import com.feliperdonatti.workshopmongo.repository.PostRepository;
import com.feliperdonatti.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {
		postRepository.deleteAll();
		userRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com"); 
		User alex = new User(null, "Alex Green", "alex@gmail.com"); 
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));

		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

		Post post1 = new Post(null, sdf.parse("2022-01-29"), "Partiu Viagem", "Vou viajar para São Paulo! Abraços", new AuthorDto(maria));
		Post post2 = new Post(null, sdf.parse("2021-12-18"), "Bom dia", "Acordei Feliz Hoje", new AuthorDto(maria));
		

		postRepository.saveAll(Arrays.asList(post1, post2));
		
		
		
	}

}
