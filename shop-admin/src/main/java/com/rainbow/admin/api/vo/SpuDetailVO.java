package com.rainbow.admin.api.vo;

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
@ApiModel(value = "SpuDetailVO", description = "Spu详情VO")
public class SpuDetailVO extends SpuSimpleVO {

    @ApiModelProperty(value = "商品描述")
    private String description;

    @ApiModelProperty(value = "sku列表")
    private List<SkuSimpleVO> skuList;

    @ApiModelProperty(value = "spu图片列表")
    private List<SpuImgSimpleVO> spuImgList;

    @ApiModelProperty(value = "spu属性列表")
    private List<SpuSpecSimpleVO> spuSpecList;
}
