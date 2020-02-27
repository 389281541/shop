package com.rainbow.admin.api.vo;

import com.rainbow.common.vo.IdNameVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Collection;
import java.util.List;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "SpecNameSimpleVO", description = "属性名VO")
public class SpecNameSimpleVO extends IdNameVO {

    @ApiModelProperty(value = "类别ID")
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
    private Integer critical;

    @ApiModelProperty(value = "是否销售属性：0-不是 1-是")
    private Integer sku;

    @ApiModelProperty(value = "是否搜索属性：0-不是 1-是")
    private Integer search;

    @ApiModelProperty(value = "是否多选属性：0-不是 1-是")
    private Integer multiple;

    @ApiModelProperty(value = "类型 0-规格 1-参数")
    private Integer type;

    @ApiModelProperty(value = "属性值列表-全部")
    private List<IdNameVO> specValues;

    @ApiModelProperty(value = "属性值列表-已选中")
    private Collection<String> selectSpecValues;

}
