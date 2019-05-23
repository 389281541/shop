package com.rainbow.common.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * ID DTO
 *
 * @author lujinwei
 * @Date 2018/11/12 14:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IdDTO extends BaseDTO {

    @ApiModelProperty(value = "主键ID", required = true)
    @NotNull(message = "主键ID不能为空")
    private Long id;

}
