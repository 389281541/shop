package com.rainbow.admin;

import com.rainbow.admin.entity.User;
import com.rainbow.admin.mapper.UserMapper;
import com.rainbow.common.enums.DelFlagEnum;
import com.rainbow.common.util.MD5Utils;
import com.rainbow.common.util.PasswordUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

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
@ActiveProfiles("local")
public class UserMapperTest {

    @Resource
    private UserMapper userMapper;

    @Test
    @Transactional
    public void testSave() {

        //生成服务器盐
        String salt = PasswordUtils.randomStr(8);
        System.out.println("server salt:" + salt);
        //传输过来的password进行加密
        String formPasswd = MD5Utils.encodeByMd5AndSalt("vigo", "a1b2c3d4");
        System.out.println("form password:" + formPasswd);
        //生成服务器密码
        String dbPassword = MD5Utils.encodeByMd5AndSalt(formPasswd,salt);
        System.out.println("db password:" + dbPassword);
        User user = new User();
        user.setId(3L);
        user.setCreateTime(LocalDateTime.now());
        user.setDelStatus(DelFlagEnum.NO.getValue());
        user.setLastLoginTime(LocalDateTime.now());
        user.setMobile("13690724216");
        user.setUserName("vigo");
        user.setSalt(salt);
        user.setPassword(dbPassword);
        user.setUpdateTime(LocalDateTime.now());
        user.setLastLoginTime(LocalDateTime.now());
        userMapper.insert(user);
    }


    @Test
    public void testCheck() {
        String formPassword = MD5Utils.encodeByMd5AndSalt("admin","a1b2c3d4");
        String serverPassword = MD5Utils.encodeByMd5AndSalt(formPassword,"Vcm8GOND");
        System.out.println(serverPassword);
    }




}
