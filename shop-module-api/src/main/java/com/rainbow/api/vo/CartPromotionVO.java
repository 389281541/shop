package com.rainbow.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * 购物车促销VO
 *
 * @author lujinwei
 * @since 2020/3/15
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "CartPromotionVO", description = "购物车促销VO")
public class CartPromotionVO extends CartSimpleVO {

    @ApiModelProperty(value = "促销类型：0->没有促销使用原价;1->使用限时促销价;2->使用满减价格;")
    private Integer promotionType;

    @ApiModelProperty(value = "分配到每项促销优惠金额")
    private BigDecimal perReduceAmount;

}
