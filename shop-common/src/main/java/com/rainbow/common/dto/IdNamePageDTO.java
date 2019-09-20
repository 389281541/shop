package com.rainbow.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * 分页传输对象
 *
 * @author lujinwei
 * @since 2019-02-14
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "IdNamePageDTO", description = "分页传输对象")
public class IdNamePageDTO extends IdPageDTO {

    @ApiModelProperty(value = "名称")
    private String name;
}
