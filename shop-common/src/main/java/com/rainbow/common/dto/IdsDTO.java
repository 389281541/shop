package com.rainbow.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 主键ID列表DTO
 *
 * @author: lujinwei
 * @since: 2018-11-07
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "IdsDTO", description = "主键ID列表DTO")
public class IdsDTO extends BaseDTO {

    @ApiModelProperty(value = "主键ID列表")
    @NotEmpty(message = "ids不能为空")
    private List<Long> ids;

}
