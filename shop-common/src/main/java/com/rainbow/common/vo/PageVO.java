package com.rainbow.common.vo;

import com.rainbow.common.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * page视图
 *
 * @author: lujinwei
 * @since: 2018-12-18
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "PageVO", description = "分页参数")
public class PageVO extends BaseDTO {

    @ApiModelProperty("当前分页")
    private Integer current;

    @ApiModelProperty("总分页")
    private Integer pages;

    @ApiModelProperty("分页大小")
    private Integer size;

    @ApiModelProperty("总数量")
    private Integer total;
}
