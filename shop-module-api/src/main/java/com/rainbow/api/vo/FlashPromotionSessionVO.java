package com.rainbow.api.vo;

import com.rainbow.common.vo.IdNameVO;
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
@ApiModel(value = "FlashPromotionSessionVO", description = "秒杀时间段VO")
public class FlashPromotionSessionVO extends IdNameVO {

    @ApiModelProperty(value = "每日开始时间")
    private LocalTime startTime;

    @ApiModelProperty(value = "每日结束时间")
    private LocalTime endTime;

    @ApiModelProperty(value = "启用状态：0->不启用；1->启用")
    private Integer status;

    @ApiModelProperty(value = "秒杀活动商品数")
    private Long count;
}
