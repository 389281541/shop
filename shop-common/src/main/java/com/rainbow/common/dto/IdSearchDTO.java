package com.rainbow.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 搜索基类
 *
 * @author lujinwei
 * @since 2019-02-22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "SearchDTO", description = "搜索基类")
public class IdSearchDTO extends SearchDTO {

    @ApiModelProperty("搜索关键字")
    private Long id;
}
