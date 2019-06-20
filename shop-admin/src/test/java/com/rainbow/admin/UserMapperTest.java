package com.rainbow.admin;

import com.rainbow.admin.entity.User;
import com.rainbow.admin.mapper.user.UserMapper;
import com.rainbow.common.enums.DelFlagEnum;
import com.rainbow.common.util.MD5Utils;
import com.rainbow.common.util.PasswordUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
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
@SpringBootTest(classes = ShopAdminApplication.class)
@ActiveProfiles("dev")
public class UserMapperTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void testSave() {

        //生成服务器盐
        String salt = PasswordUtils.randomStr(8);
        System.out.println("server salt:" + salt);
        //传输过来的password进行加密
        String formPasswd = MD5Utils.encodeByMd5AndSalt("rainbow", "a1b2c3d4");
        System.out.println("form password:" + formPasswd);
        //生成服务器密码
        String dbPassword = MD5Utils.encodeByMd5AndSalt(formPasswd,salt);
        System.out.println("db password:" + dbPassword);
        User user = new User();
        user.setId(2L);
        user.setCreateTime(LocalDateTime.now());
        user.setDelStatus(DelFlagEnum.NO.getValue());
        user.setLastLoginTime(LocalDateTime.now());
        user.setMobile("15801248054");
        user.setUserName("rainbow");
        user.setSalt(salt);
        user.setPassword(dbPassword);
        user.setUpdateTime(LocalDateTime.now());
        user.setLastLoginTime(LocalDateTime.now());
        userMapper.insert(user);
    }




}
