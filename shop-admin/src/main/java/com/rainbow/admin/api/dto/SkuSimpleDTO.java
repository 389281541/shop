package com.rainbow.admin.api.dto;

import com.rainbow.common.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "SkuSimpleDTO", description = "spu初始化sku传输对象")
public class SkuSimpleDTO extends BaseDTO {

    @ApiModelProperty(value = "skuID")
    private Long skuId;

    @ApiModelProperty(value = "商品ID")
    private Long spuId;

    @ApiModelProperty(value = "SKU编码")
    private String skuNo;

    @ApiModelProperty(value = "库存")
    private Integer stock;

    @ApiModelProperty(value = "预警库存")
    private Integer lowStock;

    @ApiModelProperty(value = "商品重量，默认为克")
    private BigDecimal weight;

    @ApiModelProperty(value = "尺寸：0-小件，1-中件，2-大件")
    private Integer dimension;

    @ApiModelProperty(value = "价格")
    private Long price;

    @ApiModelProperty(value = "图片url")
    private String imgUrl;

    @ApiModelProperty(value = "属性列表")
    private List<Long> specValueIdList;

}
