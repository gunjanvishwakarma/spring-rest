package com.gunjan.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gunjan.rest.webservices.restfulwebservices.exception.UserNotFoundException;

@RestController
public class UserResource
{
    @Autowired
    private UserRepository userRepository;
    
    @GetMapping(path = "/users")
    public List<User> retrieveAllUsers()
    {
        return userRepository.findAll();
    }
    
    @GetMapping(path = "/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id)
    {
        User user = userRepository.getOne(id);
        if(user == null) throw new UserNotFoundException("User does not exist: " + id);
    
        EntityModel<User> resource = new EntityModel<User>(user);
    
        ControllerLinkBuilder controllerLinkBuilder = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
    
        resource.add(controllerLinkBuilder.withRel("all-users"));
        
        return resource;
    }
    
    @PostMapping(path = "/users")
    public ResponseEntity createUser(@Valid @RequestBody User user)
    {
        User savedUser = userRepository.save(user);
        URI userUri = ServletUriComponentsBuilder.
                fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(userUri).build();
    }
    
    @DeleteMapping(path = "/users/{id}")
    public void deleteById(@PathVariable int id)
    {
        userRepository.deleteById(id);
    }
    
}
