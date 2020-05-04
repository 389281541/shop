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
 * 团购支付订单
 *
 * @author lujinwei
 * @since 2020-05-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("wx_pay_order")
@ApiModel(value="WxPayOrder对象", description="团购支付订单")
public class WxPayOrder extends Model<WxPayOrder> {

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "交易状态")
    @TableField("order_status")
    private Integer orderStatus;

    @ApiModelProperty(value = "支付订单号")
    @TableField("pay_order_no")
    private Long payOrderNo;

    @ApiModelProperty(value = "有效交易订单号")
    @TableField("trade_order_no")
    private Long tradeOrderNo;

    @ApiModelProperty(value = "订单实付金额（分）")
    @TableField("pay_money")
    private Long payMoney;

    @ApiModelProperty(value = "支付成功通知业务库接口")
    @TableField("callback_url")
    private String callbackUrl;

    @ApiModelProperty(value = "支付成功通知支付库接口")
    @TableField("notify_url")
    private String notifyUrl;

    @ApiModelProperty(value = "商品描述")
    @TableField("goods_body")
    private String goodsBody;

    @ApiModelProperty(value = "商品详情")
    @TableField("goods_detail")
    private String goodsDetail;

    @ApiModelProperty(value = "支付时间")
    @TableField("trade_succ_time")
    private LocalDateTime tradeSuccTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "支付userid")
    @TableField("shop_id")
    private Long shopId;


}
