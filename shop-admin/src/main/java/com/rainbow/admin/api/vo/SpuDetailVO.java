package com.rainbow.admin.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "SpuDetailVO", description = "Spu详情VO")
public class SpuDetailVO extends SpuSimpleVO {

    @ApiModelProperty(value = "商品描述")
    private String description;

    @ApiModelProperty(value = "使用积分限制")
    private Integer useIntegrationLimit;

    @ApiModelProperty(value = "促销开始时间")
    private LocalDateTime promotionStartTime;

    @ApiModelProperty(value = "促销结束时间")
    private LocalDateTime promotionEndTime;

    @ApiModelProperty(value = "活动限购数量")
    private Integer promotionPerLimit;

    @ApiModelProperty(value = "促销类型：0->没有促销使用原价;1->使用促销价；2->使用满减价格；")
    private Integer promotionType;

    @ApiModelProperty(value = "sku列表")
    private List<SkuSimpleVO> skuList;

    @ApiModelProperty(value = "spu图片列表")
    private List<SpuImgSimpleVO> spuImgList;

    @ApiModelProperty(value = "spu属性列表")
    private List<SpuSpecSimpleVO> spuSpecList;

    @ApiModelProperty(value = "spu满减优惠列表")
    private List<SpuFullReductionSimpleVO> spuFullReductionSimpleVOList;
}
