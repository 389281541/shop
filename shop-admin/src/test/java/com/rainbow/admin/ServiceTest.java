package com.rainbow.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rainbow.admin.api.dto.BrandDTO;
import com.rainbow.admin.api.vo.BrandSimpleVO;
import com.rainbow.admin.service.IBrandService;
import com.rainbow.common.dto.IdDTO;
import com.rainbow.common.dto.IdNamePageDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShopAdminApplication.class)
@ActiveProfiles("local")
public class ServiceTest {

    @Resource
    private IBrandService brandService;


    @Test
    public void testPageBrand() {
        IdNamePageDTO idPageDTO = new IdNamePageDTO();
        idPageDTO.setId(20L);
        idPageDTO.setName("海");
        idPageDTO.setPageNum(1);
        idPageDTO.setPageSize(20);
        IPage<BrandSimpleVO> brandSimpleVOIPage = brandService.pageBrandByItem(idPageDTO);
        System.out.println(brandSimpleVOIPage.getSize());
    }

    @Test
    public void testAddBrand() {
        BrandDTO brandDTO = new BrandDTO();
        brandDTO.setName("清扬");
        brandDTO.setDescription("清扬男士洗发水，无屑可击");
        brandDTO.setItemId(20L);

        int result = brandService.addBrand(brandDTO);
        System.out.println(result);
    }

    @Test
    public void testRemoveBrand() {
        IdDTO idDTO = new IdDTO();
        idDTO.setId(10L);
        Integer integer = brandService.removeBrand(idDTO);
        System.out.println(integer);
    }

}
