package com.rainbow.api.dto;

import com.rainbow.common.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "GoConfirmOrderDTO", description = "秒杀商品DTO")
public class GoConfirmOrderDTO extends BaseDTO {

    @ApiModelProperty(value = "skuId")
    @NotNull(message = "skuId不能为空")
    private Long skuId;

    @ApiModelProperty(value = "购买数量")
    @NotNull(message = "购买数量不能为空")
    private Integer quantity;

    @ApiModelProperty(value = "秒杀活动ID")
    @NotNull(message = "秒杀活动ID")
    private Long flashPromotionId;

    @ApiModelProperty(value = "秒杀时间段ID")
    @NotNull(message = "秒杀时间段ID")
    private Long flashPromotionSessionId;

    @ApiModelProperty(value = "秒杀商品数量")
    @NotNull(message = "秒杀商品数量")
    private Long flashPromotionNum;

    @ApiModelProperty(value = "秒杀价格")
    @NotNull(message = "秒杀价格不能为空")
    private BigDecimal flashPrice;

    @ApiModelProperty(value = "用户ID", hidden = true)
    private Long customerId;
}
