package com.rainbow.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * ID分页传输对象
 *
 * @author lujinwei
 * @since 2019-02-14
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "IdPageDTO", description = "ID分页传输对象")
public class IdPageDTO extends PageDTO {

    @ApiModelProperty(value = "ID")
    @NotNull(message = "id不能为空")
    private Long id;

}
