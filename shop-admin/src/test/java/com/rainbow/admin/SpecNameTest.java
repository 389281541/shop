package com.rainbow.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rainbow.admin.api.dto.SpecNameSearchDTO;
import com.rainbow.admin.entity.SpecName;
import com.rainbow.admin.mapper.SpecNameMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShopAdminApplication.class)
@ActiveProfiles("local")
public class SpecNameTest {

    @Resource
    private SpecNameMapper specNameMapper;
//https://cloud.tencent.com/developer/article/1432227
    @Test
    public void test() {
        SpecNameSearchDTO param = new SpecNameSearchDTO();
        param.setName("颜色");
        Page<SpecName> page = new Page<>(1, 5);
        List<SpecName> specNames = specNameMapper.pageSpecName(param, page);
        System.out.println(specNames);
    }
}