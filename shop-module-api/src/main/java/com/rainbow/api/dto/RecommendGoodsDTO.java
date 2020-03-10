package com.rainbow.api.dto;

import com.rainbow.common.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 推荐商品DTO
 *
 * @author lujinwei
 * @since 2020/3/9
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "RecommendGoodsDTO", description = "推荐商品查询DTO")
public class RecommendGoodsDTO extends BaseDTO {
    
    @ApiModelProperty(value = "类别ID")
    private Long itemId;
}
