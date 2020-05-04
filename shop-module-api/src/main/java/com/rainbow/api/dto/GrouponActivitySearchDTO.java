package com.rainbow.api.dto;

import com.rainbow.common.dto.SearchPageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 团购活动搜索DTO
 *
 * @author lujinwei
 * @since 2020/5/2
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "GrouponActivitySearchDTO", description = "团购活动搜索DTO")
public class GrouponActivitySearchDTO extends SearchPageDTO {

    @ApiModelProperty(value = "活动状态 0:草稿，1:未开始，2:进行中，3:关闭，4:已结束")
    private Integer status;
}
