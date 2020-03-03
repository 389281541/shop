package com.rainbow.api.dto;

import com.rainbow.common.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "SkuUpdateDTO", description = "sku更新传输对象")
public class SkuBatchUpdateDTO extends BaseDTO {

    @ApiModelProperty(value = "sku批量更新对象")
    private List<SkuUpdateDTO> skuUpdateDTOList;
}
