package com.rainbow.portal.mq;

import com.rainbow.api.vo.CartPromotionVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 秒杀消息
 *
 * @author lujinwei
 * @since 2020/4/23
 */
@Data
public class FlashMessage {

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

    @ApiModelProperty(value = "父订单号")
    private String parentOrderNO;

    @ApiModelProperty(value = "用户ID", hidden = true)
    private Long customerId;

    @ApiModelProperty(value = "购物车选项", hidden = true)
    List<CartPromotionVO> cartPromotionVOList;
}
