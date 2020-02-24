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

import java.time.LocalDateTime;
import java.util.List;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "SpuSaveDTO", description = "SPU保存DTO")
public class SpuSaveDTO extends BaseDTO {

    @ApiModelProperty(value = "商品名称")
    private String name;

    @ApiModelProperty(value = "商品编号")
    private String spuNo;

    @ApiModelProperty(value = "品牌ID")
    private Long brandId;

    @ApiModelProperty(value = "品牌名称")
    private String brandName;

    @ApiModelProperty(value = "类别ID")
    private Long itemId;

    @ApiModelProperty(value = "类别名称")
    private String itemName;

    @ApiModelProperty(value = "店铺ID")
    private Long shopId;

    @ApiModelProperty(value = "类别名称")
    private String shopName;

    @ApiModelProperty(value = "销量")
    private Integer sale;

    @ApiModelProperty(value = "单位")
    private String unit;

    @ApiModelProperty(value = "商品描述")
    private String description;

    @ApiModelProperty(value = "上下架状态 0下架 1上架")
    private Integer saleStatus;

    @ApiModelProperty(value = "审核状态：0审核拒绝，1正在审核，2审核通过")
    private Integer auditStatus;

    @ApiModelProperty(value = "spu排序")
    private Integer sortId;

    @ApiModelProperty(value = "是否推荐 0不推荐 1推荐")
    @EnumValidAnnotation(message = "推荐状态错误" , allowNull = false, target = BooleanEnum.class)
    private Integer recommendStatus;

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
    private List<SkuSaveDTO> skuList;

    @ApiModelProperty(value = "spu图片列表")
    private List<SpuImgSaveDTO> spuImgList;

    @ApiModelProperty(value = "spu属性列表")
    private List<SpuSpecSaveDTO> spuSpecList;

    @ApiModelProperty(value = "spu满减列表")
    private List<SpuFullReductionSaveDTO> spuFullReductionSaveDTOList;


}
