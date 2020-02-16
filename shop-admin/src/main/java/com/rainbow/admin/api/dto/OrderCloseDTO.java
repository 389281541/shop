package com.rainbow.admin.api.dto;

import com.rainbow.common.dto.IdsDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "OrderCloseDTO", description = "订单关闭DTO")
public class OrderCloseDTO extends IdsDTO {

    @ApiModelProperty("note")
    @NotBlank(message = "说明不能为空")
    private String note;
}
