package com.feliperdonatti.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feliperdonatti.workshopmongo.domain.Post;
import com.feliperdonatti.workshopmongo.repository.PostRepository;
import com.feliperdonatti.workshopmongo.services.exception.ObjectNotFoundException;


@Service
public class PostService {
	
	@Autowired
	private PostRepository repository;
	
	
	public Post findById(String id) {
		Optional<Post> post = repository.findById(id);
		return post.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Post.class.getName()));
	}
	
	public List<Post> findByTitle(String text){
		//return repository.findByTitleContainingIgnoreCase(text);
		return repository.searchTitle(text);
	}
	
	

}
