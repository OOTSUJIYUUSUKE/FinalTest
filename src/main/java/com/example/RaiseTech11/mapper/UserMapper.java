package com.example.RaiseTech11.mapper;

import com.example.RaiseTech11.entity.User;
import com.example.RaiseTech11.form.CreateForm;
import org.apache.ibatis.annotations.Mapper;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {

    public List<User> findAll();

    public Optional<User> findById(int id);

    public void insert(User user);

    public void update(int id, String name, String birthday);

    public void delete(int id);
}
