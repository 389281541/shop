package com.rainbow.admin.entity;

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
 * 商品基本属性表
 *
 * @author lujinwei
 * @since 2019-11-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("spu_spec")
@ApiModel(value="SpuSpec对象", description="商品基本属性表")
public class SpuSpec extends Model<SpuSpec> {

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "商品ID")
    @TableField("spu_id")
    private Long spuId;

    @ApiModelProperty(value = "属性名ID")
    @TableField("spec_name_id")
    private Long specNameId;

    @ApiModelProperty(value = "属性名")
    @TableField("spec_name")
    private String specName;

    @ApiModelProperty(value = "属性值ID")
    @TableField("spec_value_id")
    private Long specValueId;

    @ApiModelProperty(value = "属性值")
    @TableField("spec_value")
    private String specValue;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;


}
