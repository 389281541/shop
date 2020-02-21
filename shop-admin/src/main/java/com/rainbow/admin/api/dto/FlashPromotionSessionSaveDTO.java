package com.rainbow.admin.api.dto;

import com.rainbow.common.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalTime;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "FlashPromotionSessionSaveDTO", description = "秒杀时间段保存传输对象")
public class FlashPromotionSessionSaveDTO extends BaseDTO {

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty(value = "每日开始时间")
    private LocalTime startTime;

    @ApiModelProperty(value = "每日结束时间")
    private LocalTime endTime;

    @ApiModelProperty(value = "启用状态：0->不启用；1->启用")
    private Integer status;
}
