package com.rainbow.api.dto;

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
@ApiModel(value = "NoteChangeDTO", description = "备注信息修改")
public class NoteChangeDTO extends BaseDTO {

    @ApiModelProperty(value = "订单id")
    private Long id;

    @ApiModelProperty(value = "备注信息")
    private String note;
}
