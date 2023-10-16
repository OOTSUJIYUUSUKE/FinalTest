package com.example.RaiseTech11.controller;

import com.example.RaiseTech11.entity.User;
import com.example.RaiseTech11.form.CreateForm;
import com.example.RaiseTech11.form.UpdateForm;
import com.example.RaiseTech11.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


import java.net.URI;
import java.util.List;
import java.util.Map;

@RequestMapping("/users")
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public List<UserResponse> findAll() {
        return userService.findAll().stream().map(UserResponse::new).toList();
    }

    @GetMapping("/id")
    public UserResponse findById(@RequestParam(value = "id") int id) {
        User user = userService.findById(id);
        UserResponse conversionUser = new UserResponse(user);
        return conversionUser;
    }

    @PostMapping()
    public ResponseEntity<Map<String, String>> createUser
            (@RequestBody @Validated CreateForm form, UriComponentsBuilder uriComponentsBuilder) {
        User user = userService.create(form.getName(), form.getBirthday());
        URI url = uriComponentsBuilder.path("/users/id/" + user.getId())
                .build()
                .toUri();
        return ResponseEntity.created(url).body(Map.of("message", "new user successfully created"));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Map<String, String>> patchUser
            (@PathVariable("id")int id, @RequestBody @Validated UpdateForm form) {
        userService.update(id, form.getName(), form.getBirthday());
        return ResponseEntity.ok(Map.of("message", "the user successfully updated"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable("id")int id) {
        userService.delete(id);
        return ResponseEntity.ok(Map.of("message", "the user successfully deleted"));
    }
}
