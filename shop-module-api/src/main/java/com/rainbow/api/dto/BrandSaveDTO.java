package com.rainbow.api.dto;

import com.rainbow.common.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

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
@ApiModel(value = "BrandSaveDTO", description = "品牌添加DTO")
public class BrandSaveDTO extends BaseDTO {

    @ApiModelProperty(value = "品牌名称")
    @NotBlank(message = "品牌名不能为空")
    private String name;

    @ApiModelProperty(value = "品牌logo")
    private String logo;

    @ApiModelProperty(value = "类别ID")
    @NotNull(message = "类别不能为空")
    private List<Long> itemIds;

    @ApiModelProperty(value = "品牌描述")
    private String description;
}
