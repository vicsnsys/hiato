package com.project.hiato.controller;

import com.project.hiato.dto.UserDTO;
import com.project.hiato.dto.UserPasswordDTO;
import com.project.hiato.dto.UserResponseDTO;
import com.project.hiato.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public UserResponseDTO create(@RequestBody UserDTO data){
        return userService.create(data);
    }

    @GetMapping
    public List<UserResponseDTO> findAll(){
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public UserResponseDTO findById(@PathVariable Long id){
        return userService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        userService.deleteById(id);
    }

    @PutMapping("/{id}")
    public UserResponseDTO update(@PathVariable Long id, @RequestBody UserDTO data){
        return userService.update(id, data);
    }

    @PutMapping("/{id}/password")
    public UserResponseDTO updatePassword(@PathVariable Long id, @RequestBody UserPasswordDTO data){
        return userService.updatePassword(id, data);
    }
}
