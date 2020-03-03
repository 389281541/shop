package com.rainbow.api.vo;

import com.rainbow.common.dto.BaseDTO;
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
@ApiModel(value = "HomeAdvertiseVO", description = "首页广告VO")
public class HomeAdvertiseVO extends BaseDTO {

    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "主题")
    private String theme;

    @ApiModelProperty(value = "位置：0->轮播图，1->首页活动位")
    private Integer type;

    @ApiModelProperty(value = "图片链接")
    private String imgUrl;

    @ApiModelProperty(value = "跳转链接")
    private String forwardUrl;

    @ApiModelProperty(value = "上线状态 0-未上线 1-已上线")
    private Integer status;

    @ApiModelProperty(value = "排序ID")
    private Long sortId;

    @ApiModelProperty(value = "备注")
    private String note;
}
