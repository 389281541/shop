package com.rainbow.admin.api.dto;

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
@ApiModel(value = "SpuSpecSaveDTO", description = "spu属性传输对象")
public class SpuSpecSaveDTO extends BaseDTO {

    @ApiModelProperty(value = "商品ID")
    private Long spuId;

    @ApiModelProperty(value = "属性名ID")
    private Long specNameId;
}
