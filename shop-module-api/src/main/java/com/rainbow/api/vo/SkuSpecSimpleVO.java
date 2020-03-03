package com.rainbow.api.vo;

import com.rainbow.common.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "SkuSpecSimpleVO", description = "sku规格VO")
public class SkuSpecSimpleVO extends BaseDTO {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "SKUID")
    private Long skuId;

    @ApiModelProperty(value = "属性名Id")
    private Long specNameId;

    @ApiModelProperty(value = "属性名")
    private String specName;

    @ApiModelProperty(value = "属性值Id")
    private Long specValueId;

    @ApiModelProperty(value = "属性值")
    private String specValue;

    @ApiModelProperty(value = "排序ID")
    private Integer sortId;

}
