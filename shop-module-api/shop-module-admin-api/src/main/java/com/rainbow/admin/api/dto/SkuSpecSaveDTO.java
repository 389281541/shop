package com.rainbow.admin.api.dto;

import com.rainbow.common.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "SkuSpecSaveDTO", description = "sku属性传输对象")
public class SkuSpecSaveDTO extends BaseDTO {

    @ApiModelProperty(value = "SKUID")
    private Long skuId;

    @ApiModelProperty(value = "属性值ID")
    @NotNull(message = "属性值ID不能为空")
    private Long specValueId;

    @ApiModelProperty(value = "属性值ID")
    @NotNull(message = "属性值不能为空")
    private String specValue;

    @ApiModelProperty(value = "属性名ID")
    @NotNull(message = "属性名ID不能为空")
    private Long specNameId;

    @ApiModelProperty(value = "属性名ID")
    @NotNull(message = "属性名不能为空")
    private String specName;

    @ApiModelProperty(value = "排序ID")
    private Integer sortId;
}
