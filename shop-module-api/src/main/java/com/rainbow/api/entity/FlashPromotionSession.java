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
import java.time.LocalTime;

/**
 * 限时购场次表
 *
 * @author lujinwei
 * @since 2020-02-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("flash_promotion_session")
@ApiModel(value="FlashPromotionSession对象", description="限时购场次表")
public class FlashPromotionSession extends Model<FlashPromotionSession> {

    @ApiModelProperty(value = "编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "场次名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "每日开始时间")
    @TableField("start_time")
    private LocalTime startTime;

    @ApiModelProperty(value = "每日结束时间")
    @TableField("end_time")
    private LocalTime endTime;

    @ApiModelProperty(value = "启用状态：0->不启用；1->启用")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "删除状态 0-未删除 1-已删除")
    @TableField("del_status")
    private Integer delStatus;


}
