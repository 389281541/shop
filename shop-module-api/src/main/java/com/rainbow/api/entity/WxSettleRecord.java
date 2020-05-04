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
 * 团购微信结算记录
 *
 * @author lujinwei
 * @since 2020-05-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("wx_settle_record")
@ApiModel(value="WxSettleRecord对象", description="团购微信结算记录")
public class WxSettleRecord extends Model<WxSettleRecord> {

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "打款方式 0：企业付款到微信 1：标记打款")
    @TableField("payment_type")
    private Integer paymentType;

    @ApiModelProperty(value = "付款状态")
    @TableField("settle_status")
    private String settleStatus;

    @ApiModelProperty(value = "打款流水号")
    @TableField("payment_no")
    private Long paymentNo;

    @ApiModelProperty(value = "原订单金额，单位为分")
    @TableField("order_money")
    private Integer orderMoney;

    @ApiModelProperty(value = "微信支付订单号")
    @TableField("transaction_id")
    private String transactionId;

    @ApiModelProperty(value = "结算金额，单位为分")
    @TableField("settle_money")
    private Integer settleMoney;

    @ApiModelProperty(value = "手续费，单位为分")
    @TableField("fee_money")
    private Integer feeMoney;

    @ApiModelProperty(value = "交易单号")
    @TableField("trade_no")
    private String tradeNo;

    @ApiModelProperty(value = "交易成功日期")
    @TableField("trade_time")
    private LocalDateTime tradeTime;

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
