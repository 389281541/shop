package com.rainbow.admin.api.vo;

import com.rainbow.common.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "SkuSimpleVO", description = "SKU列表VO")
public class SkuSimpleVO extends BaseDTO {

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "商品编码")
    private String skuNo;

    @ApiModelProperty(value = "sku名称")
    private String skuName;

    @ApiModelProperty(value = "商品ID")
    private Long spuId;

    @ApiModelProperty(value = "库存")
    private Integer stock;

    @ApiModelProperty(value = "预警库存")
    private Integer lowStock;

    @ApiModelProperty(value = "销量")
    private Integer sale;

    @ApiModelProperty(value = "商品重量，默认为克")
    private BigDecimal weight;

    @ApiModelProperty(value = "尺寸：0-小件，1-中件，2-大件")
    private Integer dimension;

    @ApiModelProperty(value = "价格")
    private BigDecimal originalPrice;

    @ApiModelProperty(value = "价格")
    private BigDecimal price;

    @ApiModelProperty(value = "sku spec关联表")
    private List<SkuSpecSimpleVO> skuSpecList;

    @ApiModelProperty(value = "属性键值对")
    private Map<String, String> skuSpecMap;


}
