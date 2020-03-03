package com.rainbow.api.dto;

import com.rainbow.common.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "HomeAdvertiseSearchDTO", description = "首页广告搜索DTO")
public class HomeAdvertiseSearchDTO extends PageDTO {

    @ApiModelProperty(value = "位置：0->轮播图，1->首页活动位")
    private Integer type;

    @ApiModelProperty(value = "上线状态 0-未上线 1-已上线")
    private Integer status;

}
