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
 * 轮播图表
 *
 * @author lujinwei
 * @since 2020-03-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("home_advertise")
@ApiModel(value="HomeAdvertise对象", description="轮播图表")
public class HomeAdvertise extends Model<HomeAdvertise> {

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "主题")
    @TableField("theme")
    private String theme;

    @ApiModelProperty(value = "位置：0->轮播图，1->首页活动位")
    @TableField("type")
    private Integer type;

    @ApiModelProperty(value = "图片链接")
    @TableField("img_url")
    private String imgUrl;

    @ApiModelProperty(value = "跳转链接")
    @TableField("forward_url")
    private String forwardUrl;

    @ApiModelProperty(value = "上线状态 0-未上线 1-已上线")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "排序ID")
    @TableField("sort_id")
    private Long sortId;

    @ApiModelProperty(value = "备注")
    @TableField("note")
    private String note;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;


}
