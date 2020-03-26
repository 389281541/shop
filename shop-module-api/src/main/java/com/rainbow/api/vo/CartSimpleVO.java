package com.rainbow.api.vo;

import com.rainbow.common.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * 购物车(包括促销信息)VO
 *
 * @author lujinwei
 * @since 2020/3/14
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "CartSimpleVO", description = "购物车VO")
public class CartSimpleVO extends BaseDTO {

    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "SKUID")
    private Long skuId;

    @ApiModelProperty(value = "sku属性")
    private String skuSpec;

    @ApiModelProperty(value = "库存")
    private Integer skuStock;

    @ApiModelProperty(value = "sku名称")
    private String skuName;

    @ApiModelProperty(value = "锁定库存")
    private Integer skuLockStock;

    @ApiModelProperty(value = "SPUID")
    private Long spuId;

    @ApiModelProperty(value = "商品名称")
    private String spuName;

    @ApiModelProperty(value = "封面图片")
    private String coverImg;

    @ApiModelProperty(value = "用户ID")
    private Long customerId;

    @ApiModelProperty(value = "店铺ID")
    private Long shopId;

    @ApiModelProperty(value = "店铺名称")
    private String shopName;

    @ApiModelProperty(value = "类别ID")
    private Long itemId;

    @ApiModelProperty(value = "商品数量")
    private Integer quantity;

    @ApiModelProperty(value = "原价")
    private BigDecimal originalPrice;

    @ApiModelProperty(value = "价格")
    private BigDecimal price;
}
