package com.rainbow.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 店铺表
 *
 * @author lujinwei
 * @since 2019-12-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("shop")
@ApiModel(value="Shop对象", description="店铺表")
public class Shop extends Model<Shop> {

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "店铺名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "店铺类型：1.自营，2.平台")
    @TableField("type")
    private Integer type;

    @ApiModelProperty(value = "店主姓名")
    @TableField("keeper_name")
    private String keeperName;

    @ApiModelProperty(value = "联系电话")
    @TableField("phone_number")
    private String phoneNumber;

    @ApiModelProperty(value = "供应商开户银行名称")
    @TableField("bank_name")
    private String bankName;

    @ApiModelProperty(value = "银行账号")
    @TableField("bank_account")
    private String bankAccount;

    @ApiModelProperty(value = "店铺地址(默认发货地址)")
    @TableField("address")
    private String address;

    @ApiModelProperty(value = "状态 0审核拒绝，1正在审核，2审核通过")
    @TableField("audit_status")
    private Integer auditStatus;

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
