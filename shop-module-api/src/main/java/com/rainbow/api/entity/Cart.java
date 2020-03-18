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
 * 购物车表
 *
 * @author lujinwei
 * @since 2020-03-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("cart")
@ApiModel(value="Cart对象", description="购物车表")
public class Cart extends Model<Cart> {

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "SKUID")
    @TableField("sku_id")
    private Long skuId;

    @ApiModelProperty(value = "sku属性")
    @TableField("sku_spec")
    private String skuSpec;

    @ApiModelProperty(value = "库存")
    @TableField("sku_stock")
    private Integer skuStock;

    @ApiModelProperty(value = "SPUID")
    @TableField("spu_id")
    private Long spuId;

    @ApiModelProperty(value = "商品名称")
    @TableField("spu_name")
    private String spuName;

    @ApiModelProperty(value = "封面图片")
    @TableField("cover_img")
    private String coverImg;

    @ApiModelProperty(value = "用户ID")
    @TableField("customer_id")
    private Long customerId;

    @ApiModelProperty(value = "店铺ID")
    @TableField("shop_id")
    private Long shopId;

    @ApiModelProperty(value = "类别ID")
    @TableField("item_id")
    private Long itemId;

    @ApiModelProperty(value = "商品数量")
    @TableField("quantity")
    private Integer quantity;

    @ApiModelProperty(value = "价格")
    @TableField("price")
    private BigDecimal price;

    @ApiModelProperty(value = "原价")
    @TableField("original_price")
    private BigDecimal originalPrice;

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
