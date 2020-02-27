package com.rainbow.admin.api.vo;

import com.rainbow.admin.api.entity.CouponItem;
import com.rainbow.admin.api.entity.CouponSpu;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "CouponDetailVO", description = "优惠券详情VO")
public class CouponDetailVO extends CouponSimpleVO {

    @ApiModelProperty(value = "发行数量")
    private Integer publishNum;

    @ApiModelProperty(value = "领取数量")
    private Integer receiveNum;

    @ApiModelProperty(value = "每人限领张数")
    private Integer receiveNumLimit;

    @ApiModelProperty(value = "总数量")
    private Integer totalNum;

    @ApiModelProperty(value = "备注")
    private String note;

    @ApiModelProperty(value = "已使用数量")
    private Integer useNum;

    @ApiModelProperty(value = "可以领取的日期")
    private LocalDateTime enableTime;

    @ApiModelProperty(value = "优惠绑定的商品")
    private List<CouponSpu> couponSpuList;

    @ApiModelProperty(value = "优惠绑定的商品分类")
    private List<CouponItem> couponItemList;
}
