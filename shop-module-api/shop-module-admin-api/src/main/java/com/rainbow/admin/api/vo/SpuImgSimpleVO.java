package com.rainbow.admin.api.vo;

import com.rainbow.common.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "SpuImgSimpleVO", description = "spu img视图对象")
public class SpuImgSimpleVO extends BaseDTO {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "商品ID")
    private Long spuId;

    @ApiModelProperty(value = "sku ID")
    private Long skuId;

    @ApiModelProperty(value = "图片地址")
    private String imgUrl;

    @ApiModelProperty(value = "是否封面图 0-非封面图 1-封面图")
    private Integer coverFlag;

    @ApiModelProperty(value = "是否主图 0-非主图 1-主图")
    private Integer masterFlag;

    @ApiModelProperty(value = "是否颜色图 0-非颜色图 1-颜色图")
    private Integer colorFlag;

    @ApiModelProperty(value = "sku spec关联表")
    private List<SkuSpecSimpleVO> skuSpecList;

    @ApiModelProperty(value = "属性键值对")
    private Map<String, String> skuSpecMap;

}
