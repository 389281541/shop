package com.rainbow.api.entity;

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
 * 团购表
 *
 * @author lujinwei
 * @since 2020-05-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("groupon")
@ApiModel(value="Groupon对象", description="团购表")
public class Groupon extends Model<Groupon> {

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "创建人用户Id")
    @TableField("creator_id")
    private Integer creatorId;

    @ApiModelProperty(value = "创建人name")
    @TableField("creator_name")
    private String creatorName;

    @ApiModelProperty(value = "拼团活动id")
    @TableField("groupon_activity_id")
    private Integer grouponActivityId;

    @ApiModelProperty(value = "订单完成时间")
    @TableField("success_time")
    private LocalDateTime successTime;

    @ApiModelProperty(value = "拼团状态:0 拼团中 1成功  2失败")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "成团类型 ：0 学员拼团成功 1机构手动成团")
    @TableField("type")
    private Integer type;

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
