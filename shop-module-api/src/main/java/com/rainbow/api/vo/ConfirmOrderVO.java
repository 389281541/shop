package com.rainbow.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 确认订单VO
 *
 * @author lujinwei
 * @since 2020/3/18
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "ConfirmOrderVO", description = "确认订单VO")
public class ConfirmOrderVO extends  ConfirmOrderFeeVO {

    @ApiModelProperty(value = "送货方式 ")
    private Integer deliverMode;

    @ApiModelProperty(value = "支付方式")
    private Integer payType;

    @ApiModelProperty(value = "积分使用规则")
    private IntegrationRuleSettingVO integrationRuleSetting;

    @ApiModelProperty(value = "购物车")
    private List<CartPromotionVO>  cartPromotionList;

    @ApiModelProperty(value = "收货地址")
    private List<CustomerAddressVO> customerAddressList;

    @ApiModelProperty(value = "用户可用优惠券")
    private List<CouponCustomerSimpleVO> couponCustomerList;

}
