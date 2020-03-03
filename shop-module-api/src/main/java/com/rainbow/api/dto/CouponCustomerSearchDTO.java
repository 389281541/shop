package com.rainbow.api.dto;

import com.rainbow.common.dto.PageDTO;
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
@ApiModel(value = "CouponReceiverSearchDTO", description = "优惠券领取DTO")
public class CouponCustomerSearchDTO extends PageDTO {

    @ApiModelProperty(value = "优惠券ID")
    @NotNull(message = "优惠券ID不能为空")
    private Long couponId;

    @ApiModelProperty(value = "优惠券ID 0->未使用；1->已使用；2->已过期")
    private Integer useStatus;

    @ApiModelProperty(value = "订单编号")
    private String orderNo;
}
