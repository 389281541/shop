package com.rainbow.common.dto;

import com.rainbow.common.annotation.EnumValidAnnotation;
import com.rainbow.common.enums.BooleanEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "StatusBatchChangeDTO", description = "批量更新状态DTO")
public class StatusBatchChangeDTO extends IdsDTO {

    @ApiModelProperty(value = "主键ID列表")
    @EnumValidAnnotation(message = "类型", allowNull = false, target = BooleanEnum.class)
    private Integer status;
}
