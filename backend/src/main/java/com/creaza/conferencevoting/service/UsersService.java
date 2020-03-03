package com.creaza.conferencevoting.service;

import com.creaza.conferencevoting.exception.ResourceNotFoundException;
import com.creaza.conferencevoting.model.dao.User;
import com.creaza.conferencevoting.model.dto.UserDto;
import com.creaza.conferencevoting.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    private static final Logger log = LoggerFactory.getLogger(UsersService.class);

    private UserRepository repository;

    @Autowired
    public UsersService(UserRepository repository) {
        this.repository = repository;
    }

    public User createUser(UserDto user) {
        log.info("creating a new user with email - {}", user.getEmail());
            return repository.save(new User(user.getEmail()));
    }

    public User getUserById(Long id) {
        log.info("fetching user by id - {}", id);
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }
}
