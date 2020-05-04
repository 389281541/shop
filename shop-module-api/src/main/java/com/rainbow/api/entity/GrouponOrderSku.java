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

/**
 * 团购订单表
 *
 * @author lujinwei
 * @since 2020-05-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("groupon_order_sku")
@ApiModel(value="GrouponOrderSku对象", description="团购订单表")
public class GrouponOrderSku extends Model<GrouponOrderSku> {

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "商品ID")
    @TableField("spu_id")
    private Integer spuId;

    @ApiModelProperty(value = "商品name")
    @TableField("spu_name")
    private String spuName;

    @ApiModelProperty(value = "商品skuID")
    @TableField("sku_id")
    private Integer skuId;

    @ApiModelProperty(value = "skuName")
    @TableField("sku_name")
    private String skuName;

    @ApiModelProperty(value = "订单id")
    @TableField("groupon_order_id")
    private Long grouponOrderId;

    @ApiModelProperty(value = "订单编号")
    @TableField("groupon_order_no")
    private String grouponOrderNo;

    @ApiModelProperty(value = "sku属性JSON")
    @TableField("sku_spec")
    private String skuSpec;

    @ApiModelProperty(value = "品牌ID")
    @TableField("brand_id")
    private Long brandId;

    @ApiModelProperty(value = "品牌名称")
    @TableField("brand_name")
    private String brandName;

    @ApiModelProperty(value = "店铺ID")
    @TableField("shop_id")
    private Long shopId;

    @ApiModelProperty(value = "店铺名称")
    @TableField("shop_name")
    private String shopName;

    @ApiModelProperty(value = "购买数量")
    @TableField("quantity")
    private Integer quantity;

    @ApiModelProperty(value = "价格")
    @TableField("price")
    private Long price;

    @ApiModelProperty(value = "类别ID")
    @TableField("item_id")
    private Long itemId;

    @ApiModelProperty(value = "商品促销分解金额")
    @TableField("discount_amount")
    private BigDecimal discountAmount;

    @ApiModelProperty(value = "该商品经过优惠后的分解金额")
    @TableField("real_amount")
    private BigDecimal realAmount;


}
