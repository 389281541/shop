package com.rainbow.common.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 时间model
 *
 * @author lujinwei
 * @since 2020/4/14
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "HMS", description = "时分秒传输对象")
public class HMS {

    @ApiModelProperty(value = "时")
    private Integer hour;

    @ApiModelProperty(value = "分")
    private Integer minute;

    @ApiModelProperty(value = "秒")
    private Integer second;

}
