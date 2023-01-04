package com.example.loc03restfulcrud.service;

import com.example.loc03restfulcrud.controller.dto.UserDto;
import com.example.loc03restfulcrud.entity.User;
import com.example.loc03restfulcrud.repositoty.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    // create
    public void create(UserDto user) {
        // 이미 등록된 이메일인지 확인
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        // 비밀번호와 비밀번호 확인이 일치하는지 확인
        if (!user.getPassword().equals(user.getPasswordConfirm())) {
            throw new IllegalArgumentException("Password and password confirm are not same");
        }

        User newUser = new User();
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        userRepository.save(newUser);

    }

    // update
    public void update(Long id, UserDto user) {
        Optional<User> foundUser = userRepository.findById(id);
        if (foundUser.isPresent()) {
            User updateUser = foundUser.get();
            updateUser.setName(user.getName());
            userRepository.save(updateUser);
        } else {
            throw new NoSuchElementException("User not found");
        }
    }

    //delete
    public void delete(Long id) {
        Optional<User> aUser = readOne(id);
        if (aUser.isPresent()) {
            userRepository.delete(aUser.get());
        }
    }

    //read one
    public Optional<User> readOne(Long id) {
        return userRepository.findById(id);
    }

    //read all
    public List<User> readAll() {
        return userRepository.findAll();
    }
}


