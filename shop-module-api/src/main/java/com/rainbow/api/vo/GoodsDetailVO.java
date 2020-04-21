package com.rainbow.api.vo;

import com.rainbow.common.model.HMS;
import com.rainbow.common.vo.FatherChildrenVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 商品详情VO
 *
 * @author lujinwei
 * @since 2020/3/8
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "GoodsDetailVO", description = "商品详情VO")
public class GoodsDetailVO extends GoodsSimpleVO {

    @ApiModelProperty(value = "商品介绍主图")
    private String masterImgUrl;

    @ApiModelProperty(value = "促销类型：0->没有促销使用原价;1->使用促销价；2->使用满减价格；")
    private Integer promotionType;

    @ApiModelProperty(value = "促销开始时间")
    private LocalDateTime promotionStartTime;

    @ApiModelProperty(value = "促销结束时间")
    private LocalDateTime promotionEndTime;

    @ApiModelProperty(value = "活动限购数量")
    private Integer promotionPerLimit;

    @ApiModelProperty(value = "商品相册图")
    private List<String> photoList;

    @ApiModelProperty(value = "商品参数")
    private List<SpuSpecSimpleVO> spuParameters;

    @ApiModelProperty(value = "满减信息")
    private List<SpuFullReductionSimpleVO> fullReductionList;

    @ApiModelProperty(value = "sku信息")
    private List<SkuSimpleVO> skuList;

    @ApiModelProperty(value = "选择属性列表")
    private List<FatherChildrenVO> specNameList;

    @ApiModelProperty(value = "秒杀商品信息")
    private FlashPromotionSpuInfoVO flashSpuInfo;

}
