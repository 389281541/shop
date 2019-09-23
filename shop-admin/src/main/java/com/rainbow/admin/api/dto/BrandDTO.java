package com.rainbow.admin.api.dto;

import com.rainbow.common.dto.BaseDTO;
import com.rainbow.common.dto.IdNameDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 品牌数据传输对象
 *
 * @author lujinwei
 * @since 2019-09-20
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "BrandDTO", description = "品牌VO")
public class BrandDTO extends IdNameDTO {

    @ApiModelProperty(value = "品牌logo")
    private String logo;

    @ApiModelProperty(value = "类别ID")
    @NotNull(message = "类别不能为空")
    private Long itemId;

    @ApiModelProperty(value = "品牌描述")
    private String description;
}
