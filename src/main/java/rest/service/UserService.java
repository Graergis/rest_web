package rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rest.entity.User;
import rest.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User save(User user) {
		User saveUser = userRepository.save(user);

		return saveUser;
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findOne(Long type) {
		return userRepository.findOne(type);
	}
}
