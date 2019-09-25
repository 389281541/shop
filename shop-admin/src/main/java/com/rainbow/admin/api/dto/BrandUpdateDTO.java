package com.rainbow.admin.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * 品牌更新DTO
 *
 * @author lujinwei
 * @since 2019-09-24
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "BrandUpdateDTO", description = "品牌更新DTO")
public class BrandUpdateDTO extends BrandSaveDTO {

    @ApiModelProperty(value = "品牌ID")
    @NotNull(message = "ID不能为空")
    private Long id;

    @ApiModelProperty(value = "品牌名称")
    private String name;

}
