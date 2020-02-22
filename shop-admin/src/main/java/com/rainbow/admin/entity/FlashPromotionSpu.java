package com.rainbow.admin.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 商品限时购与商品关系表
 *
 * @author lujinwei
 * @since 2020-02-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("flash_promotion_spu")
@ApiModel(value="FlashPromotionSpu对象", description="商品限时购与商品关系表")
public class FlashPromotionSpu extends Model<FlashPromotionSpu> {

    @ApiModelProperty(value = "编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "秒杀活动ID")
    @TableField("flash_promotion_id")
    private Long flashPromotionId;

    @ApiModelProperty(value = "秒杀时间段ID")
    @TableField("flash_promotion_session_id")
    private Long flashPromotionSessionId;

    @ApiModelProperty(value = "商品ID")
    @TableField("spu_id")
    private Long spuId;

    @ApiModelProperty(value = "限时购价格")
    @TableField("flash_discount_price")
    private BigDecimal flashDiscountPrice;

    @ApiModelProperty(value = "限时购数量")
    @TableField("flash_promotion_num")
    private Integer flashPromotionNum;

    @ApiModelProperty(value = "每人限购数量")
    @TableField("flash_promotion_limit")
    private Integer flashPromotionLimit;

    @ApiModelProperty(value = "排序")
    @TableField("sort_id")
    private Integer sortId;


}
