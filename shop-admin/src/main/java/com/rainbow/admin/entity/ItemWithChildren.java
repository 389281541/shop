package com.rainbow.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.rainbow.common.vo.IdNameVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "ItemWithChildren对象", description = "类别表")
public class ItemWithChildren extends Item {

    @ApiModelProperty(value = "子类名列表")
    private List<IdNameVO> children;
}
