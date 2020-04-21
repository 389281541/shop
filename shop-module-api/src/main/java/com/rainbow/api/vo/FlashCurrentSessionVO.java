package com.rainbow.api.vo;

import com.rainbow.common.model.HMS;
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
@ApiModel(value = "FlashCurrentSessionVO", description = "当前秒杀时间段VO")
public class FlashCurrentSessionVO extends FlashPromotionSessionVO {

    @ApiModelProperty(value = "秒杀倒计时")
    private HMS hms;

    @ApiModelProperty(value = "秒杀进程")
    private Integer flashStatus;
}
