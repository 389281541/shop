package com.rainbow.api.dto;

import com.rainbow.common.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "FlashPromotionSpuSaveDTO", description = "秒杀商品新增传输对象")
public class FlashPromotionSpuSaveDTO extends BaseDTO {

    @ApiModelProperty(value = "秒杀活动ID")
    private Long flashPromotionId;

    @ApiModelProperty(value = "秒杀时间段ID")
    private Long flashPromotionSessionId;

    @ApiModelProperty(value = "商品ID")
    private Long spuId;

    @ApiModelProperty(value = "限时折扣价格")
    private BigDecimal flashDiscountPrice;

    @ApiModelProperty(value = "限时购数量")
    private Integer flashPromotionNum;

    @ApiModelProperty(value = "每人限购数量")
    private Integer flashPromotionLimit;

    @ApiModelProperty(value = "排序")
    private Integer sortId;

}
