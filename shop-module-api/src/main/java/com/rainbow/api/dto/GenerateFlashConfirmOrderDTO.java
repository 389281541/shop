package com.rainbow.api.dto;

import com.rainbow.common.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "FlashSpuSearchDTO", description = "秒杀商品搜索对象")
public class GenerateFlashConfirmOrderDTO extends BaseDTO {

    @ApiModelProperty(value = "skuId")
    @NotNull(message = "skuId不能为空")
    private Long skuId;

    @ApiModelProperty(value = "用户ID", hidden = true)
    private Long customerId;
}
