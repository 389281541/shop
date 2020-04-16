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
 * 秒杀商品VO
 *
 * @author lujinwei
 * @since 2020/4/14
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "FlashGoodsSimpleVO", description = "秒杀商品列表VO")
public class FlashGoodsSimpleVO extends IdNameVO {

    @ApiModelProperty(value = "秒杀活动ID")
    private Long flashPromotionId;

    @ApiModelProperty(value = "封面图")
    private String coverImg;

    @ApiModelProperty(value = "秒杀价格")
    private BigDecimal flashPrice;

    @ApiModelProperty(value = "秒杀价格")
    private BigDecimal originalPrice;

}
