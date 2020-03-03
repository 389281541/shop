package com.rainbow.api.dto;

import com.rainbow.api.entity.CouponItem;
import com.rainbow.api.entity.CouponSpu;
import com.rainbow.common.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "CouponSaveDTO", description = "优惠券保存传输对象")
public class CouponSaveDTO  extends BaseDTO {

    @ApiModelProperty(value = "优惠卷类型；0->全场赠券；1->会员赠券；2->购物赠券；3->注册赠券")
    private Integer type;

    @ApiModelProperty(value = "优惠券名称")
    private String name;

    @ApiModelProperty(value = "总数量")
    private Integer totalNum;

    @ApiModelProperty(value = "金额")
    private BigDecimal amount;

    @ApiModelProperty(value = "每人限领张数")
    private Integer receiveNumLimit;

    @ApiModelProperty(value = "使用门槛；0表示无门槛 1-满多少金额使用")
    private Integer useConditionType;

    @ApiModelProperty(value = "使用条件的数量金额限制,金额时精确到分")
    private Integer useConditionLimit;

    @ApiModelProperty(value = "优惠券有效期开始时间")
    private LocalDateTime startEffectTime;

    @ApiModelProperty(value = "优惠券有效期截止时间")
    private LocalDateTime endEffectTime;

    @ApiModelProperty(value = "使用类型：0->全场通用；1->指定分类；2->指定商品")
    private Integer scopeType;

    @ApiModelProperty(value = "备注")
    private String note;

    @ApiModelProperty(value = "发行数量")
    private Integer publishNum;

    @ApiModelProperty(value = "已使用数量")
    private Integer useNum;

    @ApiModelProperty(value = "领取数量")
    private Integer receiveNum;

    @ApiModelProperty(value = "可以领取的日期")
    private LocalDateTime enableTime;

    @ApiModelProperty(value = "优惠码")
    private String couponCode;

    @ApiModelProperty(value = "优惠绑定的商品")
    private List<CouponSpu> couponSpuList;

    @ApiModelProperty(value = "优惠绑定的商品分类")
    private List<CouponItem> couponItemList;
}
