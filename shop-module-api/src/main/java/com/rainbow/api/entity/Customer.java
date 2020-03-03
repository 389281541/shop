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

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 顾客登录表
 *
 * @author lujinwei
 * @since 2020-02-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("customer")
@ApiModel(value="Customer对象", description="顾客登录表")
public class Customer extends Model<Customer> {

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户名")
    @TableField("user_name")
    private String userName;

    @ApiModelProperty(value = "密码")
    @TableField("password")
    private String password;

    @ApiModelProperty(value = "盐")
    @TableField("salt")
    private String salt;

    @ApiModelProperty(value = "用户电话")
    @TableField("mobile")
    private String mobile;

    @ApiModelProperty(value = "用户头像")
    @TableField("avatar")
    private String avatar;

    @ApiModelProperty(value = "真实姓名")
    @TableField("identity_name")
    private String identityName;

    @ApiModelProperty(value = "证件类型：1 身份证，2 军官证，3 护照")
    @TableField("identity_type")
    private Integer identityType;

    @ApiModelProperty(value = "证件号码")
    @TableField("identity_no")
    private String identityNo;

    @ApiModelProperty(value = "邮箱")
    @TableField("email")
    private String email;

    @ApiModelProperty(value = "性别: 0-男 1-女")
    @TableField("gender")
    private Boolean gender;

    @ApiModelProperty(value = "用户生日")
    @TableField("birthday")
    private LocalDate birthday;

    @ApiModelProperty(value = "积分")
    @TableField("integration")
    private Integer integration;

    @ApiModelProperty(value = "用户状态 0-正常 1-禁用")
    @TableField("user_status")
    private Integer userStatus;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "上次登陆时间")
    @TableField("last_login_time")
    private LocalDateTime lastLoginTime;


}
