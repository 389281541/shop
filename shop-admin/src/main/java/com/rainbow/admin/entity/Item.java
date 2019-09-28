package com.rainbow.admin.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 类别表
 *
 * @author lujinwei
 * @since 2019-08-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("item")
@ApiModel(value = "Item对象", description = "类别表")
public class Item extends Model<Item> {

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "类别名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "编号")
    @TableField("item_no")
    private Long itemNo;

    @ApiModelProperty(value = "父类别ID")
    @TableField("parent_id")
    private Long parentId;

    @ApiModelProperty(value = "排序ID")
    @TableField("sort_id")
    private Long sortId;

    @ApiModelProperty(value = "是否父级别 0-非父级别 1-父级别")
    @TableField("is_parent")
    private Boolean parent;

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


}
