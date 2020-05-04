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
 * 团购商铺收款微信信息
 *
 * @author lujinwei
 * @since 2020-05-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("shop_wechat_info")
@ApiModel(value="ShopWechatInfo对象", description="团购商铺收款微信信息")
public class ShopWechatInfo extends Model<ShopWechatInfo> {

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "店铺id")
    @TableField("shop_id")
    private Long shopId;

    @ApiModelProperty(value = "收款微信openId")
    @TableField("open_id")
    private String openId;

    @ApiModelProperty(value = "收款微信持有人真实姓名")
    @TableField("real_name")
    private String realName;

    @ApiModelProperty(value = "收款微信昵称")
    @TableField("nick_name")
    private String nickName;

    @ApiModelProperty(value = "收款微信性别，值为1时是男性，值为2时是女性，值为0时是未知")
    @TableField("sex")
    private Boolean sex;

    @ApiModelProperty(value = "用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头\n  像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。")
    @TableField("head_img_url")
    private String headImgUrl;

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
