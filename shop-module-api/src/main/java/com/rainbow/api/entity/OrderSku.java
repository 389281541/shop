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
 * 订单-sku关联表
 *
 * @author lujinwei
 * @since 2020-02-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("order_sku")
@ApiModel(value="OrderSku对象", description="订单-sku关联表")
public class OrderSku extends Model<OrderSku> {

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "订单id")
    @TableField("order_id")
    private Long orderId;

    @ApiModelProperty(value = "订单编号")
    @TableField("order_no")
    private String orderNo;

    @ApiModelProperty(value = "SKUID")
    @TableField("sku_id")
    private Long skuId;

    @ApiModelProperty(value = "skuSpec")
    @TableField("sku_spec")
    private String skuSpec;

    @ApiModelProperty(value = "SPUID")
    @TableField("spu_id")
    private Long spuId;

    @ApiModelProperty(value = "SPU图片")
    @TableField("sku_pic")
    private String skuPic;

    @ApiModelProperty(value = "SKU名称")
    @TableField("sku_name")
    private String skuName;

    @ApiModelProperty(value = "品牌ID")
    @TableField("brand_id")
    private Long brandId;

    @ApiModelProperty(value = "品牌名称")
    @TableField("brand_name")
    private String brandName;

    @ApiModelProperty(value = "店铺ID")
    @TableField("shop_id")
    private Long shopId;

    @ApiModelProperty(value = "购买数量")
    @TableField("quantity")
    private Integer quantity;

    @ApiModelProperty(value = "价格")
    @TableField("price")
    private BigDecimal price;

    @ApiModelProperty(value = "类别ID")
    @TableField("item_id")
    private Long itemId;

    @ApiModelProperty(value = "商品促销名称")
    @TableField("promotion_name")
    private String promotionName;

    @ApiModelProperty(value = "商品促销分解金额")
    @TableField("promotion_amount")
    private BigDecimal promotionAmount;

    @ApiModelProperty(value = "优惠券优惠分解金额")
    @TableField("coupon_amount")
    private BigDecimal couponAmount;

    @ApiModelProperty(value = "积分优惠分解金额")
    @TableField("integration_amount")
    private BigDecimal integrationAmount;

    @ApiModelProperty(value = "该商品经过优惠后的分解金额")
    @TableField("real_amount")
    private BigDecimal realAmount;

    @TableField("integration_award")
    private Integer integrationAward;

    @ApiModelProperty(value = "删除状态 0-未删除 1-已删除")
    @TableField("del_status")
    private Integer delStatus;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;


}
