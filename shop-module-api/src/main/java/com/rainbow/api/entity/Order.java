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
 * 订单表
 *
 * @author lujinwei
 * @since 2020-02-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("order_")
@ApiModel(value="Order对象", description="订单表")
public class Order extends Model<Order> {

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "订单编号")
    @TableField("order_no")
    private String orderNo;

    @ApiModelProperty(value = "用户ID")
    @TableField("customer_id")
    private Long customerId;

    @ApiModelProperty(value = "用户名称")
    @TableField("customer_name")
    private String customerName;

    @ApiModelProperty(value = "支付方式 0：支付宝，1：微信，2：银行卡")
    @TableField("pay_type")
    private Integer payType;

    @ApiModelProperty(value = "订单状态 0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "订单类型：0->正常订单；1->秒杀订单")
    @TableField("order_type")
    private Integer orderType;

    @ApiModelProperty(value = "自动确认时间（单位为天）")
    @TableField("auto_confirm_day")
    private Integer autoConfirmDay;

    @ApiModelProperty(value = "支付交易号")
    @TableField("trade_no")
    private Long tradeNo;

    @ApiModelProperty(value = "促销金额")
    @TableField("promotion_amount")
    private BigDecimal promotionAmount;

    @ApiModelProperty(value = "积分金额")
    @TableField("integration_amount")
    private BigDecimal integrationAmount;

    @ApiModelProperty(value = "活动信息")
    @TableField("promotion_info")
    private String promotionInfo;

    @ApiModelProperty(value = "该订单可以获得的积分")
    @TableField("integration_award")
    private Integer integrationAward;

    @ApiModelProperty(value = "优惠券金额")
    @TableField("coupon_amount")
    private BigDecimal couponAmount;

    @ApiModelProperty(value = "支付金额")
    @TableField("total_amount")
    private BigDecimal totalAmount;

    @ApiModelProperty(value = "物流ID")
    @TableField("flow_id")
    private Long flowId;

    @ApiModelProperty(value = "物流公司(配送方式)")
    @TableField("delivery_company")
    private String deliveryCompany;

    @ApiModelProperty(value = "物流单号")
    @TableField("delivery_bill_no")
    private String deliveryBillNo;

    @ApiModelProperty(value = "配送方式：0-快递 1-自取")
    @TableField("deliver_mode")
    private Integer deliverMode;

    @ApiModelProperty(value = "运费")
    @TableField("deliver_fee")
    private BigDecimal deliverFee;

    @ApiModelProperty(value = "收货人姓名")
    @TableField("receiver_name")
    private String receiverName;

    @ApiModelProperty(value = "收货人电话")
    @TableField("receiver_phone")
    private String receiverPhone;

    @ApiModelProperty(value = "收货人邮编")
    @TableField("receiver_post_code")
    private String receiverPostCode;

    @ApiModelProperty(value = "省份/直辖市")
    @TableField("receiver_province")
    private String receiverProvince;

    @ApiModelProperty(value = "城市")
    @TableField("receiver_city")
    private String receiverCity;

    @ApiModelProperty(value = "区")
    @TableField("receiver_region")
    private String receiverRegion;

    @ApiModelProperty(value = "详细地址")
    @TableField("receiver_detail_address")
    private String receiverDetailAddress;

    @ApiModelProperty(value = "订单备注")
    @TableField("note")
    private String note;

    @ApiModelProperty(value = "确认收货状态：0->未确认；1->已确认")
    @TableField("confirm_status")
    private Integer confirmStatus;

    @ApiModelProperty(value = "下单时使用的积分")
    @TableField("use_integration")
    private Integer useIntegration;

    @ApiModelProperty(value = "下单时使用的优惠券")
    @TableField("use_coupon_id")
    private Long useCouponId;

    @ApiModelProperty(value = "支付时间")
    @TableField("payment_time")
    private LocalDateTime paymentTime;

    @ApiModelProperty(value = "发货时间")
    @TableField("delivery_time")
    private LocalDateTime deliveryTime;

    @ApiModelProperty(value = "确认收货时间")
    @TableField("receive_time")
    private LocalDateTime receiveTime;

    @ApiModelProperty(value = "删除状态 0-未删除 1-已删除")
    @TableField("del_status")
    private Integer delStatus;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;


}
