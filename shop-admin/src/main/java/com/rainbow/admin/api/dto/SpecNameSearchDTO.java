package com.rainbow.admin.api.dto;

import com.rainbow.common.dto.IdNameDTO;
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
@ApiModel(value = "SpecNameSearchDTO", description = "属性名搜索对象")
public class SpecNameSearchDTO extends IdNameDTO {

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

}