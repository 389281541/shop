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
 * 优惠券和商品分类关系表
 *
 * @author lujinwei
 * @since 2020-02-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("coupon_item")
@ApiModel(value="CouponItem对象", description="优惠券和商品分类关系表")
public class CouponItem extends Model<CouponItem> {

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "优惠券ID")
    @TableField("coupon_id")
    private Long couponId;

    @ApiModelProperty(value = "类别ID")
    @TableField("item_id")
    private Long itemId;

    @ApiModelProperty(value = "产品分类名称")
    @TableField("item_name")
    private String itemName;

    @ApiModelProperty(value = "父分类名称")
    @TableField("parent_item_name")
    private String parentItemName;


}
