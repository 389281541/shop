package com.rainbow.admin.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 商品表
 *
 * @author lujinwei
 * @since 2019-12-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("spu")
@ApiModel(value="Spu对象", description="商品表")
public class Spu extends Model<Spu> {

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "商品名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "商品编号")
    @TableField("spu_no")
    private String spuNo;

    @ApiModelProperty(value = "品牌ID")
    @TableField("brand_id")
    private Long brandId;

    @ApiModelProperty(value = "品牌名称")
    @TableField("brand_name")
    private String brandName;

    @ApiModelProperty(value = "类别ID")
    @TableField("item_id")
    private Long itemId;

    @ApiModelProperty(value = "类别名称")
    @TableField("item_name")
    private String itemName;

    @ApiModelProperty(value = "店铺ID")
    @TableField("shop_id")
    private Long shopId;

    @ApiModelProperty(value = "类别名称")
    @TableField("shop_name")
    private String shopName;

    @ApiModelProperty(value = "销量")
    @TableField("sale")
    private Integer sale;

    @ApiModelProperty(value = "单位")
    @TableField("unit")
    private String unit;

    @ApiModelProperty(value = "商品描述")
    @TableField("description")
    private String description;

    @ApiModelProperty(value = "上下架状态 0下架 1上架")
    @TableField("sale_status")
    private Integer saleStatus;

    @ApiModelProperty(value = "审核状态：0审核拒绝，1正在审核，2审核通过")
    @TableField("audit_status")
    private Integer auditStatus;

    @ApiModelProperty(value = "spu排序")
    @TableField("sort_id")
    private Integer sortId;

    @ApiModelProperty(value = "是否推荐 0不推荐 1推荐")
    @TableField("recommend")
    private Integer recommendStatus;

    @ApiModelProperty(value = "使用积分限制")
    @TableField("use_integration_limit")
    private Integer useIntegrationLimit;

    @ApiModelProperty(value = "促销开始时间")
    @TableField("promotion_start_time")
    private LocalDateTime promotionStartTime;

    @ApiModelProperty(value = "促销结束时间")
    @TableField("promotion_end_time")
    private LocalDateTime promotionEndTime;

    @ApiModelProperty(value = "活动限购数量")
    @TableField("promotion_per_limit")
    private Integer promotionPerLimit;

    @ApiModelProperty(value = "促销类型：0->没有促销使用原价;1->使用促销价；2->使用满减价格；")
    @TableField("promotion_type")
    private Integer promotionType;

    @ApiModelProperty(value = "删除状态 0-未删除 1-已删除")
    @TableField("del_status")
    private Integer delStatus;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;


}
