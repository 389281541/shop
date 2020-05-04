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
 * 团购微信打款记录
 *
 * @author lujinwei
 * @since 2020-05-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("wx_payment_record")
@ApiModel(value="WxPaymentRecord对象", description="团购微信打款记录")
public class WxPaymentRecord extends Model<WxPaymentRecord> {

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "交易金额，单位为分")
    @TableField("trade_money")
    private BigDecimal tradeMoney;

    @ApiModelProperty(value = "手续费，单位为分")
    @TableField("fee_money")
    private BigDecimal feeMoney;

    @ApiModelProperty(value = "结算金额，单位为分")
    @TableField("settle_money")
    private BigDecimal settleMoney;

    @ApiModelProperty(value = "打款状态")
    @TableField("payment_status")
    private Integer paymentStatus;

    @ApiModelProperty(value = "收款人")
    @TableField("receiver")
    private String receiver;

    @ApiModelProperty(value = "收款人姓名")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "打款流水号")
    @TableField("payment_no")
    private Long paymentNo;

    @ApiModelProperty(value = "微信打款流水号")
    @TableField("wx_payment_no")
    private String wxPaymentNo;

    @ApiModelProperty(value = "终止打款原因")
    @TableField("reject_reason")
    private String rejectReason;

    @ApiModelProperty(value = "打款成功日期")
    @TableField("pay_success_time")
    private LocalDateTime paySuccessTime;

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
