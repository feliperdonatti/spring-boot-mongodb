package com.feliperdonatti.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feliperdonatti.workshopmongo.domain.Post;
import com.feliperdonatti.workshopmongo.domain.User;
import com.feliperdonatti.workshopmongo.dto.UserDto;
import com.feliperdonatti.workshopmongo.repository.PostRepository;
import com.feliperdonatti.workshopmongo.repository.UserRepository;
import com.feliperdonatti.workshopmongo.services.exception.ObjectNotFoundException;


@Service
public class PostService {
	
	@Autowired
	private PostRepository repository;
	
	public List<Post> findAll(){
		return repository.findAll();
	}
	
	public Post findById(String id) {
		Optional<Post> post = repository.findById(id);
		return post.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Post.class.getName()));
	}
	
	public Post insert(Post post) {
		return repository.save(post);
	}
	
	public void delete(String id) {
		findById(id);
		repository.deleteById(id);
	}
	
	public Post update(Post obj) {
		Post newObj = findById(obj.getId());
		//updateData(newObj, obj);
		return repository.save(newObj);
	}
	/*
	private void updateData(Post newObj, Post obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
		
	}
	*/
	public User fromDto(UserDto userDto) {
		return new User(userDto.getId(), userDto.getName(), userDto.getEmail());
		
	}

}
