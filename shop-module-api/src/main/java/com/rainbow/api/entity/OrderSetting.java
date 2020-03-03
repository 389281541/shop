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

/**
 * 订单设置表
 *
 * @author lujinwei
 * @since 2020-02-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("order_setting")
@ApiModel(value="OrderSetting对象", description="订单设置表")
public class OrderSetting extends Model<OrderSetting> {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "秒杀订单超时关闭时间(分)")
    @TableField("flash_order_overtime")
    private Integer flashOrderOvertime;

    @ApiModelProperty(value = "正常订单超时时间(分)")
    @TableField("normal_order_overtime")
    private Integer normalOrderOvertime;

    @ApiModelProperty(value = "发货后自动确认收货时间（天）")
    @TableField("confirm_overtime")
    private Integer confirmOvertime;

    @ApiModelProperty(value = "自动完成交易时间，不能申请售后（天）")
    @TableField("finish_overtime")
    private Integer finishOvertime;

    @ApiModelProperty(value = "订单完成后自动好评时间（天）")
    @TableField("comment_overtime")
    private Integer commentOvertime;


}
