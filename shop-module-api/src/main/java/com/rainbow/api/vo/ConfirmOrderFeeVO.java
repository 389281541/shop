package com.rainbow.api.vo;

import com.rainbow.common.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * 确认订单计算VO
 *
 * @author lujinwei
 * @since 2020/3/18
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "ConfirmOrderFeeVO", description = "确认订单费用VO")
public class ConfirmOrderFeeVO extends BaseDTO {

    @ApiModelProperty(value = "商品总金额")
    private BigDecimal totalAmount;

    @ApiModelProperty(value = "运费金额")
    private BigDecimal deliverAmount;

    @ApiModelProperty(value = "优惠金额")
    private BigDecimal promotionAmount;

    @ApiModelProperty(value = "最终支付金额")
    private BigDecimal payAmount;
}
