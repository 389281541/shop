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
 * 优惠券表
 *
 * @author lujinwei
 * @since 2020-02-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("coupon")
@ApiModel(value="Coupon对象", description="优惠券表")
public class Coupon extends Model<Coupon> {

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "优惠卷类型；0->全场赠券；1->会员赠券；2->购物赠券；3->注册赠券")
    @TableField("type")
    private Integer type;

    @ApiModelProperty(value = "优惠券名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "总数量")
    @TableField("totalNum")
    private Integer totalNum;

    @ApiModelProperty(value = "金额")
    @TableField("amount")
    private BigDecimal amount;

    @ApiModelProperty(value = "每人限领张数")
    @TableField("receive_num_limit")
    private Integer receiveNumLimit;

    @ApiModelProperty(value = "使用门槛；0表示无门槛 1-满多少金额使用 2-购买多少件商品可以使用")
    @TableField("use_condition_type")
    private Integer useConditionType;

    @ApiModelProperty(value = "使用条件的数量金额限制,金额时精确到分")
    @TableField("use_condition_limit")
    private Integer useConditionLimit;

    @ApiModelProperty(value = "优惠券有效期开始时间")
    @TableField("start_effect_time")
    private LocalDateTime startEffectTime;

    @ApiModelProperty(value = "优惠券有效期截止时间")
    @TableField("end_effect_time")
    private LocalDateTime endEffectTime;

    @ApiModelProperty(value = "使用类型：0->全场通用；1->指定分类；2->指定商品")
    @TableField("scope_type")
    private Integer scopeType;

    @ApiModelProperty(value = "备注")
    @TableField("note")
    private String note;

    @ApiModelProperty(value = "发行数量")
    @TableField("publish_num")
    private Integer publishNum;

    @ApiModelProperty(value = "已使用数量")
    @TableField("use_num")
    private Integer useNum;

    @ApiModelProperty(value = "领取数量")
    @TableField("receive_num")
    private Integer receiveNum;

    @ApiModelProperty(value = "可以领取的日期")
    @TableField("enable_time")
    private LocalDateTime enableTime;

    @ApiModelProperty(value = "优惠码")
    @TableField("coupon_code")
    private String couponCode;


}
