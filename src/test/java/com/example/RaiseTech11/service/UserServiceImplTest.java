package com.example.RaiseTech11.service;

import com.example.RaiseTech11.entity.User;
import com.example.RaiseTech11.exception.ResourceNotFoundException;
import com.example.RaiseTech11.form.CreateForm;
import com.example.RaiseTech11.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @InjectMocks
    UserServiceImpl userServiceImpl;
    @Mock
    UserMapper userMapper;
    @Test
    public void 全てのユーザーの情報を返すこと() {
        List<User> user = new ArrayList<>();
        user.add(new User(1, "田中太郎", "19901010"));
        user.add(new User(2, "加藤大", "19951010"));
        user.add(new User(3, "斉藤直樹", "19801010"));
        doReturn(user).when(userMapper).findAll();
        List<User> actual = userServiceImpl.findAll();
        assertEquals(actual, user);
        verify(userMapper, times(1)).findAll();
    }

    @Test
    public void 指定したIDのユーザーデータを返すこと() {
        Optional<User> user = Optional.of(new User(1, "田中太郎", "19901010"));
        doReturn(user).when(userMapper).findById(1);
        User actual = userServiceImpl.findById(1);
        assertEquals(actual, new User(1, "田中太郎", "19901010"));
        verify(userMapper, times(1)).findById(1);
    }

    @Test
    public void 指定したIDのユーザーが存在しないとき例外を返すこと() {
        doReturn(Optional.empty()).when(userMapper).findById(99);
        assertThrows(ResourceNotFoundException.class, () -> userServiceImpl.findById(99));
        verify(userMapper, times(1)).findById(99);
    }

    @Test
    public void オートインクリメントで取ってきたIDに入力データが登録できること() {
        CreateForm form = new CreateForm("大辻友佑", "19950221");
        User user = new User(form.getName(), form.getBirthday());
        doNothing().when(userMapper).insert(user);
        //bindingResultがnullだとエラーが出るので以下2行でインスタンス生成をする。
        User examapleUser = new User();
        BindingResult bindingResult = new DataBinder(examapleUser).getBindingResult();
        userServiceImpl.create(form.getName(), form.getBirthday(), bindingResult);
        verify(userMapper, times(1)).insert(user);
    }

    @Test
    public void 指定したIDのデータを入力データで更新ができること() {
        doReturn(Optional.of(new User("田中太郎", "19901010"))).when(userMapper).findById(1);
        User user = new User(1, "加藤大", "19951010");
        //bindingResultがnullだとエラーが出るので以下2行でインスタンス生成をする。
        User examapleUser = new User();
        BindingResult bindingResult = new DataBinder(examapleUser).getBindingResult();
        userServiceImpl.update(1, user.getName(), user.getBirthday(), bindingResult);
        verify(userMapper, times(1)).findById(1);
        verify(userMapper, times(1)).update(1, user.getName(), user.getBirthday());
    }

    @Test
    public void 更新指定したIDが存在しないとき例外を返すこと() {
        doReturn(Optional.empty()).when(userMapper).findById(99);
        assertThrows(ResourceNotFoundException.class, () -> userServiceImpl.findById(99));
        verify(userMapper, times(1)).findById(99);
    }

    @Test
    void 指定したIDのデータが削除できること() {
        doReturn(Optional.of(new User("田中太郎", "19901010"))).when(userMapper).findById(1);
        User deletedUser = userServiceImpl.findById(1);
        userServiceImpl.delete(1);
        verify(userMapper, times(1)).findById(1);
        verify(userMapper, times(1)).delete(1);
    }

    @Test
    public void 削除指定したIDが存在しないとき例外を返すこと() {
        doReturn(Optional.empty()).when(userMapper).findById(99);
        assertThrows(ResourceNotFoundException.class, () -> userServiceImpl.findById(99));
        verify(userMapper, times(1)).findById(99);
        verify(userMapper, never()).delete(99);
    }
}
