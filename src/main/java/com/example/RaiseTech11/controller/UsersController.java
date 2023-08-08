package com.example.RaiseTech11.controller;

import com.example.RaiseTech11.entity.Users;
import com.example.RaiseTech11.service.UsersService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


import java.net.URI;
import java.util.List;
import java.util.Map;

@RequestMapping("/users")
@RestController
public class UsersController {
    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping()
    public List<UsersResponse> findAll() {
        return usersService.findAll().stream().map(UsersResponse::new).toList();
    }
    @GetMapping("/id")
    public UsersResponse findById(@RequestParam(value = "id") int id) {
        Users user = usersService.findById(id);
        UsersResponse conversionUser = new UsersResponse(user);
        return conversionUser;
    }
    @PostMapping()
    public ResponseEntity<Map<String, String>> createUsers(@RequestBody @Validated Users newUser, BindingResult bindingResult, UriComponentsBuilder uriComponentsBuilder) {
        ModelMapper modelMapper = new ModelMapper();
        Users conversionUsers = modelMapper.map(newUser, Users.class);
        Users user = usersService.create(conversionUsers, bindingResult);
        URI url = uriComponentsBuilder.path("/users/id/" + user.getId())
                .build()
                .toUri();
        return ResponseEntity.created(url).body(Map.of("message", "new user successfully create"));
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Map<String, String>> patchUsers(@PathVariable("id")int id, @RequestBody @Validated Users newUser, BindingResult bindingResult, UriComponentsBuilder uriComponentsBuilder) {
        ModelMapper modelMapper = new ModelMapper();
        Users conversionUser = modelMapper.map(newUser, Users.class);
        usersService.update(id, conversionUser, bindingResult);
        URI url = uriComponentsBuilder.path("/users/id/" + id)
                .build()
                .toUri();
        return ResponseEntity.created(url).body(Map.of("message", "the user successfully update"));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteUsers(@PathVariable("id")int id) {
        usersService.delete(id);
        return ResponseEntity.ok(Map.of("message", "the user successfully delete"));
    }
}
