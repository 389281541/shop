package com.rainbow.api.dto;

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
@ApiModel(value = "CustomerAddressUpdateDTO", description = "用户地址更新传输对象")
public class CustomerAddressUpdateDTO extends CustomerAddressSaveDTO {

    @ApiModelProperty(value = "品牌ID")
    @NotNull(message = "ID不能为空")
    private Long id;
}
