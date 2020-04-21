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
@ApiModel(value = "FlashPromotionSpuInfoVO", description = "秒杀商品信息VO")
public class FlashPromotionSpuInfoVO extends FlashPromotionSpuVO {

    @ApiModelProperty(value = "秒杀倒计时")
    private HMS hms;

    @ApiModelProperty(value = "秒杀进程")
    private Integer flashStatus;

    @ApiModelProperty(value = "是否秒杀 0-非秒杀 1-秒杀")
    private Integer flashFlag;
}
