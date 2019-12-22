package com.rainbow.admin.api.vo;

import com.rainbow.admin.entity.Sku;
import com.rainbow.admin.entity.SpuImg;
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

    @ApiModelProperty(value = "spu图片")
    private List<SpuImg> spuImgList;

    @ApiModelProperty(value = "sku图片")
    private List<Sku> skuList;

}
