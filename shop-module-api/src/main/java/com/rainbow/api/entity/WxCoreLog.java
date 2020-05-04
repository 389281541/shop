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
 * 微信日志
 *
 * @author lujinwei
 * @since 2020-05-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("wx_core_log")
@ApiModel(value="WxCoreLog对象", description="微信日志")
public class WxCoreLog extends Model<WxCoreLog> {

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "级别")
    @TableField("info_level")
    private String infoLevel;

    @ApiModelProperty(value = "关键字")
    @TableField("info_keyword")
    private String infoKeyword;

    @ApiModelProperty(value = "搜索关键字")
    @TableField("search_keyword")
    private String searchKeyword;

    @ApiModelProperty(value = "参数")
    @TableField("info_body")
    private String infoBody;

    @ApiModelProperty(value = "订单号")
    @TableField("order_no")
    private Long orderNo;

    @ApiModelProperty(value = "主机")
    @TableField("cur_host")
    private String curHost;

    @ApiModelProperty(value = "create time")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "支付错误")
    @TableField("pay_exception")
    private String payException;

    @ApiModelProperty(value = "报警日志")
    @TableField("alarm_msg")
    private String alarmMsg;


}
