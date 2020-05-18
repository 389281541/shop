package com.rainbow.api.dto;

import com.rainbow.common.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * 支付参数
 *
 * @author lujinwei
 * @since 2020/4/7
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "ParentOrderNoDTO", description = "父订单号DTO")
public class ParentOrderNoDTO extends BaseDTO {

    @ApiModelProperty(value = "父订单")
    @NotNull(message = "订单")
    private String parentOrderNo;

    @ApiModelProperty(value = "用户ID", hidden = true)
    private Long customerId;
}
