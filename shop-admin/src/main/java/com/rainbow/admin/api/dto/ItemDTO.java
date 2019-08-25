package com.rainbow.admin.api.dto;


import com.rainbow.common.dto.IdNameDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Range;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "ItemDTO", description = "类别传输对象")
public class ItemDTO extends IdNameDTO {

    @ApiModelProperty(value = "父类别ID")
    private Long parentId;

    @ApiModelProperty(value = "类别编号")
    @Range(min = 10000, max = 20000, message = "只能是10000-20000内的数")
    private Long itemNo;

}
