package com.rainbow.admin.entity;

import com.rainbow.common.vo.IdNameVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SpecNameWithValues", description="规格名称表")
public class SpecNameWithValues extends SpecName {
    @ApiModelProperty(value = "属性值列表")
    private List<IdNameVO> specValues;
}
