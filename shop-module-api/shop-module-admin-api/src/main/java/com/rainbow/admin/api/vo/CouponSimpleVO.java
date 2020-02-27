package com.rainbow.admin.api.vo;

import com.rainbow.common.vo.IdNameVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "CouponSimpleVO", description = "优惠券VO")
public class CouponSimpleVO extends IdNameVO {

    @ApiModelProperty(value = "优惠码")
    private String couponCode;

    @ApiModelProperty(value = "优惠卷类型；0->全场赠券；1->会员赠券；2->购物赠券；3->注册赠券")
    private Integer type;

    @ApiModelProperty(value = "使用类型：0->全场通用；1->指定分类；2->指定商品")
    private Integer scopeType;

    @ApiModelProperty(value = "使用门槛；0表示无门槛 1-满多少金额使用")
    private Integer useConditionType;

    @ApiModelProperty(value = "使用条件的数量金额限制,金额时精确到分")
    private Integer useConditionLimit;

    @ApiModelProperty(value = "优惠券有效期开始时间")
    private LocalDateTime startEffectTime;

    @ApiModelProperty(value = "优惠券有效期截止时间")
    private LocalDateTime endEffectTime;

    @ApiModelProperty(value = "金额")
    private BigDecimal amount;


}
