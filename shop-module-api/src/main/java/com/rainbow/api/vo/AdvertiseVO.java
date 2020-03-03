package com.rainbow.api.vo;

import com.rainbow.common.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;


@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "AdvertiseVO", description = "首页广告信息VO")
public class AdvertiseVO extends BaseDTO {

    @ApiModelProperty(value = "轮播图广告")
    private List<HomeAdvertiseVO> rotateChartList;

    @ApiModelProperty(value = "活动位广告")
    private List<HomeAdvertiseVO> activityList;
}
