package com.example.RaiseTech11.service;

import com.example.RaiseTech11.entity.Users;
import com.example.RaiseTech11.exception.ResourceNotFoundException;
import com.example.RaiseTech11.mapper.UsersMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsersServiceImplTest {
    @InjectMocks
    UsersServiceImpl usersServiceImpl;
    @Mock
    UsersMapper usersMapper;
    @Test
    public void 全てのユーザーの情報を返すこと() {
        List<Users> usersForm = new ArrayList<>();
        usersForm.add(new Users(1, "田中太郎", "19901010"));
        usersForm.add(new Users(2, "加藤大", "19951010"));
        usersForm.add(new Users(3, "斉藤直樹", "19801010"));
        doReturn(usersForm).when(usersMapper).findAll();
        List<Users> actual = usersServiceImpl.findAll();
        assertThat(actual).isEqualTo(usersForm);
        verify(usersMapper, times(1)).findAll();
    }
    @Test
    public void  存在するユーザーのIDを指定したときに正常にユーザーが返されること() throws ResourceNotFoundException {
        Optional<Users> users = Optional.of(new Users(1, "大辻友佑", "19950221"));
        doReturn(users).when(usersMapper).findById(1);
        Users actual = usersServiceImpl.findById(1);
        assertThat(actual).isEqualTo(new Users(1, "大辻友佑", "19950221"));
        verify(usersMapper, times(1)).findById(1);
    }
    @Test
    public void 指定したidのユーザーが存在しない時例外を返すこと() {
        
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}
