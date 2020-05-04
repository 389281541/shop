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
 * 团购订单表
 *
 * @author lujinwei
 * @since 2020-05-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("groupon_order")
@ApiModel(value="GrouponOrder对象", description="团购订单表")
public class GrouponOrder extends Model<GrouponOrder> {

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "学生ID")
    @TableField("customer_id")
    private Integer customerId;

    @ApiModelProperty(value = "订单ID")
    @TableField("order_id")
    private Long orderId;

    @ApiModelProperty(value = "支付订单ID")
    @TableField("pay_order_id")
    private Long payOrderId;

    @ApiModelProperty(value = "交易流水号")
    @TableField("trade_no")
    private Long tradeNo;

    @ApiModelProperty(value = "拼团活动id")
    @TableField("groupon_activity_id")
    private Integer grouponActivityId;

    @ApiModelProperty(value = "团id")
    @TableField("groupon_id")
    private Integer grouponId;

    @ApiModelProperty(value = "总金额")
    @TableField("total_amount")
    private BigDecimal totalAmount;

    @ApiModelProperty(value = "优惠金额")
    @TableField("discount_amount")
    private BigDecimal discountAmount;

    @ApiModelProperty(value = "订单状态:0：待付款，1 取消 2 待成团  3 拼团成功 4退款中5 拼团失败")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "操作类型 ：0 手动成团 1 手动退款")
    @TableField("operate_type")
    private Integer operateType;

    @ApiModelProperty(value = "删除状态 0-未删除 1-已删除")
    @TableField("del_status")
    private Integer delStatus;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "订单完成时间")
    @TableField("success_time")
    private LocalDateTime successTime;


}
