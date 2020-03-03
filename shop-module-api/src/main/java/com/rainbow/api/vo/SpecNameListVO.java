package com.rainbow.api.vo;

import com.rainbow.common.dto.BaseDTO;
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
@ApiModel(value = "SpecNameListVO", description = "属性列表VO")
public class SpecNameListVO extends BaseDTO {

    @ApiModelProperty(value = "规格列表")
    private List<SpecNameSimpleVO>  skuSpecList;

    @ApiModelProperty(value = "参数列表")
    private List<SpecNameSimpleVO>  spuSpecList;

}
