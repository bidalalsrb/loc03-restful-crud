package com.example.loc03restfulcrud.controller;

import com.example.loc03restfulcrud.controller.dto.UserDto;
import com.example.loc03restfulcrud.entity.User;
import com.example.loc03restfulcrud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserService userService;

    //create
    @PostMapping
    private void create(@RequestBody UserDto requestCreated) {
        userService.create(requestCreated);
    }

    //update
    @PutMapping("{id}")
    private void update(@PathVariable Long id, @RequestBody UserDto requestUpdated) {
        userService.update(id, requestUpdated);
    }
    //delete
    @DeleteMapping("{id}")
    private void delete(@PathVariable Long id){
        userService.delete(id);
    }
    @GetMapping("{id}")
    private User readOne(@PathVariable Long id){
        return userService
                .readOne(id)
                .orElse(null);
    }
    @GetMapping
    public List<User> readAll(){
        return userService.readAll();
    }

}
