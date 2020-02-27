package com.rainbow.admin;

import com.rainbow.admin.api.entity.Permission;
import com.rainbow.admin.api.entity.Role;
import com.rainbow.admin.service.IAdministratorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShopAdminApplication.class)
@ActiveProfiles("local")
public class PermissionTest {

    @Autowired
    private IAdministratorService administratorService;


    @Test
    public void testPermission() {
        List<Permission> permissions = administratorService.getPermissionByUserId(1L);
        System.out.println(permissions);
    }

    @Test
    public void testRole() {
        List<Role> roles = administratorService.getRoleByUserId(1L);
        System.out.println(roles);
    }

}
