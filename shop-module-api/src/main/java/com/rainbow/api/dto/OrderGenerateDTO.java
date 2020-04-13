package com.rainbow.api.dto;

import com.rainbow.common.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * 订单生成DTO
 *
 * @author lujinwei
 * @since 2020/3/20
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "OrderGenerateDTO", description = "订单生成DTO")
public class OrderGenerateDTO extends BaseDTO {

    @ApiModelProperty(value = "优惠券ID")
    private Long couponId;

    @ApiModelProperty(value = "地址ID")
    private Long addressId;

    @ApiModelProperty(value = "支付方式")
    private Integer payType;

    @ApiModelProperty(value = "送货方式 0-快递 1-邮政")
    private Integer deliverMode;

    @ApiModelProperty(value = "运费")
    private BigDecimal deliverFee;

    @ApiModelProperty(value = "使用的积分数")
    private Integer useIntegration;

    @ApiModelProperty(value = "用户ID", hidden = true)
    private Long customerId;
}
