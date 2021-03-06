package com.rainbow.api.dto;

import com.rainbow.common.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "SpecValueSaveDTO", description = "属性值保存传输对象")
public class SpecValueSaveDTO extends BaseDTO {

    @ApiModelProperty(value = "属性名ID")
    private Long specNameId;

    @ApiModelProperty(value = "名称")
    @NotBlank(message = "属性值不能为空")
    private String specValue;

    @ApiModelProperty(value = "排序ID")
    @NotNull(message = "排序ID不能为空")
    private Long sortId;


}
