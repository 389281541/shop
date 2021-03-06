package com.rainbow.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;

/**
 * 分页 DTO
 *
 * @author lujinwei
 * @since 2018-11-12
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "PageDTO", description = "分页DTO")
public class PageDTO extends BaseDTO {

    /**
     * 默认分页条数
     */
    private static final int DEF_PAGE_SIZE = 20;
    /**
     * 默认页码
     */
    private static final int DEF_PAGE_NUM = 1;

    @ApiModelProperty(value = "页码", example = "1")
    @Min(value = 1, message = "pageNum不能小于1")
    private Integer pageNum = DEF_PAGE_NUM;

    @ApiModelProperty(value = "分页数", example = "20")
    @Min(value = 1, message = "pageSize不能小于1")
    private Integer pageSize = DEF_PAGE_SIZE;
}
