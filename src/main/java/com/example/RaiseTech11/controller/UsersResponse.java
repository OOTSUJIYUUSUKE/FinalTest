package com.example.RaiseTech11.controller;

import com.example.RaiseTech11.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UsersResponse {
    private final String name;

    public UsersResponse(Users name) {
        this.name = name.getName();
    }
}
