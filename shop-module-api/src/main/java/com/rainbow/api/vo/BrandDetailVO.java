package com.rainbow.api.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "BrandDetailVO", description = "品牌VO")
public class BrandDetailVO extends BrandSimpleVO {

    @ApiModelProperty(value = "类别ID")
    private List<Long> itemIds;

    @ApiModelProperty(value = "品牌描述")
    private String description;
}
