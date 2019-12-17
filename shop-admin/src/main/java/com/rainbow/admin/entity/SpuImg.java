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
    private Integer isCover;

    @ApiModelProperty(value = "是否主图 0-非主图 1-主图")
    @TableField("is_master")
    private Integer isMaster;

    @ApiModelProperty(value = "是否颜色图 0-非颜色图 1-颜色图")
    @TableField("is_color")
    private Integer isColor;

    @ApiModelProperty(value = "图片位置")
    @TableField("sort_id")
    private Integer sortId;

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
