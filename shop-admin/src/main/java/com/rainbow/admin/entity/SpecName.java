package com.rainbow.admin.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.rainbow.common.model.IdName;
import com.rainbow.common.vo.IdNameVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 规格名称表
 *
 * @author lujinwei
 * @since 2019-11-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("spec_name")
@ApiModel(value="SpecName对象", description="规格名称表")
public class SpecName extends Model<SpecName> {

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "属性名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "类别ID")
    @TableField("item_id")
    private Long itemId;

    @ApiModelProperty(value = "是否存在别名 0-不存在 1-存在")
    @TableField("exist_alias")
    private Integer existAlias;

    @ApiModelProperty(value = "是否颜色属性 0-不是 1-是")
    @TableField("is_color")
    private Integer color;

    @ApiModelProperty(value = "是否枚举属性 0-不是 1-是")
    @TableField("is_enumeration")
    private Integer enumeration;

    @ApiModelProperty(value = "是否输入属性 0-不是 1-是")
    @TableField("is_input")
    private Integer input;

    @ApiModelProperty(value = "是否关键属性 0-不是 1-是")
    @TableField("is_key")
    private Integer key;

    @ApiModelProperty(value = "是否销售属性 0-不是 1-是")
    @TableField("is_sku")
    private Integer sku;

    @ApiModelProperty(value = "是否搜索字段 0-不是 1-是")
    @TableField("is_search")
    private Integer search;

    @ApiModelProperty(value = "是否必须 0-不是 1-是")
    @TableField("is_must")
    private Integer must;

    @ApiModelProperty(value = "是否多选 0-不是 1-是")
    @TableField("is_multiple")
    private Integer multiple;

    @ApiModelProperty(value = "删除状态 0-未删除 1-已删除")
    @TableField("del_status")
    @TableLogic
    private Integer delStatus;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "属性值列表")
    private List<IdNameVO> specValues;

}
