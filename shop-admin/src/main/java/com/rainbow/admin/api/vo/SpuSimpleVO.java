package com.rainbow.admin.api.vo;

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
    private Long spuNo;

    @ApiModelProperty(value = "品牌")
    private IdNameVO brand;

    @ApiModelProperty(value = "类别")
    private IdNameVO item;

    @ApiModelProperty(value = "店铺")
    private IdNameVO shop;

    @ApiModelProperty(value = "销量")
    private Integer sale;

    @ApiModelProperty(value = "单位")
    private String unit;

    @ApiModelProperty(value = "上下架状态 0下架 1上架")
    private Integer saleStatus;

    @ApiModelProperty(value = "审核状态：0审核拒绝，1正在审核，2审核通过")
    private Integer auditStatus;

    @ApiModelProperty(value = "spu排序")
    private Integer sortId;

    @ApiModelProperty(value = "是否推荐 0不推荐 1推荐")
    private Integer recommend;


}