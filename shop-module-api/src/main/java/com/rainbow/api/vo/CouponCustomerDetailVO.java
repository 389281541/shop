package com.rainbow.api.vo;

import com.rainbow.api.entity.Coupon;
import com.rainbow.api.entity.CouponItem;
import com.rainbow.api.entity.CouponSpu;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 优惠券领取详情页  包括优惠券关联表
 *
 * @author lujinwei
 * @since 2020/3/19
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "CouponCustomerDetailVO", description = "优惠券领取VO")
public class CouponCustomerDetailVO extends CouponCustomerSimpleVO {

    @ApiModelProperty(value = "优惠券信息")
    private Coupon coupon;

    @ApiModelProperty(value = "优惠券类别关联表")
    private List<CouponItem> couponItemList;

    @ApiModelProperty(value = "优惠券商品关联关系表")
    private List<CouponSpu> couponSpuList;


}
