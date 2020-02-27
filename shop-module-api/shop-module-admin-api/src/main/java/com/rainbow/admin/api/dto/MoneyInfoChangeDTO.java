package com.rainbow.admin.api.dto;

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
@ApiModel(value = "MoneyInfoChangeDTO", description = "资金信息修改")
public class MoneyInfoChangeDTO extends BaseDTO {
    @ApiModelProperty(value = "订单id")
    private Long id;

    @ApiModelProperty(value = "邮递费用")
    private BigDecimal deliverFee;

    @ApiModelProperty(value = "促销费用")
    private BigDecimal promotionAmount;
}
