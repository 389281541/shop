package com.rainbow.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "SmsSendDTO", description = "短信发送传输对象")
public class SmsSendDTO extends BaseDTO {

    @ApiModelProperty("手机号")
    @NotNull(message = "手机号不能为空")
    private String mobile;

}
