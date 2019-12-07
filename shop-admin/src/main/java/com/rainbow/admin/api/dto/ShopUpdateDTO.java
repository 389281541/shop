package com.rainbow.admin.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "ShopUpdateDTO", description = "商铺更新传输对象")
public class ShopUpdateDTO extends ShopSaveDTO {

    @ApiModelProperty(value = "品牌ID")
    @NotNull(message = "ID不能为空")
    private Long id;
}
