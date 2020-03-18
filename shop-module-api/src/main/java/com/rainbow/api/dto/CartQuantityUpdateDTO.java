package com.rainbow.api.dto;

import com.rainbow.common.dto.IdDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;

/**
 * 购物车更新数量DTO
 *
 * @author lujinwei
 * @since 2020/3/16
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "CartQuantityUpdateDTO", description = "购物车更新数量DTO")
public class CartQuantityUpdateDTO extends IdDTO {

    @ApiModelProperty(value = "购买数量")
    @Min(value = 1, message = "数量不能少于1")
    private Integer quantity;

    @ApiModelProperty(value = "购买数量", hidden = true)
    private Long customerId;
}
