package com.example.accessingdatamysql.Controllers;

import com.example.accessingdatamysql.Models.User;
import com.example.accessingdatamysql.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@RequestMapping(path="/demo")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers(){
        return StreamSupport.stream(userRepository.findAll().spliterator(),false)
                .collect(Collectors.toList());
    }



    @PostMapping(path = "/add")
    public @ResponseBody String newUser(@RequestParam String name, @RequestParam String email){
        User n=new User(name,email);
        userRepository.save(n);
        return  "Saved";
    }

    @PostMapping(path = "/edit")
    public @ResponseBody String EditUser(@RequestParam String name, @RequestParam String email, @RequestParam int id){
        return userRepository.findById(id)
                .map(user -> {
                    user.setName(name);
                    user.setEmail(email);
                    userRepository.save(user);
                    return "Edit";
                }).
                orElse("Not edit ");

    }

    @DeleteMapping(path="/delete")
    public @ResponseBody String DeleteUser(@RequestParam int id){
        userRepository.findById(id).ifPresent(user -> {
            userRepository.delete(user);
        });
        return "delete";
    }
}
