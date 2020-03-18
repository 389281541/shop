package com.rainbow.api.dto;

import com.rainbow.common.dto.SearchPageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.Pattern;

/**
 * 商品搜索DTO
 *
 * @author lujinwei
 * @since 2020/3/8
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "GoodsSearchDTO", description = "商品搜索对象")
public class GoodsSearchDTO extends SearchPageDTO {

    @ApiModelProperty(value = "类别ID")
    private Long itemId;

    @ApiModelProperty(value = "店铺ID")
    private Long shopId;

    @ApiModelProperty(value = "排序字段")
    private String sortBy;

    @ApiModelProperty(value = "排序方式 desc asc")
    @Pattern(regexp = "asc|desc", message = "排序类型取值异常")
    private String sortType;
}
