package com.rainbow.admin.api.dto;


import com.rainbow.common.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "ItemSaveDTO", description = "类别保存传输对象")
public class ItemSaveDTO extends BaseDTO {

    @ApiModelProperty(value = "名称")
    @NotBlank(message = "名称不能为空")
    private String name;

    @ApiModelProperty(value = "父类别ID")
    private Long parentId;

    @ApiModelProperty(value = "类别编号")
    @Range(min = 10000, max = 20000, message = "类别编号只能是10000-20000内的数")
    private Long itemNo;

}
