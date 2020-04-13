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
 *  订单sku VO
 *
 * @author lujinwei
 * @since 2020/3/20
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "OrderSkuPromotionVO", description = "订单sku VO")
public class OrderSkuPromotionVO extends BaseDTO {

    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "订单id")
    private Long orderId;

    @ApiModelProperty(value = "订单编号")
    private String orderNo;

    @ApiModelProperty(value = "SKUID")
    private Long skuId;

    @ApiModelProperty(value = "skuSpec")
    private String skuSpec;

    @ApiModelProperty(value = "SPUID")
    private Long spuId;

    @ApiModelProperty(value = "商品名称")
    private String spuName;

    @ApiModelProperty(value = "SPU图片")
    private String skuPic;

    @ApiModelProperty(value = "品牌ID")
    private Long brandId;

    @ApiModelProperty(value = "品牌名称")
    private String brandName;

    @ApiModelProperty(value = "店铺ID")
    private Long shopId;

    @ApiModelProperty(value = "购买数量")
    private Integer quantity;

    @ApiModelProperty(value = "价格")
    private BigDecimal price;

    @ApiModelProperty(value = "类别ID")
    private Long itemId;

    @ApiModelProperty(value = "商品促销名称")
    private String promotionName;

    @ApiModelProperty(value = "商品促销分解金额")
    private BigDecimal promotionAmount;

    @ApiModelProperty(value = "优惠券优惠分解金额")
    private BigDecimal couponAmount;

    @ApiModelProperty(value = "积分优惠分解金额")
    private BigDecimal integrationAmount;

    @ApiModelProperty(value = "该商品经过优惠后的分解金额")
    private BigDecimal realAmount;

    @ApiModelProperty(value = "获得的积分")
    private Integer integrationAward;

}
