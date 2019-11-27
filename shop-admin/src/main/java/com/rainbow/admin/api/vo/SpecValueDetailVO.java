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
@ApiModel(value = "SpecValueDetailVO", description = "属性值VO")
public class SpecValueDetailVO extends SpecValueSimpleVO {
    @ApiModelProperty(value = "属性名ID")
    private Long specNameId;

    @ApiModelProperty(value = "属性名ID")
    private String specName;
}
