package com.rainbow.admin;

import com.rainbow.admin.entity.Administrator;
import com.rainbow.admin.mapper.AdministratorMapper;
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
    private AdministratorMapper administratorMapper;

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
        Administrator administrator = new Administrator();
        administrator.setId(3L);
        administrator.setCreateTime(LocalDateTime.now());
        administrator.setDelStatus(DelFlagEnum.NO.getValue());
        administrator.setLastLoginTime(LocalDateTime.now());
        administrator.setMobile("13690724216");
        administrator.setUserName("vigo");
        administrator.setSalt(salt);
        administrator.setPassword(dbPassword);
        administrator.setUpdateTime(LocalDateTime.now());
        administrator.setLastLoginTime(LocalDateTime.now());
        administratorMapper.insert(administrator);
    }


    @Test
    public void testCheck() {
        String formPassword = MD5Utils.encodeByMd5AndSalt("admin","a1b2c3d4");
        String serverPassword = MD5Utils.encodeByMd5AndSalt(formPassword,"e8iN9Zpb");
        System.out.println(serverPassword);
    }




}
