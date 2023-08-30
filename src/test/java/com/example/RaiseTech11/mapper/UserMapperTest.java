package com.example.RaiseTech11.mapper;

import com.example.RaiseTech11.entity.User;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.spring.api.DBRider;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DBRider
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    @DataSet(value = "datasets/users.yml")
    @Transactional
    void 全てのユーザーの情報を返すこと() {
        List<User> users = userMapper.findAll();
        assertThat(users)
                .hasSize(3)
                .contains(
                        new User(1, "福田 圭", "19360801"),
                        new User(2, "宇野 香織", "19860722"),
                        new User(3, "鈴木 佳史", "19641005")
                );
    }

    @Test
    @DataSet(value = "datasets/users.yml")
    @Transactional
    void 指定したidのユーザーを取得すること() {
        Optional<User> user = userMapper.findById(1);
        assertThat(user).contains(new User(1, "福田 圭", "19360801"));
    }

    @Test
    @DataSet(value = "datasets/users.yml")
    @Transactional
    void 指定したidが存在しないとき空のOptionalを返すこと() {
        Optional<User> user = userMapper.findById(99);
        assertThat(user).isEmpty();
    }

    @Test
    @DataSet(value = "datasets/users.yml")
    @ExpectedDataSet(value = "datasets/insert-users.yml", ignoreCols = "id")
    @Transactional
    void データ登録ができ既存のIDより大きい数字のIDが採番されること() {
        User user = new User("長谷川 晃義", "19390128");
        userMapper.insert(user);
        assertThat(user.getId()).isGreaterThan(3);
    }

    @Test
    @DataSet(value = "datasets/users.yml")
    @ExpectedDataSet(value = "datasets/update-users.yml")
    @Transactional
    void 指定したIDのデータを入力データで更新すること() {
        userMapper.update(1, "大辻 友佑", "19950221");
    }

    @Test
    @DataSet(value = "datasets/users.yml")
    @ExpectedDataSet(value = "datasets/delete-users.yml")
    @Transactional
    void 指定したIDのデータを削除すること() {
        userMapper.delete(1);
    }
}
