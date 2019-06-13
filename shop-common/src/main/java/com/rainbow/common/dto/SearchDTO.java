package com.rainbow.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 搜索基类
 *
 * @author lujinwei
 * @since 2019-02-22
 */
@Data
@ApiModel(value = "SearchDTO", description = "搜索基类")
public class SearchDTO extends BaseDTO {

    @ApiModelProperty("搜索关键字")
    private String keyword;
}
