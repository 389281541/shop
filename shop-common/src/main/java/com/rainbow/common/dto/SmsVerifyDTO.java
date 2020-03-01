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
@ApiModel(value = "SmsVerifyDTO", description = "短信验证传输对象")
public class SmsVerifyDTO extends BaseDTO {

    @ApiModelProperty("手机号")
    @NotNull(message = "手机号不能为空")
    private String mobile;

    @ApiModelProperty("验证码")
    @NotNull(message = "验证码不能为空")
    private String verifyCode;

}
