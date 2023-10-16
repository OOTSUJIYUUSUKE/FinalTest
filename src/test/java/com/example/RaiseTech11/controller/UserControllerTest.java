package com.example.RaiseTech11.controller;

import com.example.RaiseTech11.entity.User;
import com.example.RaiseTech11.form.CreateForm;
import com.example.RaiseTech11.form.UpdateForm;
import com.example.RaiseTech11.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.comparator.CustomComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @InjectMocks
    UserController userController;

    @MockBean
    UserServiceImpl userServiceImpl;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void 全てのユーザーのnameを返すこと() throws Exception {
        List<User> users = new ArrayList<>();
        users.add(new User(1, "田中太郎", "19901010"));
        users.add(new User(2, "加藤大", "19951010"));
        users.add(new User(3, "斉藤直樹", "19801010"));
        doReturn(users).when(userServiceImpl).findAll();
        mockMvc.perform(get("/users").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(MockMvcResultMatchers.content().json("""
                        [
                          {
                            "name": "田中太郎"
                          },
                          {
                            "name": "加藤大"
                          },
                          {
                            "name": "斉藤直樹"
                          }
                        ]
                         """));
    }

    @Test
    public void 指定したIDのユーザーのnameを返すこと() throws Exception {
        User user = new User(1, "田中太郎", "19901010");
        doReturn(user).when(userServiceImpl).findById(1);
        mockMvc.perform(get("/users/id?id=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
                        {
                          "name": "田中太郎"
                        }
                        """));
    }

    @Test
    public void 新規ユーザーの登録ができること() throws Exception {
        CreateForm createUser = new CreateForm("田中太郎", "19901010");
        doReturn(new User(1, "田中太郎", "19901010"))
                .when(userServiceImpl).create(createUser.getName(), createUser.getBirthday());

        String response = mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                 "id": "1",
                                 "name": "田中太郎",
                                 "birthday": "19901010"
                                }
                                """))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        JSONAssert.assertEquals("""
                {
                    "message" : "new user successfully created"
                }
                """, response, JSONCompareMode.STRICT);
    }

    @Test
    public void ユーザーの更新ができること() throws Exception {
        UpdateForm updateUser = new UpdateForm("大辻友佑", "19950221");
        doNothing().when(userServiceImpl).update(1, updateUser.getName(), updateUser.getBirthday());

        String response = mockMvc.perform(patch("/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                 "name": "大辻友佑",
                                 "birthday": "19950221"
                                }
                                """))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        JSONAssert.assertEquals("""
                {
                    "message" : "the user successfully updated"
                }
                """, response, JSONCompareMode.STRICT);
        verify(userServiceImpl, times(1)).update(1, updateUser.getName(), updateUser.getBirthday());
    }

    @Test
    public void ユーザーの削除ができること() throws Exception {
        doNothing().when(userServiceImpl).delete(1);

        String response = mockMvc.perform(delete("/users/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        JSONAssert.assertEquals("""
                {
                    "message" : "the user successfully deleted"
                }
                """, response, JSONCompareMode.STRICT);
        verify(userServiceImpl, times(1)).delete(1);
    }
}
