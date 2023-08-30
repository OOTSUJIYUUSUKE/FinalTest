package com.example.RaiseTech11.controller;

import com.example.RaiseTech11.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserResponse {
    private final String name;

    public UserResponse(User name) {
        this.name = name.getName();
    }
}
