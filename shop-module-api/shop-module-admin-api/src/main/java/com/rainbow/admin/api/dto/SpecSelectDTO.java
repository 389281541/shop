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
@ApiModel(value = "SpecSelectDTO", description = "属性选择传输对象")
public class SpecSelectDTO extends BaseDTO {

    @ApiModelProperty(value = "类别ID")
    @NotNull(message = "类别名不能为空")
    private Long itemId;

    @ApiModelProperty(value = "spuID")
    private Long spuId;
}
