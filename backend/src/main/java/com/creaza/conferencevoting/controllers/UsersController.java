package com.creaza.conferencevoting.controllers;

import com.creaza.conferencevoting.model.dto.UserDto;
import com.creaza.conferencevoting.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UsersController {

    private static final Logger log = LoggerFactory.getLogger(UsersController.class);

    private UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity createUser(@RequestBody @Valid UserDto userDto) {
        log.info("new create user request");
        return ResponseEntity.status(HttpStatus.CREATED).body(usersService.createUser(userDto));
    }
}
