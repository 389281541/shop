package com.rainbow.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 订单条目-属性关联表
 *
 * @author lujinwei
 * @since 2020-02-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("order_sku_spec")
@ApiModel(value="OrderSkuSpec对象", description="订单条目-属性关联表")
public class OrderSkuSpec extends Model<OrderSkuSpec> {

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "订单项目id")
    @TableField("order_sku_id")
    private Long orderSkuId;

    @ApiModelProperty(value = "属性名ID")
    @TableField("spec_name_id")
    private Long specNameId;

    @ApiModelProperty(value = "属性名")
    @TableField("spec_name")
    private String specName;

    @ApiModelProperty(value = "属性值ID")
    @TableField("spec_value_id")
    private Long specValueId;

    @ApiModelProperty(value = "属性值")
    @TableField("spec_value")
    private Long specValue;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;


}
