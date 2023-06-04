package com.example.RaiseTech11.controller;

import com.example.RaiseTech11.entity.UsersForm;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UsersResponse {
    private final String name;

    public UsersResponse(UsersForm name) {
        this.name = name.getName();
    }
}
