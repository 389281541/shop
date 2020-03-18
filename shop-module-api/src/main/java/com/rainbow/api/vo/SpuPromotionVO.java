package com.rainbow.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

/**
 * spu促销VO
 *
 * @author lujinwei
 * @since 2020/3/15
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "SpuPromotionVO", description = "spu促销VO")
public class SpuPromotionVO extends SpuSimpleVO {

    @ApiModelProperty(value = "促销开始时间")
    private LocalDateTime promotionStartTime;

    @ApiModelProperty(value = "促销结束时间")
    private LocalDateTime promotionEndTime;

    @ApiModelProperty(value = "活动限购数量")
    private Integer promotionPerLimit;

    @ApiModelProperty(value = "促销类型：0->没有促销使用原价;1->使用促销价;2->使用满减价格;")
    private Integer promotionType;

    @ApiModelProperty(value = "满减")
    private List<SpuFullReductionSimpleVO> spuFullReductionList;

}
