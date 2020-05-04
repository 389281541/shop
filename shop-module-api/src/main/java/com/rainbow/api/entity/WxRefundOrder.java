package com.rainbow.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 退款订单表
 *
 * @author lujinwei
 * @since 2020-05-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("wx_refund_order")
@ApiModel(value="WxRefundOrder对象", description="退款订单表")
public class WxRefundOrder extends Model<WxRefundOrder> {

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "退款状态 ")
    @TableField("refund_status")
    private Integer refundStatus;

    @ApiModelProperty(value = "支付库退款订单编号")
    @TableField("refund_order_no")
    private Long refundOrderNo;

    @ApiModelProperty(value = "支付订单编号")
    @TableField("pay_order_no")
    private Long payOrderNo;

    @ApiModelProperty(value = "交易订单编号")
    @TableField("trade_order_no")
    private Long tradeOrderNo;

    @ApiModelProperty(value = "退款原因")
    @TableField("refund_reason")
    private String refundReason;

    @ApiModelProperty(value = "订单退款金额，分")
    @TableField("refund_money")
    private BigDecimal refundMoney;

    @ApiModelProperty(value = "订单实付金额（分）")
    @TableField("pay_money")
    private Long payMoney;

    @ApiModelProperty(value = "退款结果通知业务库接口")
    @TableField("callback_url")
    private String callbackUrl;

    @ApiModelProperty(value = "退款状态变更通知地址")
    @TableField("notify_url")
    private String notifyUrl;

    @ApiModelProperty(value = "退款入账账户")
    @TableField("refund_recv_accout")
    private String refundRecvAccout;

    @ApiModelProperty(value = "ORIGINAL—原路退款 BALANCE—退回到余额")
    @TableField("refund_channel")
    private String refundChannel;

    @ApiModelProperty(value = "三方退款订单号")
    @TableField("third_refund_id")
    private String thirdRefundId;

    @ApiModelProperty(value = "退款处理完成时间")
    @TableField("finish_time")
    private LocalDateTime finishTime;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "支付userid")
    @TableField("shop_id")
    private Long shopId;


}
