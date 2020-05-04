package com.rainbow.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * 团购活动更新DTO
 *
 * @author lujinwei
 * @since 2020/5/2
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "GrouponActivityUpdateDTO", description = "团购活动更新DTO")
public class GrouponActivityUpdateDTO extends GrouponActivitySaveDTO {

    @ApiModelProperty(value = "主键ID")
    @NotNull(message = "ID不能为空")
    private Long id;
}
