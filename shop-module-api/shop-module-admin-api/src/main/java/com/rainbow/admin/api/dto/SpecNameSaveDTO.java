package com.rainbow.admin.api.dto;

import com.rainbow.common.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "SpecNameSaveDTO", description = "属性名保存传输对象")
public class SpecNameSaveDTO extends BaseDTO {

    @ApiModelProperty(value = "名称")
    @NotBlank(message = "名称不能为空")
    private String name;

    @ApiModelProperty(value = "类别ID")
    @NotNull(message = "类别不能为空")
    private Long itemId;

    @ApiModelProperty(value = "是否存在别名：0-不存在 1-存在")
    private Integer existAlias;

    @ApiModelProperty(value = "是否颜色属性：0-不是 1-是")
    private Integer color;

    @ApiModelProperty(value = "是否枚举属性：0-不是 1-是")
    private Integer enumeration;

    @ApiModelProperty(value = "是否输入属性：0-不是 1-是")
    private Integer input;

    @ApiModelProperty(value = "是否关键属性：0-不是 1-是")
    private Integer key;

    @ApiModelProperty(value = "是否销售属性：0-不是 1-是")
    private Integer sku;

    @ApiModelProperty(value = "是否搜索属性：0-不是 1-是")
    private Integer search;

    @ApiModelProperty(value = "是否多选属性：0-不是 1-是")
    private Integer multiple;

    @ApiModelProperty(value = "类型 0-规格 1-参数")
    private Integer type;
}
