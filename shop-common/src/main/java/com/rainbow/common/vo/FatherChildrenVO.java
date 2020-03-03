package com.rainbow.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "FatherChildrenVO", description = "父子关系VO")
public class FatherChildrenVO extends IdNameVO {

    @ApiModelProperty(value = "成员ID和Name")
    List<IdNameVO> children;
}
