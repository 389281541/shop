package com.rainbow.admin.api.dto;

import com.rainbow.common.annotation.EnumValidAnnotation;
import com.rainbow.common.dto.BaseDTO;
import com.rainbow.common.enums.BooleanEnum;
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
@ApiModel(value = "SpuSaveDTO", description = "SPU保存DTO")
public class SpuSaveDTO extends BaseDTO {

    @ApiModelProperty(value = "商品名称")
    private String name;

    @ApiModelProperty(value = "品牌ID")
    private Long brandId;

    @ApiModelProperty(value = "类别ID")
    private Long itemId;

    @ApiModelProperty(value = "店铺ID")
    private Long shopId;

    @ApiModelProperty(value = "单位")
    private String unit;

    @ApiModelProperty(value = "商品描述")
    private String description;

    @ApiModelProperty(value = "spu排序")
    private Integer sortId;

    @ApiModelProperty(value = "是否推荐 0不推荐 1推荐")
    @EnumValidAnnotation(message = "推荐状态错误" , allowNull = false, target = BooleanEnum.class)
    private Integer recommend;

    @ApiModelProperty(value = "sku列表")
    private List<SkuSimpleDTO> skuSimpleDTOList;

    @ApiModelProperty(value = "spu图片列表")
    private List<String> imgList;


}
