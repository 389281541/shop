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

import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "UpDownRankingDTO", description = "排序调整传输对象")
public class UpDownRankingDTO extends IdDTO {

    @ApiModelProperty(value = "操作的属性值ID")
    @NotNull(message = "值ID不能为空")
    private Integer specValueId;

    @ApiModelProperty(value = "类型,0-上移;1-下移")
    @EnumValidAnnotation(message = "类型", allowNull = false, target = BooleanEnum.class)
    private Integer type;

}
