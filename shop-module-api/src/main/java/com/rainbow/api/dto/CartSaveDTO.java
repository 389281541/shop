package com.rainbow.api.dto;

import com.rainbow.common.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 *  添加购物车DTO
 *
 * @author lujinwei
 * @since 2020/3/14
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "CartSaveDTO", description = "购物车添加DTO")
public class CartSaveDTO extends BaseDTO {

    @ApiModelProperty(value = "skuId")
    private Long skuId;

    @ApiModelProperty(value = "购买数量")
    private Integer quantity;

    @ApiModelProperty(value = "用户ID", hidden = true)
    private Long customerId;
}
