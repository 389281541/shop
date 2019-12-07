package com.rainbow.admin.api.dto;

import com.rainbow.common.annotation.EnumValidAnnotation;
import com.rainbow.common.dto.IdDTO;
import com.rainbow.common.enums.BooleanEnum;
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
@ApiModel(value = "StatusChangeDTO", description = "状态更改传输对象")
public class StatusChangeDTO extends IdDTO {

    @ApiModelProperty(value = "类型,0-失败;1-成功")
    @EnumValidAnnotation(message = "类型", allowNull = false, target = BooleanEnum.class)
    private Integer status;
}
