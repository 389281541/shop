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
@ApiModel(value = "ShopUpdateDTO", description = "SPU更新DTO")
public class SpuUpdateDTO extends SpuSaveDTO {

    @ApiModelProperty(value = "ID")
    @NotNull(message = "ID不能为空")
    private Long id;
}
