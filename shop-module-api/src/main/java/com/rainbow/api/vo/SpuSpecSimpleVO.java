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
@ApiModel(value = "SpuSpecSimpleVO", description = "spu spec视图对象")
public class SpuSpecSimpleVO extends BaseDTO {

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "商品ID")
    private Long spuId;

    @ApiModelProperty(value = "属性名ID")
    private Long specNameId;

    @ApiModelProperty(value = "属性名")
    private String specName;

    @ApiModelProperty(value = "属性值ID")
    private Long specValueId;

    @ApiModelProperty(value = "属性值")
    private String specValue;

}
