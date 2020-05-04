package com.rainbow.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 团购活动表
 *
 * @author lujinwei
 * @since 2020-05-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("groupon_activity")
@ApiModel(value="GrouponActivity对象", description="团购活动表")
public class GrouponActivity extends Model<GrouponActivity> {

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "拼团活动名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "团购活动编号")
    @TableField("activity_no")
    private String activityNo;

    @ApiModelProperty(value = "商品id")
    @TableField("spu_id")
    private Long spuId;

    @ApiModelProperty(value = "封面url")
    @TableField("cover_url")
    private String coverUrl;

    @ApiModelProperty(value = "活动状态：0:下线 1：上线")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "介绍（富文本）")
    @TableField("introduction")
    private String introduction;

    @ApiModelProperty(value = "开始时间")
    @TableField("start_time")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "结束时间")
    @TableField("end_time")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "课程原价")
    @TableField("original_price")
    private BigDecimal originalPrice;

    @ApiModelProperty(value = "团购价格")
    @TableField("group_price")
    private BigDecimal groupPrice;

    @ApiModelProperty(value = "拼团人数")
    @TableField("unit")
    private Integer unit;

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
