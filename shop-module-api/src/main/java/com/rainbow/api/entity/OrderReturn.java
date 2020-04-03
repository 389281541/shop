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
 * 退单
 *
 * @author lujinwei
 * @since 2020-03-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("order_return")
@ApiModel(value="OrderReturn对象", description="退单")
public class OrderReturn extends Model<OrderReturn> {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "店铺ID")
    @TableField("shop_id")
    private Long shopId;

    @ApiModelProperty(value = "类型 0-退款 1-退货退款")
    @TableField("type")
    private Integer type;

    @ApiModelProperty(value = "收货地址表")
    @TableField("shop_address")
    private String shopAddress;

    @ApiModelProperty(value = "收货人")
    @TableField("receiver_phone")
    private String receiverPhone;

    @ApiModelProperty(value = "收货人名称")
    @TableField("receiver_name")
    private String receiverName;

    @ApiModelProperty(value = "订单id")
    @TableField("order_id")
    private Long orderId;

    @ApiModelProperty(value = "订单编号")
    @TableField("order_no")
    private String orderNo;

    @ApiModelProperty(value = "申请时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "退货人姓名")
    @TableField("user_name")
    private String userName;

    @ApiModelProperty(value = "退货人电话")
    @TableField("user_phone")
    private String userPhone;

    @ApiModelProperty(value = "退款金额")
    @TableField("return_amount")
    private BigDecimal returnAmount;

    @ApiModelProperty(value = "申请状态：0->待处理；1->退货中；2->已完成；3->已拒绝")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "处理时间")
    @TableField("handle_time")
    private LocalDateTime handleTime;

    @ApiModelProperty(value = "订单实际支付价格")
    @TableField("pay_price")
    private BigDecimal payPrice;

    @ApiModelProperty(value = "原因")
    @TableField("reason")
    private String reason;

    @ApiModelProperty(value = "描述")
    @TableField("description")
    private String description;

    @ApiModelProperty(value = "凭证图片，以逗号隔开")
    @TableField("proof_pic")
    private String proofPic;

    @ApiModelProperty(value = "处理备注")
    @TableField("handle_note")
    private String handleNote;

    @ApiModelProperty(value = "收货时间")
    @TableField("receive_time")
    private LocalDateTime receiveTime;

    @ApiModelProperty(value = "收货备注")
    @TableField("receive_note")
    private String receiveNote;


}
