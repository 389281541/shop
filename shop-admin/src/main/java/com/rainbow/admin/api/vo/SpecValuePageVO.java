package com.rainbow.admin.api.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rainbow.common.dto.BaseDTO;
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
@ApiModel(value = "SpecValuePageVO", description = "属性值VO")
public class SpecValuePageVO extends BaseDTO {

    @ApiModelProperty(value = "属性名ID")
    private String specName;

    @ApiModelProperty(value = "属性值分页对象")
    private IPage<SpecValueSimpleVO> specValuePage;
}
