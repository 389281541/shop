package com.rainbow.api.vo;

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
@ApiModel(value = "SpuFullReductionSimpleVO", description = "spu 满减视图对象")
public class SpuFullReductionSimpleVO extends BaseDTO {

    @ApiModelProperty(value = "SPUID")
    private Long spuId;

    @ApiModelProperty(value = "满减价格")
    private BigDecimal fullPrice;

    @ApiModelProperty(value = "扣减价格")
    private BigDecimal reducePrice;
}
