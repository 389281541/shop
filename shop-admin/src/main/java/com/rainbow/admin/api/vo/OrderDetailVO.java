package com.rainbow.admin.api.vo;


import com.rainbow.admin.entity.OrderSku;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "OrderDetailVO", description = "订单VO")
public class OrderDetailVO extends OrderSimpleVO {

    @ApiModelProperty(value = "自动确认时间（单位为天）")
    private Integer autoConfirmDay;

    @ApiModelProperty(value = "支付交易号")
    private Long tradeNo;

    @ApiModelProperty(value = "促销金额")
    private BigDecimal promotionAmount;

    @ApiModelProperty(value = "积分金额")
    private BigDecimal integrationAmount;

    @ApiModelProperty(value = "用户ID")
    private Long customerId;

    @ApiModelProperty(value = "活动信息")
    private String promotionInfo;

    @ApiModelProperty(value = "该订单可以获得的积分")
    private Integer integrationAward;

    @ApiModelProperty(value = "优惠券金额")
    private BigDecimal couponAmount;

    @ApiModelProperty(value = "物流ID")
    private Long flowId;

    @ApiModelProperty(value = "物流公司(配送方式)")
    private String deliveryCompany;

    @ApiModelProperty(value = "物流单号")
    private String deliveryBillNo;

    @ApiModelProperty(value = "配送方式：0-快递 1-自取")
    private Integer deliverMode;

    @ApiModelProperty(value = "运费")
    private BigDecimal deliverFee;

    @ApiModelProperty(value = "订单备注")
    private String note;

    @ApiModelProperty(value = "确认收货状态：0->未确认；1->已确认")
    private Integer confirmStatus;

    @ApiModelProperty(value = "下单时使用的积分")
    private Integer useIntegration;

    @ApiModelProperty(value = "支付时间")
    private LocalDateTime paymentTime;

    @ApiModelProperty(value = "发货时间")
    private LocalDateTime deliveryTime;

    @ApiModelProperty(value = "确认收货时间")
    private LocalDateTime receiveTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "商品信息")
    private List<OrderSku> orderSkuList;

}
