package com.rainbow.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rainbow.admin.api.vo.BrandSimpleVO;
import com.rainbow.admin.service.IBrandService;
import com.rainbow.common.dto.IdPageDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShopAdminApplication.class)
@ActiveProfiles("dev")
public class ServiceTest {

    @Resource
    private IBrandService brandService;


    @Test
    public void testBrand() {
        IdPageDTO idPageDTO = new IdPageDTO();
        idPageDTO.setId(20L);
        idPageDTO.setPageNum(1);
        idPageDTO.setPageSize(1);
        IPage<BrandSimpleVO> brandSimpleVOIPage = brandService.pageBrandByItem(idPageDTO);
        System.out.println(brandSimpleVOIPage.getSize());
    }

}
