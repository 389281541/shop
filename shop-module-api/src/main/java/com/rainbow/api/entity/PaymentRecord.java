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

import java.time.LocalDateTime;

/**
 * 支付记录表
 *
 * @author lujinwei
 * @since 2020-04-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("payment_record")
@ApiModel(value="PaymentRecord对象", description="支付记录表")
public class PaymentRecord extends Model<PaymentRecord> {

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户ID")
    @TableField("customer_id")
    private Long customerId;

    @ApiModelProperty(value = "父订单编号")
    @TableField("p_order_no")
    private String parentOrderNo;

    @ApiModelProperty(value = "交易流水号")
    @TableField("trade_no")
    private String tradeNo;

    @ApiModelProperty(value = "交易状态")
    @TableField("trade_status")
    private String tradeStatus;

    @ApiModelProperty(value = "支付平台 0：支付宝，1：微信")
    @TableField("pay_platform")
    private Integer payPlatform;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;


}
