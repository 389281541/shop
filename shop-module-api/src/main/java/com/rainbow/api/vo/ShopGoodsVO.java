package com.rainbow.api.vo;

import com.rainbow.common.vo.IdNameAvatarVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 店铺商品VO
 *
 * @author lujinwei
 * @since 2020/3/10
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "ShopGoodsVO", description = "商铺商品VO")
public class ShopGoodsVO extends IdNameAvatarVO {

    @ApiModelProperty(value = "店铺新品列表")
    private List<GoodsSimpleVO> newGoodsList;

    @ApiModelProperty(value = "店铺热销商品列表")
    private List<GoodsSimpleVO> hotGoodsList;

}
