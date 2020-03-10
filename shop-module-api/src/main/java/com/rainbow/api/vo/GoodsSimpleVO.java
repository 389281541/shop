package com.rainbow.api.vo;

import com.rainbow.common.vo.IdNameVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * 商品列表VO
 *
 * @author lujinwei
 * @since 2020/3/8
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "GoodsSimpleVO", description = "商品VO")
public class GoodsSimpleVO extends IdNameVO {

    @ApiModelProperty(value = "商品sku中最低价格")
    private BigDecimal minPrice;

    @ApiModelProperty(value = "封面图")
    private String coverImg;

    @ApiModelProperty(value = "销量")
    private Integer sale;

    @ApiModelProperty(value = "商店")
    private IdNameVO shop;

}
