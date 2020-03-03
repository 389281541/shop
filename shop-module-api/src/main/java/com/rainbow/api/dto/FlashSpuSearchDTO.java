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
@ApiModel(value = "FlashSpuSearchDTO", description = "秒杀商品搜索对象")
public class FlashSpuSearchDTO extends PageDTO {

    @ApiModelProperty(value = "秒杀活动ID")
    private Long flashPromotionId;

    @ApiModelProperty(value = "秒杀时间段ID")
    private Long flashPromotionSessionId;
}
