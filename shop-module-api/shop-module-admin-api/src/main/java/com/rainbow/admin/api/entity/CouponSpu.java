package com.rainbow.admin.api.entity;

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
 * 优惠券和商品的关系表
 *
 * @author lujinwei
 * @since 2020-02-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("coupon_spu")
@ApiModel(value="CouponSpu对象", description="优惠券和商品的关系表")
public class CouponSpu extends Model<CouponSpu> {

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "优惠券ID")
    @TableField("coupon_id")
    private Long couponId;

    @ApiModelProperty(value = "SPUID")
    @TableField("spu_id")
    private Long spuId;

    @ApiModelProperty(value = "SPUID")
    @TableField("spu_no")
    private Long spuNo;

    @ApiModelProperty(value = "商品名称")
    @TableField("spu_name")
    private String spuName;


}
