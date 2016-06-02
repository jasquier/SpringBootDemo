package io.zipcoder.controller;


import io.zipcoder.domain.User;
import io.zipcoder.exception.ResourceNotFoundException;
import io.zipcoder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value="/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value="/users", method= RequestMethod.GET)
    public ResponseEntity<Iterable<User>> getAllUsers(){
        Iterable<User> users = userRepository.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(value="/user/{userId}")
    public ResponseEntity<User> getUser(@PathVariable Long userId){
        verifyUser(userId);
        User user = userRepository.findOne(userId);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @RequestMapping(value="/user/lookup/{username}")
    public ResponseEntity<User> findUserByUserName(@PathVariable String username){
        User user = userRepository.findByUsername(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value="/user/name/{firstName}")
    public ResponseEntity<Iterable<User>> findUserByFirstName(@PathVariable String firstName){
        Iterable<User> users = userRepository.findUserQuery(firstName);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(value="/user", method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        user = userRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    protected void verifyUser(Long userId) throws ResourceNotFoundException {
        User user = userRepository.findOne(userId);
        if(user == null){
            throw new ResourceNotFoundException("User with id " + userId +" does not exist");
        }
    }
}
