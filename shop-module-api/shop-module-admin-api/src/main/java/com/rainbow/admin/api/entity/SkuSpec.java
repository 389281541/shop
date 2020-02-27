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
 * sku属性关联表
 *
 * @author lujinwei
 * @since 2019-12-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sku_spec")
@ApiModel(value="SkuSpec对象", description="sku属性关联表")
public class SkuSpec extends Model<SkuSpec> {

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "SKUID")
    @TableField("sku_id")
    private Long skuId;

    @ApiModelProperty(value = "属性值ID")
    @TableField("spec_name_id")
    private Long specNameId;

    @ApiModelProperty(value = "属性值ID")
    @TableField("spec_name_id")
    private String specName;

    @ApiModelProperty(value = "属性值ID")
    @TableField("spec_value_id")
    private Long specValueId;

    @ApiModelProperty(value = "属性值")
    @TableField("spec_value")
    private String specValue;

    @ApiModelProperty(value = "排序ID")
    @TableField("sort_id")
    private Integer sortId;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;


}
