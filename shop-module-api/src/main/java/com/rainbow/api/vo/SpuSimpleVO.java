package com.rainbow.api.vo;

import com.rainbow.common.vo.IdNameVO;
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
@ApiModel(value = "SpuSimpleVO", description = "SPU列表VO")
public class SpuSimpleVO extends IdNameVO {

    @ApiModelProperty(value = "商品编号")
    private String spuNo;

    @ApiModelProperty(value = "品牌Id")
    private Long brandId;

    @ApiModelProperty(value = "品牌名称")
    private String brandName;

    @ApiModelProperty(value = "类别ID")
    private Long itemId;

    @ApiModelProperty(value = "类别名称")
    private String itemName;

    @ApiModelProperty(value = "父类别ID")
    private Long parentItemId;

    @ApiModelProperty(value = "店铺ID")
    private Long shopId;

    @ApiModelProperty(value = "店铺名称")
    private String shopName;

    @ApiModelProperty(value = "销量")
    private Integer sale;

    @ApiModelProperty(value = "单位")
    private String unit;

    @ApiModelProperty(value = "上下架状态 0下架 1上架")
    private Integer saleStatus;

    @ApiModelProperty(value = "是否推荐 0不推荐 1推荐")
    private Integer recommendStatus;

    @ApiModelProperty(value = "审核状态：0审核拒绝，1正在审核，2审核通过")
    private Integer auditStatus;

    @ApiModelProperty(value = "spu排序")
    private Integer sortId;


}
