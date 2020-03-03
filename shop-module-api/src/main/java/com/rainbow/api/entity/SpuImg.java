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
 * 商品图片表
 *
 * @author lujinwei
 * @since 2019-12-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("spu_img")
@ApiModel(value="SpuImg对象", description="商品图片表")
public class SpuImg extends Model<SpuImg> {

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "SPU ID")
    @TableField("spu_id")
    private Long spuId;

    @ApiModelProperty(value = "商品ID")
    @TableField("sku_id")
    private Long skuId;

    @ApiModelProperty(value = "图片地址")
    @TableField("img_url")
    private String imgUrl;

    @ApiModelProperty(value = "是否封面图 0-非封面图 1-封面图")
    @TableField("is_cover")
    private Integer coverFlag;

    @ApiModelProperty(value = "是否主图 0-非主图 1-主图")
    @TableField("is_master")
    private Integer masterFlag;

    @ApiModelProperty(value = "是否颜色图 0-非颜色图 1-颜色图")
    @TableField("is_color")
    private Integer colorFlag;

    @ApiModelProperty(value = "图片位置")
    @TableField("sort_id")
    private Integer sortId;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;


}
