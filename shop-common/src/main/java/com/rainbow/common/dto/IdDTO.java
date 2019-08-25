package com.rainbow.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * ID DTO
 *
 * @author lujinwei
 * @since 2018-11-12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "IdDTO", description = "Id传输对象")
public class IdDTO extends BaseDTO {

    @ApiModelProperty(value = "主键ID", required = true)
    private Long id;

}
