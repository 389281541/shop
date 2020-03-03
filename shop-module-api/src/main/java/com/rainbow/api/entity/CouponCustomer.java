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
 * 优惠券使用、领取历史表
 *
 * @author lujinwei
 * @since 2020-02-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("coupon_customer")
@ApiModel(value="CouponCustomer对象", description="优惠券使用、领取历史表")
public class CouponCustomer extends Model<CouponCustomer> {

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "优惠券ID")
    @TableField("coupon_id")
    private Long couponId;

    @ApiModelProperty(value = "用户ID")
    @TableField("customer_id")
    private Long customerId;

    @ApiModelProperty(value = "优惠券码")
    @TableField("coupon_code")
    private String couponCode;

    @ApiModelProperty(value = "领取人昵称")
    @TableField("customer_name")
    private String customerName;

    @ApiModelProperty(value = "获取类型：0->后台赠送；1->主动获取")
    @TableField("receive_type")
    private Integer receiveType;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "使用状态：0->未使用；1->已使用；2->已过期")
    @TableField("use_status")
    private Integer useStatus;

    @ApiModelProperty(value = "使用时间")
    @TableField("use_time")
    private LocalDateTime useTime;

    @ApiModelProperty(value = "订单编号")
    @TableField("order_id")
    private Long orderId;

    @ApiModelProperty(value = "订单编号")
    @TableField("order_no")
    private String orderNo;


}
