package com.luistahuite.user.controller;

import com.luistahuite.user.entities.User;
import com.luistahuite.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserRestController {
    @Autowired
    UserRepository userRepository;

    @GetMapping()
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @PostMapping()
    public ResponseEntity<User> create(@RequestBody User user) {
        User save = userRepository.save(user);
        return ResponseEntity.ok(save);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable long id, @RequestBody User user) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User updateUser = optionalUser.get();
            updateUser.setName(user.getName());
            updateUser.setEmail(user.getEmail());
            updateUser.setPassword(user.getPassword());
            User save = userRepository.save(updateUser);
            return new ResponseEntity<>(save, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
