package com.rainbow.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rainbow.admin.api.dto.BrandSaveDTO;
import com.rainbow.admin.api.vo.BrandSimpleVO;
import com.rainbow.admin.entity.RolePermission;
import com.rainbow.admin.mapper.AdministratorRoleMapper;
import com.rainbow.admin.mapper.BrandItemMapper;
import com.rainbow.admin.mapper.RolePermissionMapper;
import com.rainbow.admin.service.IBrandService;
import com.rainbow.common.dto.IdDTO;
import com.rainbow.common.dto.IdNamePageDTO;
import com.rainbow.common.model.KV;
import org.assertj.core.util.Lists;
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
public class BrandTest {

    @Resource
    private IBrandService brandService;

    @Resource
    private BrandItemMapper brandItemMapper;


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
        BrandSaveDTO brandDTO = new BrandSaveDTO();
        brandDTO.setName("清扬");
        brandDTO.setDescription("清扬男士洗发水，无屑可击");
        List<Long> itemIds = Lists.newArrayList();
        itemIds.add(20L);
        brandDTO.setItemIds(itemIds);

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

    @Test
    public void testEquesl() {
        Long a = -1L;
        System.out.println(a.equals(-1L));
    }

    @Test
    public void testBrandItemMapper() {
        List<KV<Long, Long>> list = brandItemMapper.getBrandCountByItemId();
        System.out.println(list);
    }

}
