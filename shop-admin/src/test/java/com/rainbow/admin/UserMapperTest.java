package com.rainbow.admin;

import com.rainbow.admin.entity.User;
import com.rainbow.admin.mapper.UserMapper;
import com.rainbow.common.enums.DelFlagEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * 用户相关单元
 *
 * @author lujinwei
 * @since 2019-06-15
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("local")
public class UserMapperTest {

    @Resource
    private UserMapper userMapper;


    @Test
    public void testSave() {
        User user = new User();
        user.setCreateTime(LocalDateTime.now());
        user.setDelStatus(DelFlagEnum.NO.getValue());
        user.setLastLoginTime(LocalDateTime.now());
        user.setMobile("15801248054");
        user.setUserName("admin");
        user.setSalt();
        user.setPassword();
        userMapper.insert();
    }


}
