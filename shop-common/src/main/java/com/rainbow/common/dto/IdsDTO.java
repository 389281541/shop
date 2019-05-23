package com.rainbow.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 主键ID列表DTO
 * @author: lujinwei
 * @date: 2018-11-07
 */
@Data
@ApiModel(value = "IdsDTO", description = "主键ID列表DTO")
@AllArgsConstructor
@NoArgsConstructor
public class IdsDTO extends BaseDTO {

    @ApiModelProperty(value = "主键ID列表")
    private List<Long> ids;

}
