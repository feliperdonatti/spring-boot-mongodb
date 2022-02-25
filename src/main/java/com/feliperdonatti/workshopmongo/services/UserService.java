package com.feliperdonatti.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feliperdonatti.workshopmongo.domain.User;
import com.feliperdonatti.workshopmongo.dto.UserDto;
import com.feliperdonatti.workshopmongo.repository.UserRepository;
import com.feliperdonatti.workshopmongo.services.exception.ObjectNotFoundException;


@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public List<User> findAll(){
		return repository.findAll();
	}
	
	public User findById(String id) {
		Optional<User> user = repository.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + User.class.getName()));
	}
	
	public User insert(User user) {
		return repository.save(user);
	}
	
	public void delete(String id) {
		findById(id);
		repository.deleteById(id);
	}
	
	public User fromDto(UserDto userDto) {
		return new User(userDto.getId(), userDto.getName(), userDto.getEmail());
		
	}

}
