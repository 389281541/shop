package com.rainbow.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 顾客地址表
 *
 * @author lujinwei
 * @since 2020-03-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("customer_address")
@ApiModel(value="CustomerAddress对象", description="顾客地址表")
public class CustomerAddress extends Model<CustomerAddress> {

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户ID")
    @TableField("customer_id")
    private Long customerId;

    @ApiModelProperty(value = "收货人姓名")
    @TableField("receiver_name")
    private String receiverName;

    @ApiModelProperty(value = "邮编")
    @TableField("zip")
    private String zip;

    @ApiModelProperty(value = "地区表中省份")
    @TableField("province")
    private String province;

    @ApiModelProperty(value = "收货人手机号")
    @TableField("phone")
    private String phone;

    @ApiModelProperty(value = "地区表中城市")
    @TableField("city")
    private String city;

    @ApiModelProperty(value = "地区表中的区")
    @TableField("district")
    private String district;

    @ApiModelProperty(value = "具体的地址门牌号")
    @TableField("address")
    private String address;

    @ApiModelProperty(value = "是否默认 0-否 1-是")
    @TableField("is_default")
    private Integer isDefault;

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
