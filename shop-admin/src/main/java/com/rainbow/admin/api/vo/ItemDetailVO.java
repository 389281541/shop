package com.rainbow.admin.api.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "ItemDetailVO", description = "类别VO")
public class ItemDetailVO extends ItemSimpleVO {

    @ApiModelProperty(value = "父类别ID")
    private Long parentId;
}
