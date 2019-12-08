package com.rainbow.admin.api.vo;

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
@ApiModel(value = "SpuDetailVO", description = "Spu详情VO")
public class SpuDetailVO extends SpuSimpleVO {

    @ApiModelProperty(value = "商品描述")
    private String description;

}
